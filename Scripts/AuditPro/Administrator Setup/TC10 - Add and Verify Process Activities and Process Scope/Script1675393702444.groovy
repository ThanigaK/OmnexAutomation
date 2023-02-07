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
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform\r\n'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Cllick on Process/Activities Option'
WebUI.click(findTestObject('Home_Page/processActivities_Option'))

'Wait for the Process Scope Link'
WebUI.waitForElementPresent(findTestObject('ProcessActivities_Page/proccessScope_Link'), 10)

'Verify Process Scopr link is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/proccessScope_Link'), 10)

'Verify Add processScope button is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/addProccessScope_Button'), 10)

'Verify Search Icon is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/search_Icon'), 10)

'Verify Refresh Icon is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/refresh_Icon'), 10)

'Select on the first scope displayed'
WebUI.click(findTestObject('ProcessActivities_Page/selectQAScope_link'))

'Wait for the Process Name Header to get displayed'
WebUI.waitForElementPresent(findTestObject('ProcessActivities_Page/processName_Header'), 5)

'Verify Proces Name Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processName_Header'), 10)

'Verify Proces Scope Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processScope_Header'), 10)

'Verify Status Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/status_Header'), 10)

'Verify Proces Owner Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/globalProcessOwner_Header'), 10)

'Verify Entity Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/entity_Header'), 10)

'Verify Clauses Name Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/clauses_Header'), 10)

'Verify AuditForm Name Header is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/auditForm_Header'), 10)

'Verify New Process Scope Name Button is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/newProcessScope_Button'), 10)

'Verify Delete Process Scope Name Button is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/deleteProcessScope_Button'), 10)

'Verify Copy Process Scope Name Button is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/copyProcessScope_Button'), 10)

'Verify Edit Process Scope Name Button is displayed in the page'
WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/editProcessScope_Button'), 10)

'Navigate back to the Process Activites page'
WebUI.back()

'Click on the Add Process Scope button'
WebUI.click(findTestObject('ProcessActivities_Page/addProccessScope_Button'))

'Wait for the page to load'
WebUI.waitForPageLoad(5)

'Create a new Process Scopr and verify the same'
CustomKeywords.'auditPro.ProcessActivities.createNewProcessScopeandVerify'('SCBAuto')

'Delete the Process Scope created above'
CustomKeywords.'auditPro.ProcessActivities.deleteProcessScope'('')


