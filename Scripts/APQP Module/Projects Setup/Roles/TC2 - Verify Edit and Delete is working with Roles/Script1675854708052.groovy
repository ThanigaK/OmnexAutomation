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

'Mouse over the select checkbox of role'
WebUI.mouseOver(findTestObject('ProjectSetup_Page/RolesSelectButton'))

'Click on the edit role button'
WebUI.click(findTestObject('ProjectSetup_Page/EditButton'))

'Verify whether Role Name Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 10)

'Verify whether Role Description Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RoleDescription_TextBox'), 10)

'Verify whether Role Level Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RoleLevel_TextBox'), 10)

'Verify whether User Selection Icon is present in Add Roles Table'
not_run: WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/UserSelectionIcon'), 10)

'Chenge the name of role created'
WebUI.setText(findTestObject('ProjectSetup_Page/RoleName_TextBox'), 'edited')

'Click on Save Button to save the changes'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Mouse over the select checkbox of role'
WebUI.mouseOver(findTestObject('ProjectSetup_Page/RolesSelectButton'))

'Click on the edit role button'
WebUI.click(findTestObject('ProjectSetup_Page/EditButton'))

'Wait for 3 seconds for the page to load'
WebUI.delay(3, FailureHandling.STOP_ON_FAILURE)

'Click on user Selection Icon to select the users for the role'
WebUI.click(findTestObject('ProjectSetup_Page/UserSelectionIcon_Edit'))

'Wait for 5 seconds for the page to load'
WebUI.delay(5, FailureHandling.STOP_ON_FAILURE)

'Enter the input user to be selected '
WebUI.setText(findTestObject('ProjectSetup_Page/UserNameTextBox_Search'), 'apqp')

'Wait for 3 seconds for the page to load'
WebUI.delay(3, FailureHandling.STOP_ON_FAILURE)

'Select the first row user name after search'
WebUI.click(findTestObject('ProjectSetup_Page/FirstRowUserName'))

'Click on Done button to Confirm the Selected User'
WebUI.click(findTestObject('ProjectSetup_Page/DoneButton'))

'Click on Save Button to save the changes'
WebUI.click(findTestObject('ProjectSetup_Page/SaveRole_Button'))

'Verify whether Role Level Text Box is present in Add Roles Table'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SuccessAlert'), 10)

'Get the text of role created success alert message'
String successtext = WebUI.getText(findTestObject('ProjectSetup_Page/SuccessAlert'))

'Verify the success text with the expected text'
Assert.assertTrue(successtext.contains('Saved Successfully'))

'Wait for a second for the page to load'
WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

'Mouse over the select checkbox of role'
WebUI.mouseOver(findTestObject('ProjectSetup_Page/RolesSelectButton'))

'Click on the Delete button to delete the role'
WebUI.click(findTestObject('ProjectSetup_Page/DeleteButtonRow'))

'Click on Ok for the confirmation pop up asked'
WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))

