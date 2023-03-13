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
import junit.framework.Assert as Assert
import org.openqa.selenium.Keys as Keys

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform\r\n'
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

'Enter the input in Search text box\r\n'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB')

'Wait for 3 seconds\r\n'
WebUI.delay(3)

'Click on User Selection checkbox\r\n'
WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

'Click on Save button\r\n'
WebUI.click(findTestObject('Groups_Page/userSave_Button'))

'Verify Success message is displayed properly\r\n'
WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 0)

'Wait for 3 seconds\r\n'
WebUI.delay(3)

'Close the success message displayed\r\n'
WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

'Click on View users Icon in Groups page\r\n'
WebUI.click(findTestObject('Groups_Page/viewUsers_Icon'))

'Click on Remove Button to remove the user\r\n'
WebUI.click(findTestObject('Groups_Page/viewUser_RemoveButton'))

'Get the text of the alert message \r\n'
String alert = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUser_AlertMessage'))

'Verify the alert message text with the text captured from previous step'
Assert.assertTrue(alert.contains('Please Select the User(s)'))

'Click the Close button\r\n'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_AlertClose'))

'Click on the Remove User Checkbox'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserCheckBox'))

'Click on the Remove button'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveButton'))

'Click on Ok for the Remove popup'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserPopupOk'))

'Get the text of the alert message \r\n'
String alertsuccess = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserSuccessMessage'))

'Verify the alert message text with the text captured from previous step'
Assert.assertTrue(alertsuccess.contains('Removed Successfully'))

'Click the Close button\r\n'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserSuccessMessageClose'))

'Click the Close button\r\n'
WebUI.click(findTestObject('Object Repository/Groups_Page/viewUsers_CloseButton'))

'Modify the group name created and verify'
CustomKeywords.'suiteModule.GroupPage.modifyTheGroupName'('Audits')

'Delete the group created in the test case\r\n'
CustomKeywords.'suiteModule.GroupPage.deleteTheGroup'('Audits')

