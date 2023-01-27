package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.TimeUnit

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
import common.HomeAndLogin_Page
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
class AdminSettings_Page extends CommonClass {

	public void adminSettingsUpdation(String format,String length, String name1,String name2,String name3,String name4,String name5) {
		DriverFactory.getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/passwordFormat_ComboBox'))
		switchFrameAndDoActions("format", "//li[text()='"+format+"']", "click",findTestObject('Home_Page/detailView_iFrame'))


		WebUI.setText(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/passwordLength_TextBox'), length+Keys.ENTER)

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/first_ComboBox'),20)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), name1+Keys.ENTER)

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/second_ComboBox'),20)
		if(name2.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/emptySelection_ComboBox'))
		} else {
			WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), name2+Keys.ENTER)
		}

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/third_ComboBox'),20)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), name3+Keys.ENTER)

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/fourth_ComboBox'),20)
		if(name4.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/emptySelection_ComboBox'))
		} else {
			WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), name4+Keys.ENTER)
		}

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/fifth_ComboBox'),20)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), name5+Keys.ENTER)

		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Admin settings updated..! Success message verified..!")
	}


	@And("Update the Admin setting based on excel data")
	public void updateTheAdminSettingBasedOnExcelData() {
		String data = getData("Name format")
		String[] name = data.split("\\|");
		adminSettingsUpdation(getData("Password format"),"3",name[0],name[1],name[2],name[3],name[4])
	}
	@And("Validate the changes")
	public void validateTheChanges() {
		new HomeAndLogin_Page().goToUsersPage()
		new UserCreation_Page().createUser()
		new HomeAndLogin_Page().logoutToTheApplication()
		new HomeAndLogin_Page().loginToTheApplicationWithUsernameAndPassword(userName, "Password")
		KeywordUtil.logInfo("Login verification done with updated Admin settings")
		new HomeAndLogin_Page().validateNameFormat(getData("First name")+"- -"+getData("Last name"))
	}

	@And("Update admin settings with default values")
	public void updateAdminSettingsWithDefaultValues() {
		new HomeAndLogin_Page().logoutToTheApplication()
		new HomeAndLogin_Page().loginToTheApplication()
		new HomeAndLogin_Page().goToAdminSettingsPage()
		adminSettingsUpdation("Alphanumeric","1","Last Name","","Middle Name","","First Name")
	}
}