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
import org.junit.Assert as Assert
import org.openqa.selenium.Keys as Keys
import org.testng.Assert as Keys

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Audits option in the left pane\r\n'
WebUI.click(findTestObject('Object Repository/Home_Page/auditsMenu_Option'))

'Verify Scheduler Option is present inside Audits menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/scheduler_Option'), 10)

'Click on the Scheduler option to expand'
WebUI.click(findTestObject('Object Repository/Home_Page/scheduler_Option'))

'Verify Actions menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/actions_Option'), 10)

'Verify LPA Schedule menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/LPASchedule_Option'), 10)

'Verify Audit Schedule menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditSchedule_Option'), 10)

'Verify Templates menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/templates_Option'), 10)

'Verify Month View menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/monthView_Option'), 10)

'Verify Audit Calendar menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditCalendar_Option'), 10)

'Verify Audit Status menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditStatus_Option'), 10)

'Verify Corrective Action Status menu item is present inside Scheduler option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/correctiveActionStatus_Option'), 10)

'Verify Auditor option is present inside Audits menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditor_Option'), 10)

'Click on the Auditor option to expand'
WebUI.click(findTestObject('Object Repository/Home_Page/auditor_Option'))

'Verify Perform Audit menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/performAudit_Option'), 10)

'Verify Audit Schedule menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditor_AuditSchedule'), 10)

'Verify Audit Approval menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditApproval_Option'), 10)

'Verify Audit Calendar menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditorAuditCalendar_Option'), 10)

'Verify Audit Status menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditorAuditStatus_Option'), 10)

'Verify Corrective Action Status menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditorCorrectiveActionStatus_Option'), 10)

'Verify Auditee option is present inside Audits menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditee_Option'), 10)

'Click on the Auditee option to expand'
WebUI.click(findTestObject('Object Repository/Home_Page/auditee_Option'))

'Verify Cause menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeCause_Option'), 10)

'Verify Sub Cause menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeSubCause_Option'), 10)

'Verify Corrective Actions menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditee_CorrectiveActions_Option'), 10)

'Verify Supplier Self Evaluation menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeSupplierSelfEvaluation_Option'), 10)

'Verify Audit Calendar menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditCalendar_Option'), 10)

'Verify Audit Status menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeAuditStatus_Option'), 10)

'Verify Corrective Action Status menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeCorrectiveActionStatus_Option'), 10)

'Logout of the application'
CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'auditor', '5xx1bkCcAlw=')

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Audits option in the left pane\r\n'
WebUI.click(findTestObject('Object Repository/Home_Page/auditsMenu_Option'))

'Verify Auditor option is present inside Audits menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditor_Option'), 0)

'Click on the Auditor option to expand'
WebUI.click(findTestObject('Object Repository/Home_Page/auditor_Option'))

'Verify Perform Audit menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/performAudit_Option'), 10)

'Verify Audit Schedule menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Home_Page/auditSchedule_Option'), 10)

'Verify Audit Approval menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditApproval_Option'), 10)

'Verify Audit Calendar menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Home_Page/auditCalendar_Option'), 10)

'Verify Audit Status menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Home_Page/auditStatus_Option'), 10)

'Verify Corrective Action Status menu item is present inside Auditor option'
WebUI.verifyElementPresent(findTestObject('Home_Page/correctiveActionStatus_Option'), 10)

'Logout of the application'
CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'auditee', '5xx1bkCcAlw=')

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Audits option in the left pane\r\n'
WebUI.click(findTestObject('Object Repository/Home_Page/auditsMenu_Option'))

'Verify Auditee option is present inside Audits menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditee_Option'), 10)

'Click on the Auditee option to expand'
WebUI.click(findTestObject('Object Repository/Home_Page/auditee_Option'))

'Verify Cause menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeCause_Option'), 10)

'Verify Sub Cause menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeSubCause_Option'), 10)

'Verify Corrective Actions menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditee_CorrectiveActions_Option'), 10)

'Verify Supplier Self Evaluation menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditeeSupplierSelfEvaluation_Option'), 10)

'Verify Audit Calendar menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/auditCalendar_Option'), 10)

'Verify Audit Status menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Home_Page/auditStatus_Option'), 10)

'Verify Corrective Action Status menu item is present inside Auditee option'
WebUI.verifyElementPresent(findTestObject('Home_Page/correctiveActionStatus_Option'), 10)

