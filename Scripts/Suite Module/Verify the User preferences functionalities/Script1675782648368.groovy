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
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the user page\r\n'
CustomKeywords.'suiteModule.HomePage.NavigateToUsersPage'()

'Create new user'
NewUser = CustomKeywords.'suiteModule.UserPage.createUserReturnUserName'(Code, FirstName, LastName, Email, UserName, Password, 
    ITAR, ChangePassword)

'Logout from the application'
CustomKeywords.'suiteModule.KeyWord.Logout'()

'Enter new user name'
WebUI.setText(findTestObject('Login_Page/userName_Input'), NewUser)

'Enter new pasword'
WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), Password)

'Click login button'
WebUI.click(findTestObject('Login_Page/login_Button'))

'Navigating to the preference page'
CustomKeywords.'suiteModule.HomePage.NavigateToPreferencesPage'()

'Update preference based on the excel data\r\n'
CustomKeywords.'suiteModule.PreferencePage.updateThePreferencesSettingsBasedOnExcelData'(SessionTimeout, PageSize, DefaultLanguage, 
    DateFormat)

'Validate timezone under the profile icon'
CustomKeywords.'suiteModule.PreferencePage.validateTimezoneUnderProfileIcon'(DateFormat)

'Logout button'
WebUI.click(findTestObject('Home_Page/logout_Button'))

'Accept Pop ok button'
WebUI.click(findTestObject('Home_Page/popupOk_Button'))

'Login back to the application'
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating back to the user page'
CustomKeywords.'suiteModule.HomePage.NavigateToUsersPage'()

'Validate the modified user'
CustomKeywords.'suiteModule.UserPage.searchAndValidateUser'()

'Delete the user'
CustomKeywords.'suiteModule.UserPage.deleteUser'()

