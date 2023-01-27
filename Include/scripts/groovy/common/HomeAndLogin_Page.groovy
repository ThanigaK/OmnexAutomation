package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.VerifyElementVisibleKeyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.postgresql.Driver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
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
import groovy.json.internal.Exceptions

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * @author sibis
 *
 */
class HomeAndLogin_Page extends CommonClass {

	public void validateNameFormat(String name){
		WebUI.click(findTestObject('Object Repository/Home_Page/userProfile_Icon'))
		String txt = WebUI.getText(findTestObject('Object Repository/Home_Page/userName_Text'))
		if(txt.equals(name)) {
			KeywordUtil.logInfo("Name format matched..!")
			WebUI.click(findTestObject('Object Repository/Home_Page/userProfile_Icon'))
		} else {
			KeywordUtil.markFailedAndStop("Name format is not matched..! Actual is --> " +txt)
		}
	}

	public void changePasswordOnForgotPassword(String userName,String empCode,String mailId,String newPassword,String confirmNewPass) {
		WebUI.click(findTestObject('Object Repository/Login_Page/forgotPassword_Link'))
		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotUserName_TextBox'), userName)
		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotEmpCode_TextBox'), empCode)
		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotEmail_TextBox'), mailId)
		WebUI.click(findTestObject('Object Repository/Login_Page/validateUser_Button'))
		WebUI.setEncryptedText(findTestObject("Object Repository/Login_Page/forgotNewPassword_TextBox"), newPassword)
		WebUI.setEncryptedText(findTestObject("Object Repository/Login_Page/forgotConfirmNewPassword_TextBox"), confirmNewPass)
		WebUI.click(findTestObject('Object Repository/Login_Page/forgotPassSubmit_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/passwordChangedSuccessMessage'), 20)
		KeywordUtil.logInfo("Password changed successfully..! Success message verified..!")
	}

