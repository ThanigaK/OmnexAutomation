import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the application\r\n'
CustomKeywords.'ewqims.KeyWord.Login'()

'Navigating to the Administration setting\r\n'
CustomKeywords.'ewqims.KeyWord.NavigateToAdminSettingPage'()

'Modify and update the Admin settings'
CustomKeywords.'ewqims.KeyWord.updateAdminSettingPage'(PasswordLenth, LastName, MiddleName, FirstName, Empty1)

'Logout from the current user\r\n'
CustomKeywords.'ewqims.KeyWord.Logout'()

'Enter new user name'
WebUI.setText(findTestObject('Login_Page/userName_Input'), 'TestAutomation')

'Enter new password '
WebUI.setText(findTestObject('Login_Page/password_Input'), 'a1234')

'Login with the new user\r\n'
WebUI.click(findTestObject('Login_Page/login_Button'))

'Go to Profile'
WebUI.click(findTestObject('Object Repository/Home_Page/userProfile_Icon'))

'Get profile name'
String txt = WebUI.getText(findTestObject('Object Repository/Home_Page/userName_Text'))

'Validate the name updated as per the above modification made in admin setting'
if (txt.equals('Automation-Test')) {
    WebUI.click(findTestObject('Home_Page/logout_Button'))

    WebUI.click(findTestObject('Home_Page/popupOk_Button'))

    WebUI.closeBrowser()
}

'Login to the application\r\n'
CustomKeywords.'ewqims.KeyWord.Login'()

'Navigating to the Administration setting\r\n'
CustomKeywords.'ewqims.KeyWord.NavigateToAdminSettingPage'()

'Reset the admin setting'
CustomKeywords.'ewqims.KeyWord.updateAdminSettingPage'(PasswordLenth, FirstName, MiddleName, LastName, Empty1)

'Close the browser\r\n'
WebUI.closeBrowser()

