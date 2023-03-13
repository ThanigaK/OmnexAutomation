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
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the User page'
CustomKeywords.'suiteModule.HomePage.NavigateToUsersPage'()

'Create new user'
CustomKeywords.'suiteModule.UserPage.createUser'(Code, FirstName, LastName, Email, UserName, Password, ITAR, ChangePassword)

'Logging out'
CustomKeywords.'suiteModule.KeyWord.Logout'()

'Change the password from Forgot password link'
NewUser = CustomKeywords.'suiteModule.UserPage.ChangeForgotPassword'(NewPassword)

'Enter New User Name'
WebUI.setText(findTestObject('Login_Page/userName_Input'), NewUser)

'Enter New Password'
WebUI.setText(findTestObject('Login_Page/password_Input'), NewPassword)

'Click Login button'
WebUI.click(findTestObject('Login_Page/login_Button'))

KeywordUtil.logInfo('Login Successfully.. !')

'Logging out'
CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login back with the Old Username and password'
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigate back to the user page'
CustomKeywords.'suiteModule.HomePage.NavigateToUsersPage'()

'Validate the newely created user'
CustomKeywords.'suiteModule.UserPage.searchAndValidateUser'()

'Delete user'
CustomKeywords.'suiteModule.UserPage.deleteUser'()

