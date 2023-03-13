package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;



/**
 * @author sibis
 *
 */
class Manufacturer_Page extends CommonClass{

	String manufacturerName = "";
	String manufacturerCode = "";

	public void manufactureCreation() {
		if (getData("Manufacturer name").equalsIgnoreCase("random")) {
			manufacturerName = "Test Automation - " + dateTimeStr;
		} else {
			manufacturerName = getData("Manufacturer name")
		}

		if (getData("Manufacturer code").equalsIgnoreCase("random")) {
			manufacturerCode = "T-" + dateTimeStr;
		} else {
			manufacturerCode = getData("Manufacturer code")
		}
		Thread.sleep(3000)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'), 20)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'),  manufacturerName)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), manufacturerCode)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/preferredManufacturer_CheckBox'))
	}

	public void addAddress() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/street_TextBox'), "Automation street")
		if(!getData("Country/Region").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/country_DropDown'), getData("Country/Region"), false)
		}
		if(!getData("State/Province").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/state_DropDown'), getData("State/Province"), false)
		}
		if(!getData("City").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/city_DropDown'), getData("City"), false)
		}
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/zipcode_TextBox'), "251098")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Address successfully added..! Success message verified..!")
	}

	public void addContact() {
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/contactAdd_Icon'),20)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addContact_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/firstName_TextBox'), getData("First name"))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/lastName_TextBox'), getData("Last name"))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/email_TextBox'), email)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/saveContact_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Contact successfully added..! Success message verified..!")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/close_Icon'))
	}

	public void selectManufacturer(String name) {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(name)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Manufacturer/Vendor data found and selected.. !")
		} else {
			throw new Error("Manufacturer/Vendor not found.. Please check the data ! Actual data is : " + val);
		}
	}

	public void clickAndConfirmDeletion() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}

	@And("Create manufacturer")
	public void createManufacturer() {
		manufactureCreation()
		addAddress()
		addContact()
	}
	@And("Delete manufacturer")
	public void deleteManufacturer() {
		pageRecordSizeChange("100")
		selectManufacturer(manufacturerName);
		clickAndConfirmDeletion()
	}
}

