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

'Launching Driver'
CustomKeywords.'suiteModule.KeyWord.newBrowser'('1')

'Entering Username'
WebUI.setText(findTestObject('Login_Page/userName_Input'), 'donotdelete')

'Entering Password'
WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), Password)

'Enable Rememeber me Checkbox'
not_run: WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))

'Hit login button'
WebUI.click(findTestObject('Login_Page/login_Button'))

'Launch new browser'
CustomKeywords.'suiteModule.KeyWord.newBrowser'('2')

'Enter username'
WebUI.setText(findTestObject('Login_Page/userName_Input'), GlobalVariable.username)

'Enter Password'
WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), GlobalVariable.Password)

'Select Rememeber me option'
not_run: WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))

'Hit login buton'
WebUI.click(findTestObject('Login_Page/login_Button'))

'Navigating to the unlock user page'
CustomKeywords.'suiteModule.HomePage.NavigateToUnlockUserPage'()

'Unlock the user'
CustomKeywords.'suiteModule.UserPage.unlockUser'()

'Validate the user unlocked'
not_run: CustomKeywords.'suiteModule.KeyWord.validateUserUnlocked'()