	public ChromeDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
		return new ChromeDriver();
	}

	@Given("Launch browser")
	public void launchBrowser() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		KeywordUtil.logInfo("Browser launched..!")
	}

	@And("Launch application")
	public void launchApplication() {
		WebUI.navigateToUrl(getPropertyValue("Global", "globalURL"))
	}

	@And("New browser (.*)")
	public void newBrowser(String count) {
		if(count.equals("1")) {
			driver1 = openChromeBrowser()
			DriverFactory.changeWebDriver(driver1)
		} else if(count.equals("2")){
			driver2 = openChromeBrowser()
			DriverFactory.changeWebDriver(driver2)
		}
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(getPropertyValue("Global", "globalURL"))
	}

	@And("Login to the application")
	public void loginToTheApplication() {
		WebUI.setText(findTestObject('Login_Page/userName_Input'), getData("Login username"))
		WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), getData("Login password"))
		WebUI.click(findTestObject('Login_Page/login_Button'))
		KeywordUtil.logInfo("Login Successfully.. !")
	}

	@And("Login to the application with (.*) and (.*)")
	public void loginToTheApplicationWithUsernameAndPassword(String user, String password) {
		WebUI.setText(findTestObject('Login_Page/userName_Input'), userName)
		WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), getData(password))
		WebUI.click(findTestObject('Login_Page/login_Button'))
		KeywordUtil.logInfo("Login Successfully.. !")
	}

	@And("Login to the application with remember me option")
	public void loginToTheApplicationWithRememberMeOption() {
		WebUI.setText(findTestObject('Login_Page/userName_Input'), getData("Login username"))
		WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), getData("Login password"))
		WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))
		WebUI.click(findTestObject('Login_Page/login_Button'))
		KeywordUtil.logInfo("Login Successfully.. !")
	}

	@And("Validate remember me option")
	public void validateRememberMeOption() {
		WebUI.setText(findTestObject('Login_Page/userName_Input'), getData("Login username")+Keys.TAB)
		WebUI.click(findTestObject('Login_Page/login_Button'))
		if(WebUI.getWindowTitle().equals("EwQIMS")){
			KeywordUtil.logInfo("Remember me option validated successfully..!")
		} else {
			throw new Exception("Remember me option not working properly..--> FAILED")
		}
	}

	@And("Logout to the application")
	public void logoutToTheApplication() {
		waitForClickableAndClick(findTestObject('Home_Page/userProfile_Icon'),20)
		/*WebUI.waitForElementClickable(findTestObject('Home_Page/userProfile_Icon'),20);
		 WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//li[@id='profiledrop']/a/img"));
		 JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getWebDriver();
		 executor.executeScript("arguments[0].click();", element);*/
		WebUI.enhancedClick(findTestObject('Home_Page/logout_Button'))
		WebUI.click(findTestObject('Home_Page/popupOk_Button'))
		KeywordUtil.logInfo("Logout Successfully.. !")
	}

	@When("Go to users page")
	public void goToUsersPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/usersChild_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/usersChild_OptionIcon'), 15)
	}

	@And("Go to Restore user page")
	public void goToRestoreUsersPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}

		WebUI.scrollToElement(findTestObject('Home_Page/restoreUser_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/restoreUser_Option'), 15)
	}

	@And("Go to Preferences page")
	public void goToPreferencesPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}

		WebUI.scrollToElement(findTestObject('Home_Page/usersPreferences_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/usersPreferences_Option'), 15)
	}

	@And("Validate timezone under profile icon")
	public void validateTimezoneUnderProfileIcon() {
		WebUI.refresh();
		WebUI.click(findTestObject('Home_Page/userProfile_Icon'))
		String time = WebUI.getText(findTestObject('Home_Page/time_Text'))
		compareDateWithCurrentDate(time);
	}

	@And("Change Password in preferences page")
	public void changePassowordInPreferencesPage() {
		WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/changePasswordT_Tab'))
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserPreferences_Page/password_TextBox'), getData("Password"))
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserPreferences_Page/newPassword_TextBox'), getData("New password"))
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserPreferences_Page/newConfirmPassword_TextBox'), getData("Confirm new password"))
		WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/saveInChangePass_Button'))
		WebUI.verifyElementPresent(findTestObject('Suite_Module/UserPreferences_Page/successMessageInChangePassword'), 10)
	}

	@And("Go to City page")
	public void goToCityPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/city_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/city_Option'), 15)
	}

	@And("Go to State page")
	public void goToStatePage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/state_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/state_Option'), 15)
	}

	@And("Go to Positions page")
	public void goToPositionsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/position_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/position_Option'), 15)
	}

	@And("Go to Country page")
	public void goToCountryPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/country_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/country_Option'), 15)
	}

	@When("Go to Labels page")
	public void goToLabelsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/labels_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/labels_Option'), 15)
	}

	@When("Go to Groups page")
	public void goToGroupsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/groups_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/groups_Option'), 15)
	}

	@When("Go to Modules page")
	public void goToModulesPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Home_Page/Modules_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/Modules_Option'), 15)
	}

	@When("Go to Manufacturer page")
	public void goToManufacturerPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/manufacturer_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/manufacturer_Option'), 15)
	}

	@When("Go to Customers page")
	public void goToCustomererPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/customers_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/customers_Option'), 15)
	}

	@When("Go to Shift page")
	public void goToShiftPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/shift_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/shift_Option'), 15)
	}

	@When("Go to Meetings page")
	public void goToMeetingsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/meetings_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/meetings_Option'), 15)
	}

	@When("Go to Entity page")
	public void goToEntityPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/entity_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/entity_Option'), 15)
	}

	@When("Go to Entity Owner page")
	public void goToEntityOwnerPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*	if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/entityOwner_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/entityOwner_Option'), 15)
	}

	@When("Go to Vendor page")
	public void goToVendorPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/vendor_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/vendor_Option'), 15)
	}

	@When("Go to Supplier page")
	public void goToSupplierPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/supplier_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/supplier_Option'), 15)
	}

	@When("Go to Organization Profile page")
	public void goToOrganizationProfilePage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/organizationProfile_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/organizationProfile_Option'), 15)
	}

	@When("Go to Admin settings page")
	public void goToAdminSettingsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/adminSettings_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/adminSettings_Option'), 15)
	}

	@When("Change password in Forgot password")
	public void changePasswordInForgotPassword() {
		changePasswordOnForgotPassword(userName, code, email, getData("New password"),getData("Confirm new password"))
	}

	@Then("Validate the forgot password")
	public void validateTheForgotPassword() {
		loginToTheApplicationWithUsernameAndPassword(userName, "New password")
		KeywordUtil.logInfo("Succesfully logged in with new password..!")
	}

	@When("Go to Teams page")
	public void goToTeamsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/teams_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/teams_Option'), 15)
	}

	@When("Go to Unlock user page")
	public void goToUnlockUserPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}

		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/unlockUser_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/unlockUser_Option'), 15)
	}

	@When("Go to Levels page")
	public void goToLevelsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
	}

	@When("Go to Document Route page")
	public void goToDocumentRoutePage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		/*if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/generalSetpSubMenu_DropDown'), "class").contains("active")) {
		 WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'), 15)
		 WebUI.click(findTestObject('Object Repository/Home_Page/generalSetUp_DropDown'))
		 }*/
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/documentRoute_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documentRoute_Option'), 15)
	}

	@When("Go to DocPro setup page")
	public void goToDocProSetupPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		//WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
	}

	@When("Go to New Doc Request page")
	public void goToNewDocRequestPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/newDocRequest_Option'), 15)
	}

	@When("Go to Documents Page")
	public void goToDocumentsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documents_Option'), 15)
	}

	@When("Go to Tags page")
	public void goToTagsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/tags_Option'), 15)
	}

	@When("Go to Actions page")
	public void goToActionsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/actions_Option'), 15)
	}

	@When("Go to Administrator Actions page")
	public void goToAdminActionsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/administratorActions_Option'), 15)
	}

	@When("Go to Favourites page")
	public void goToFavouritesPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/favourite_Option'), 15)
	}
}