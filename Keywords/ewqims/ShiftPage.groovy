package ewqims

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.util.KeywordUtil

public class ShiftPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	public static String ShiftName;

	public void selectTime(String time) {
		WebUI.scrollToPosition(0, 0)
		if(time.contains("AM") || time.contains("am")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/AM_Button'))
		} else {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/PM_Button'))
		}
		switchFrameAndDoActions("TimeSelect", "//td[contains(text(),'"+time+"')]", "click",findTestObject('Home_Page/detailView_iFrame'))
	}

	public void addShift(String Name, String FormTime, String ToTime) {
		Rand = DateTimeStr;
		if (Name.equalsIgnoreCase("random")) {
			ShiftName = "Test Automation - " + Rand;
		} else {
			ShiftName = Name;
		}
		Thread.sleep(3000)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Shift_Page/shift_Name'), ShiftName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/fromTime_Icon'))
		selectTime(FormTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/toTime_Icon'))
		selectTime(ToTime)
	}

	public void addNonWorkingDetails(String Name, String NonWorkingFromTime, String NonWorkingToTime) {

		if (Name.equalsIgnoreCase("random")) {
			ShiftName = "Test Automation - " + Rand;
		} else {
			ShiftName = Name;
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/addNonWorkingDetails_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWokringDescription_TextBox'), ShiftName+"_Non Working")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWorkingFromTime_Icon'))
		selectTime(NonWorkingFromTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/nonWorkingToTime_Icon'))
		selectTime(NonWorkingToTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/saveShift_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	@Keyword
	public void CreateShift(String Name, String FormTime, String ToTime,String NonWorkingFromTime, String NonWorkingToTime) {
		addShift(Name,FormTime,ToTime)
		addNonWorkingDetails(Name,NonWorkingFromTime,NonWorkingToTime);
	}

	@Keyword
	public void DeleteShift() {
		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridShift']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(ShiftName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridShift']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Shift data found and selected.. !")
		}
		else {
			throw new Error("Shift not found.. Please check the data !");
		}

		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}
}
