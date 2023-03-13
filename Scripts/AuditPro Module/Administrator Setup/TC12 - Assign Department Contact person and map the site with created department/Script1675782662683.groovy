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

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform\r\n'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Assign Department option'
WebUI.click(findTestObject('Home_Page/assignDepartment_Option'))

'Click on the Is A Site Dropdown'
WebUI.click(findTestObject('Department_Page/isASite_Dropdown'))

'Select Yes option to Is A Site dropdown'
WebUI.click(findTestObject('Department_Page/isASiteYes_Option'))

'Click on Search Button in the bottom of the page'
WebUI.click(findTestObject('Department_Page/searchAssignDepartment_Button'))

'Click on the checkbox next to the site that you want to assign users'
WebUI.click(findTestObject('Department_Page/selectDepartment_CheckBox'))

'Right click on the checkbox selected'
WebUI.rightClick(findTestObject('Department_Page/selectDepartment_CheckBox'))

'Click on the Select Users button'
WebUI.click(findTestObject('Department_Page/selectUser_Button'))

'Wait for 2 seconds for the page to load\r\n'
WebUI.delay(2)

'Enter the user name in search text box'
WebUI.setText(findTestObject('Department_Page/searchNameInput_Textbox'), 'SCB-QA-AutoUser')

'Wait for 2 seconds for the page to load\r\n'
WebUI.delay(2)

'Click the checkbox of the user searched'
WebUI.click(findTestObject('Department_Page/selectUser_CheckBox'), FailureHandling.STOP_ON_FAILURE)

'Click the Done button'
WebUI.click(findTestObject('Department_Page/selectUserDone_Button'))

'Wait for 2 seconds for the page to load\r\n'
WebUI.delay(2)

'Get the text of the Employee Code'
String employeeCode = WebUI.getText(findTestObject('Object Repository/Department_Page/employeeCode_Verify'))

'Verify the Employee code captured'
WebUI.verifyMatch(employeeCode, 'QA002', false)

'Get the text of the Full Name'
String fullName = WebUI.getText(findTestObject('Object Repository/Department_Page/fullName_Verify'))

'Verify the Full Name captured'
WebUI.verifyMatch(fullName, 'SCB-QA-AutoUser', false)

'Get the text of the Employee Email'
String email = WebUI.getText(findTestObject('Object Repository/Department_Page/email_Verify'))

'Verify the Employee Email captured'
WebUI.verifyMatch(email, 'scb@omnex.com', false)

'Click on the user checkbox added'
WebUI.click(findTestObject('Department_Page/departmentSelect_CheckBox'))

'Click on the Delete Users button to remove the user'
WebUI.click(findTestObject('Department_Page/deleteDepartmentFinal_Button'))

'Click on OK for the confirmation popup shown'
WebUI.click(findTestObject('Department_Page/deleteConfirmaton_Popup'))

'Wait for 2 seconds for the page to load\r\n'
WebUI.delay(2)

'Verify the Alert message displayed for removing the user'
WebUI.verifyElementPresent(findTestObject('Department_Page/alertDeletedMessage'), 5)

'Close the alert message displayed'
WebUI.click(findTestObject('Department_Page/alertDeletedMessage_Close'))

'Close the Department Contact Person page '
WebUI.click(findTestObject('Department_Page/departmentContactPerson_Close'))

'Click on the site selected previuosly at the starting the test case'
WebUI.click(findTestObject('Department_Page/selectDepartment_CheckBox'))

'Click ok and remove the site from getting selected'
WebUI.click(findTestObject('Department_Page/deleteConfirmaton_Popup'))

