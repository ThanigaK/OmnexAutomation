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

import common.CommonClass



/**
 * @author sibis
 *
 */
class Preferences_Page extends CommonClass{
	@And("Update the preferences settings based on excel data")
	public void updateThePreferencesSettingsBasedOnExcelData() {
		if(!getData("Session timeout").isEmpty() && !getData("Session timeout").equals("0")) {
			WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/sessionTimeOut_Textbox'), getData("Session timeout"))
		}

		if(!getData("Page size").isEmpty() && !getData("Page size").equals("0")) {
			WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/pageSize_TextBox'), getData("Page size"))
		}

		if(!getData("Default language").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Suite_Module/UserPreferences_Page/defaultLanguage_DropDown'),getData("Default language") , true)
		}

		if(!getData("Date format").isEmpty()) {
			String[] data =getData("Date format").split("/");
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat1_DropDown'), data[0], true)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat2_DropDown'), data[1], true)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat3_DropDown'), data[2], true)
		}

		WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/save_Button'))

		WebUI.verifyElementPresent(findTestObject('Suite_Module/UserPreferences_Page/PreferencesSettingUpdateSuccessMessage'), 15)
	}

	@And("Update preferences to default settings")
	public void updatePreferencesToDefaultSettings() {
		WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/sessionTimeOut_Textbox'), '20')

		WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/pageSize_TextBox'), '10')

		WebUI.selectOptionByLabel(findTestObject('Suite_Module/UserPreferences_Page/defaultLanguage_DropDown'), 'English' , true)

		WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat1_DropDown'), 'MM', true)
		WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat2_DropDown'), 'DD', true)
		WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat3_DropDown'), 'YYYY', true)
		WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/save_Button'))

		WebUI.verifyElementPresent(findTestObject('Suite_Module/UserPreferences_Page/PreferencesSettingUpdateSuccessMessage'), 15)
	}


	@Then('Validate language')
	public void validateLanguage() {
		WebUI.verifyElementPresent(findTestObject('Home_Page/ProductsIconText_LangValidation'), 10)
		KeywordUtil.logInfo("Language updated properly..! --> PASS")
	}
}