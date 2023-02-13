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

'Wait for 5 seconds for the page to load'
WebUI.waitForPageLoad(5)

'Verify whether Roles option is present in Projects Setup page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RolesOption'), 10)

'Click on Roles option in Projects Setup menu'
WebUI.click(findTestObject('ProjectSetup_Page/RolesOption'))

'Click on Add button to add a new role'
WebUI.click(findTestObject('ProjectSetup_Page/AddButton'))

'Click on Save button to Save the role'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether warning alert message is shown'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/WarningAlert'), 10)

'Get the text of role created warning alert message'
String warningText = WebUI.getText(findTestObject('ProjectSetup_Page/WarningAlert'))

'Verify the warning text for Name field is required error'
Assert.assertTrue(warningText.contains('Name is required'))

'Enter the input for Role Name Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 'AutoRole2' + CustomKeywords.'apqpModule.Roles.RandomNumber'())

'Click on Save button to Save the role'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether warning alert message is shown'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/WarningAlert'), 10)

'Get the text of role created warning alert message'
warningText = WebUI.getText(findTestObject('ProjectSetup_Page/WarningAlert'))

'Verify the warning text for level field is required error'
Assert.assertTrue(warningText.contains('Please enter a Integer value'))

'Enter the input for Role Level Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), 'a')

'Verify whether warning alert message is shown'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/WarningAlert'), 10)

'Get the text of role created warning alert message'
warningText = WebUI.getText(findTestObject('ProjectSetup_Page/WarningAlert'))

'Verify the warning text for level field is required error'
Assert.assertTrue(warningText.contains('You can enter only number for Level'))

'Enter the input for Role Description Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleDescription_TextBox'), 'Role2')

'Enter the input for Role Level Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), '1')

'Click on Save button to Save the role'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether Role Level Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SuccessAlert'), 10)

'Wait for a second for the page to load'
WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

'Click on Add button to add a new role'
WebUI.click(findTestObject('ProjectSetup_Page/AddButton'))

'Enter the input for Role Name Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 'AutoRole3' + CustomKeywords.'apqpModule.Roles.RandomNumber'())

'Enter the input for Role Description Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleDescription_TextBox'), 'Role3')

'Enter the input for Role Level Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), '1')

'Click on Save button to Save the role'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether Role Level Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SuccessAlert'), 10)

'Wait for a second for the page to load'
WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

'Click on Add button to add a new role'
WebUI.click(findTestObject('ProjectSetup_Page/AddButton'))

'Enter the input for Role Name Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 'AutoRole4' + CustomKeywords.'apqpModule.Roles.RandomNumber'())

'Enter the input for Role Description Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleDescription_TextBox'), 'Role4')

'Enter the input for Role Level Textbox'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), '1')

'Click on Save button to Save the role'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether Role Level Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SuccessAlert'), 10)

'Wait for a second for the page to load'
WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

'CLick on the Select All Checkbox to select ll roles created'
WebUI.click(findTestObject('ProjectSetup_Page/SelectAllCheckbox'))

'Click on Delete Button to delete all selected roles in the page'
WebUI.click(findTestObject('ProjectSetup_Page/DeleteButton'))

'Click on Ok for the confirmation pop up asked'
WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))

'Get the text of first row of the table'
String datatext = WebUI.getText(findTestObject('ProjectSetup_Page/NoDataRow'))

'Verify the text with the mentioned text'
WebUI.verifyMatch(datatext, 'No Data Found', false)

