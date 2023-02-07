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

WebUI.click(findTestObject('Home_Page/homelogo'))

WebUI.click(findTestObject('Home_Page/platform_Option1'))

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

WebUI.click(findTestObject('Home_Page/groups_Option'))

CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.delay(3)

//------------------------------------------------------------------------------------------------------------//
String adminStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AdminTable'), 'style')

Assert.assertEquals(adminStyle, '')

WebUI.click(findTestObject('Groups_Page/accessRights_Admin_ExpandIcon'))

WebUI.delay(1)

adminStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AdminTable'), 'style')

Assert.assertEquals(adminStyle, 'display: none;')

//------------------------------------------------------------------------------------------------------------//
String auditeeStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditeeTable'), 'style')

Assert.assertEquals(auditeeStyle, 'display: none;')

WebUI.click(findTestObject('Groups_Page/accessRights_Auditee_ExpandIcon'))

WebUI.delay(1)

auditeeStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditeeTable'), 'style')

Assert.assertEquals(auditeeStyle, '')

//------------------------------------------------------------------------------------------------------------//
String auditorStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditorTable'), 'style')

Assert.assertEquals(auditorStyle, 'display: none;')

WebUI.click(findTestObject('Groups_Page/accessRights_Auditor_ExpandIcon'))

WebUI.delay(1)

auditorStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_AuditorTable'), 'style')

Assert.assertEquals(auditorStyle, '')

//------------------------------------------------------------------------------------------------------------//
String reportsStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_ReportsTable'), 'style')

Assert.assertEquals(reportsStyle, 'display: none;')

WebUI.click(findTestObject('Groups_Page/accessRights_Reports_ExpandIcon'))

WebUI.delay(1)

reportsStyle = WebUI.getAttribute(findTestObject('Object Repository/Groups_Page/accessRights_ReportsTable'), 'style')

Assert.assertEquals(reportsStyle, '')

