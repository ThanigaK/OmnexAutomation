package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.TimeUnit
import java.util.logging.Logger

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
class UserCreation_Page extends CommonClass{

	public void clickOnAddUserBtn() {
		WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 30)
		Thread.sleep(3000);
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
	}

	public void validateSuccessMessage() {
		try {
			WebUI.scrollToPosition(0, 0)
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
			KeywordUtil.logInfo("User creation is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User creation is failed.. ! Success message is not displayed.. !")
		}
	}

	@And("Create user")
	public void createUser() {
		clickOnAddUserBtn();
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/code_Inuput'), code)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/firstName_Input'), getData("First name"))
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/lastName_Input'), getData("Last name"))
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/email_Input'), email)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/username_Input'), userName)
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserCreation_Page/password_Input'), getData("Password"))
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserCreation_Page/confirmPassword_Input'), getData("Confirm password"))

		if(getData("ITAR").equalsIgnoreCase("yes")) {
			WebUI.scrollToElement(findTestObject('Suite_Module/UserCreation_Page/ITAR_CheckBox'), 10)
			switchFrameAndDoActions("ITARCheckBox", "//div/div/input[@id='ChangeItar']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		} else {
			KeywordUtil.logInfo("ITAR is not required..!")
		}

		if(getData("Change password").equalsIgnoreCase("no")) {
			WebUI.scrollToElement(findTestObject('Suite_Module/UserCreation_Page/changePassword_Checkbox'), 10)
			switchFrameAndDoActions("ITARCheckBox", "//div/div/input[@id='Changelogin']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		} else {
			KeywordUtil.logInfo("Change password on next logon checkbox is already checked..!")
		}

		WebUI.click(findTestObject('Suite_Module/UserCreation_Page/save_Button'))

		validateSuccessMessage();
	}
}