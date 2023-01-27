package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.sql.Timestamp
import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.CommonClass
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



/**
 * @author sibis
 *
 */
class Shift_Page extends CommonClass{

	String shiftName = "";

	public void selectTime(String time) {
		WebUI.scrollToPosition(0, 0)
		if(time.contains("AM") || time.contains("am")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/AM_Button'))
		} else {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/PM_Button'))
		}
		switchFrameAndDoActions("TimeSelect", "//td[contains(text(),'"+time+"')]", "click",findTestObject('Home_Page/detailView_iFrame'))
	}



	public void addShift() {
		if (getData("Shift name").equalsIgnoreCase("random")) {
			shiftName = "Test Automation - " + dateTimeStr;
		} else {
			shiftName = getData("Shift name");
		}
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),20)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Shift_Page/shift_Name'), shiftName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/fromTime_Icon'))
		selectTime(getData("From time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/toTime_Icon'))
		selectTime(getData("To time"))
	}

	public void addNonWorkingDetails() {
		if (getData("Shift name").equalsIgnoreCase("random")) {
			shiftName = "Test Automation - " + dateTimeStr;
		} else {
			shiftName = getData("Shift name");
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/addNonWorkingDetails_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWokringDescription_TextBox'), shiftName+"_Non Working")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWorkingFromTime_Icon'))
		selectTime(getData("Non-working from time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWorkingToTime_Icon'))
		selectTime(getData("Non-working to time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/saveShift_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	public void selectShift() {
		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridShift']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(shiftName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridShift']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Shift data found and selected.. !")
		} else {
			throw new Error("Shift not found.. Please check the data !");
		}
	}

	@And("Create shift")
	public void createShift() {
		addShift();
		addNonWorkingDetails()
	}

	@And("Delete shift")
	public void deleteShift() {
		selectShift()
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}
}