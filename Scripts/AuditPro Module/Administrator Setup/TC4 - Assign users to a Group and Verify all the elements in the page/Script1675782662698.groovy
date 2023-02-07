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
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

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

'Create a new group with random name and prefix as -> Test Automation'
CustomKeywords.'suiteModule.GroupPage.createNewGroup'('random', 'Audits')

'Search for the new group created'
CustomKeywords.'suiteModule.GroupPage.searchGroup'('Test Automation')

'Click on the User Selection Icon'
WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

'Wait for the Search Icon to load in the Assign users page'
WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

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

'Verify Pagination is working in Assign Users page'
CustomKeywords.'suiteModule.GroupPage.verifyPagination'()

'Enter the input in Search text box'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-QA-AutoUser')

'Wait for 3 seconds\r\n'
WebUI.delay(3)

'Click on the User checkbox for selection'
WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

'Click on the Entity based Search radio button'
WebUI.click(findTestObject('Groups_Page/entityBasedSearch_RadioButton'))

'Wait for the Entity Search Tree to be shown'
WebUI.waitForElementPresent(findTestObject('Groups_Page/entitySearch_Tree'), 5)

'Verify Entity Search Tree element is present in Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/entitySearch_Tree'), 5)

'Verify Entity Search Tree List is present in Assign Users page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/entitySearch_TreeList'), 5)

'Click on Corporate site in Entitry Tree List'
WebUI.click(findTestObject('Groups_Page/entitySearchCorporate_Option'))

'Verify pagination is working in Entity Based Saerch page'
CustomKeywords.'suiteModule.GroupPage.verifyPagination'()

'Enter the user name to be selected and assigned'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-QA-AutoUser')

'Wait for 3 seconds'
WebUI.delay(3)

'Click on User Selection checkbox'
WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

'Click on Save button'
WebUI.click(findTestObject('Groups_Page/userSave_Button'))

'Verify Success message is displayed properly'
WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 0)

'Close the success message displayed'
WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

'Wait for 3 seconds'
WebUI.delay(3)

'Click on View users Icon in Groups page'
WebUI.click(findTestObject('Groups_Page/viewUsers_Icon'))

'Wait for 3 seconds'
WebUI.delay(3)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Code'), 5)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Entity'), 5)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Name'), 5)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_SearchIcon'), 5)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_RefreshIcon'), 5)

'Verify View User Code Header is diplayed'
WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_RemoveButton'), 5)

'Get the text of of the User Name from the first row'
String Name = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUserFirstRow_Name'))

'Verify the text of the User name from retireved from the previous step'
WebUI.verifyMatch(Name, 'SCB-QA-AutoUser', false)

'Click the Close button'
WebUI.click(findTestObject('Groups_Page/viewUsers_CloseButton'))

'Delete the group created in the test case'
CustomKeywords.'suiteModule.GroupPage.deleteTheGroup'('Audits')

