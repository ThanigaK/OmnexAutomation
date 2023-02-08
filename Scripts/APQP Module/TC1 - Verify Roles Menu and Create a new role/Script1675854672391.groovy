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

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'apqp', '5xx1bkCcAlw=')

'Click on the Home logo to select the platform'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the EVAV NPD/APQP Platform Platform'
WebUI.click(findTestObject('Home_Page/EVAV_Platform'))

'Click on the Setup option in the left menu'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'), FailureHandling.STOP_ON_FAILURE)

'Click on the Suite Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/projectsSetup_Option'))

WebUI.waitForPageLoad(5)

WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RolesOption'), 10)

WebUI.click(findTestObject('ProjectSetup_Page/RolesOption'))

WebUI.click(findTestObject('ProjectSetup_Page/AddButton'))

WebUI.setText(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 'AutoRole')

WebUI.setText(findTestObject('ProjectSetup_Page/RoleDescription_TextBox'), 'new')

WebUI.setText(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), '1')

WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

