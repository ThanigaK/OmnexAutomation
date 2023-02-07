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

'Click on the Home logo to select the platform'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Groups option'
WebUI.click(findTestObject('Home_Page/groups_Option'))

'Click on Add Group Button'
WebUI.click(findTestObject('Groups_Page/addGroup_Button'))

'Verify Add Title in Add Group popup box'
WebUI.verifyElementPresent(findTestObject('Groups_Page/addTitle_Text'), 10)

'Get the text of Add Group popup box'
AddHeading = WebUI.getText(findTestObject('Groups_Page/addTitle_Text'))

'Verify the text of the title in Add Group box'
WebUI.verifyMatch(AddHeading, 'Add', false)

'Verify Group Name text box is present in Add Group box'
WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_TextBox'), 10)

'Verify Save Group button is present in Add Group box'
WebUI.verifyElementPresent(findTestObject('Groups_Page/saveGroup_Button'), 10)

'Verify Cancel button is present in Add Group box'
WebUI.verifyElementPresent(findTestObject('Groups_Page/cancel_Button'), 10)

'Verify Close button is present in Add Group box'
WebUI.verifyElementPresent(findTestObject('Groups_Page/close_Button'), 10)

'Verify Action Header is present in the Groups page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/action_Header'), 10)

'Verify Group Name Header is present in the Groups page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_Header'), 10)

'Verify Users Header is present in the Groups page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/users_Header'), 10)

'Create a new group with random name and prefix as -> Test Automation'
CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

'Search for the new group created'
CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

'Verify Assign Users Icon is present in the Groups page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userSelection_Icon'), 10)

'Click on Assign user icon in Groups page'
WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

'Wait for 10 seconds for the page to load'
WebUI.delay(10)

'Verify Select Users text is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

'Verify Entity Based Search option is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/entityBasedSearch_Option'), 10)

'Verify User Based Search option is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userBasedSearch_Option'), 10)

'Verify User Search Icon is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

'Verify User Refresh Icon is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userRefresh_Icon'), 10)

'Verify Search Filter is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Filter'), 10)

'Verify User Name Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userName_Header'), 10)

'Verify User Code Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userCode_header'), 10)

'Verify User Email Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userEmail_Header'), 10)

'Verify User Position Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userPosition_Header'), 10)

'Verify User Access Rights Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userAccessRights_Header'), 10)

'Verify User Auditee Rights Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userAuditee_Header'), 10)

'Verify User Auditor Rights Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userAuditor_Header'), 10)

'Verify User Reports Rights Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userReports_Header'), 10)

'Verify User Admin Rights Header is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userAdmin_Checkbox'), 10)

'Click on User Admin Rights Checkbox'
WebUI.click(findTestObject('Groups_Page/userAdmin_Checkbox'))

'Verify Save button is present in the Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/userSave_Button'), 10)

'Click on Save button in the Assign Users page'
WebUI.click(findTestObject('Groups_Page/userSave_Button'))

'Click on close button in the Assign Users page'
WebUI.click(findTestObject('Groups_Page/userRightsClose_Button'))

'Delete the group created in the test case'
CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

