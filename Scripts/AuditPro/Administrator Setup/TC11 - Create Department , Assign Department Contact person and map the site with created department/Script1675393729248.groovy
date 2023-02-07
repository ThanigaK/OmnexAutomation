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
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail
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

'Click on Create Department Option'
WebUI.click(findTestObject('Home_Page/createDepartment_Option'))

'Wait for the Add Department Button to be present'
WebUI.waitForElementPresent(findTestObject('Department_Page/addDepartment_Button'), 10)

'Click on the Add Department Button'
WebUI.click(findTestObject('Department_Page/addDepartment_Button'))

'Wait for the Department Text box to be present'
WebUI.waitForElementPresent(findTestObject('Department_Page/department_TextBox'), 10)

'Enter the input in Department Name text box'
WebUI.setText(findTestObject('Department_Page/department_TextBox'), 'SCB_Auto')

'Enter the input in Department Code text box'
WebUI.setText(findTestObject('Department_Page/departmentCode_TextBox'), 'QA2000')

'Enter the input in Department Description text box'
WebUI.setText(findTestObject('Department_Page/departmentDesription_TextBox'), 'QA Test')

'Click on the Save button'
WebUI.click(findTestObject('Department_Page/saveDepartment_Button'))

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Verify the alert success message element is displayed'
WebUI.verifyElementPresent(findTestObject('Department_Page/successMessage_Text'), 5)

'Get the text of alert success message'
String successMessage = WebUI.getText(findTestObject('Department_Page/successMessage_Text'))

'Verify the alert success message text captured'
assertTrue(successMessage.contains('Saved successfully'))

'Close the alert success message'
WebUI.click(findTestObject('Department_Page/successMessage_Close'))

'Wait for 2 seconds for the page to load\r\n'
WebUI.delay(2)

'Verify the Department Name Created is displayed properly'
WebUI.verifyElementPresent(findTestObject('Department_Page/departmentName_Text'), 5)

'Verify the Department Code Created is displayed properly'
WebUI.verifyElementPresent(findTestObject('Department_Page/departmentCode_Text'), 5)

'Verify the Department Description Created is displayed properly'
WebUI.verifyElementPresent(findTestObject('Department_Page/departmentDescription_Text'), 5)

'Mouse over the Department checkbox'
WebUI.mouseOver(findTestObject('Department_Page/SCBDepartment_Checkbox'))

'Click on the Department checkbox '
WebUI.click(findTestObject('Department_Page/SCBDepartment_Checkbox'))

'Click on the delete button of the Department'
WebUI.click(findTestObject('Object Repository/Department_Page/deleteDepartment_Button'))

'Click Ok to the Confirmation popup shown'
WebUI.click(findTestObject('Object Repository/Department_Page/deleteConfirmaton_Popup'))

'Get the text of the Delete message alert'
String deleteMessage = WebUI.getText(findTestObject('Department_Page/successMessage_Text'))

'Verify the delete message text captured'
assertTrue(deleteMessage.contains('Deleted successfully'))

