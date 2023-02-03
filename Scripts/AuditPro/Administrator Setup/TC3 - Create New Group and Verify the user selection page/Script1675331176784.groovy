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

WebUI.click(findTestObject('Home_Page/groups_Option'))

'Click on Add Group Button'
WebUI.click(findTestObject('Groups_Page/addGroup_Button'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/addTitle_Text'), 10)

AddHeading = WebUI.getText(findTestObject('Groups_Page/addTitle_Text'))

WebUI.verifyMatch(AddHeading, 'Add', false)

WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_TextBox'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/saveGroup_Button'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/cancel_Button'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/close_Button'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/action_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/users_Header'), 10)

CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSelection_Icon'), 10)

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.delay(10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/entityBasedSearch_Option'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userBasedSearch_Option'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userRefresh_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Filter'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userName_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userCode_header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userEmail_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userPosition_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userAccessRights_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userAuditee_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userAuditor_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userReports_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userAdmin_Checkbox'), 10)

WebUI.click(findTestObject('Groups_Page/userAdmin_Checkbox'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSave_Button'), 10)

WebUI.click(findTestObject('Groups_Page/userSave_Button'))

WebUI.click(findTestObject('Groups_Page/userRightsClose_Button'))

CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

