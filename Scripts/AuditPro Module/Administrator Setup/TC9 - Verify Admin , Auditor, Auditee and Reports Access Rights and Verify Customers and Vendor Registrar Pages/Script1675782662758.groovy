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

'Click on the Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Groups option\r\n'
WebUI.click(findTestObject('Home_Page/groups_Option'))

'Create a new group with random name and prefix as -> Test Automation\r\n'
CustomKeywords.'suiteModule.GroupPage.createNewGroup'('random', 'Audits')

'Search for the new group created\r\n'
CustomKeywords.'suiteModule.GroupPage.searchGroup'('Test Automation')

'Click on the User Selection Icon\r\n'
WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

'Wait for 3 seconds\r\n'
WebUI.delay(3)

//------------------------------------------------------------------------------------------------------------//
'Get the text of the Style Attribute in Admin Table\r\n'
String adminStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AdminTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(adminStyle, '')

'Click on the Admin Expand Icon'
WebUI.click(findTestObject('Groups_Page/accessRights_Admin_ExpandIcon'))

'Wait for a second\r\n'
WebUI.delay(1)

'Get the text of the Style Attribute in Admin Table\r\n'
adminStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AdminTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(adminStyle, 'display: none;')

//------------------------------------------------------------------------------------------------------------//
'Get the text of the Style Attribute in Auditee Table\r\n'
String auditeeStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditeeTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(auditeeStyle, 'display: none;')

'Click on the Auditee Expand Icon'
WebUI.click(findTestObject('Groups_Page/accessRights_Auditee_ExpandIcon'))

'Wait for a second\r\n'
WebUI.delay(1)

'Get the text of the Style Attribute in Auditee Table\r\n'
auditeeStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditeeTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(auditeeStyle, '')

//------------------------------------------------------------------------------------------------------------//
'Get the text of the Style Attribute in Auditor Table\r\n'
String auditorStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditorTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(auditorStyle, 'display: none;')

'Click on the Auditor Expand Icon'
WebUI.click(findTestObject('Groups_Page/accessRights_Auditor_ExpandIcon'))

'Wait for a second\r\n'
WebUI.delay(1)

'Get the text of the Style Attribute in Auditor Table\r\n'
auditorStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditorTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(auditorStyle, '')

//------------------------------------------------------------------------------------------------------------//
'Get the text of the Style Attribute in Reports Table\r\n'
String reportsStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_ReportsTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(reportsStyle, 'display: none;')

'Click on the Reports Expand Icon'
WebUI.click(findTestObject('Groups_Page/accessRights_Reports_ExpandIcon'))

'Wait for a second\r\n'
WebUI.delay(1)

'Get the text of the Style Attribute in Reports Table\r\n'
reportsStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_ReportsTable'), 'style')

'Verify the text with the text captured from previous step\r\n'
Assert.assertEquals(reportsStyle, '')

