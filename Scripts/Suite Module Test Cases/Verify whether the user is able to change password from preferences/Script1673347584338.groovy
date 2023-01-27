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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the application'
CustomKeywords.'ewqims.KeyWord.Login'()

'Navigate to the user page'
CustomKeywords.'ewqims.HomePage.NavigateToUsersPage'()

'Create new user'
NewUser = CustomKeywords.'ewqims.UserPage.createUser'(Code, FirstName, LastName, Email, UserName, Password, ITAR, ChangePassword)

'Logout from the application'
CustomKeywords.'ewqims.KeyWord.Logout'()

'Enter New username'
WebUI.setText(findTestObject('Login_Page/userName_Input'), NewUser)

'Enter new password'
WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), Password)

'Click login button'
WebUI.click(findTestObject('Login_Page/login_Button'))

'Navigating to the preference page'
CustomKeywords.'ewqims.HomePage.NavigateToPreferencesPage'()

'Select the change password tab'
WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/changePasswordT_Tab'))

'Enter old password'
WebUI.setEncryptedText(findTestObject('Suite_Module/UserPreferences_Page/password_TextBox'), Password)

'Set new password'
WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/newPassword_TextBox'), NewPassword)

'set new password'
WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/newConfirmPassword_TextBox'), NewPassword)

'hit Save password button'
WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/saveInChangePass_Button'))

'Verify success message'
WebUI.verifyElementPresent(findTestObject('Suite_Module/UserPreferences_Page/successMessageInChangePassword'), 10)

'Logout from the application'
CustomKeywords.'ewqims.KeyWord.Logout'()

'Login back to the application'
CustomKeywords.'ewqims.KeyWord.Login'()

'Navigate back to the user page'
CustomKeywords.'ewqims.HomePage.NavigateToUsersPage'()

'Search and validate user'
CustomKeywords.'ewqims.UserPage.searchAndValidateUser'()

'Delete the user'
CustomKeywords.'ewqims.UserPage.deleteUser'()

