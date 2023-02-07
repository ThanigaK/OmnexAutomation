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

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform\r\n'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Groups option\r\n'
WebUI.click(findTestObject('Home_Page/groups_Option'))

'Create a new group with random name and prefix as -> Test Automation\r\n'
CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

'Search for the new group created\r\n'
CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

'Click on Assign user icon in Groups page\r\n'
WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

'Wait for the Search Icon to load in the Assign users page'
WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

'Enter the input in Search text box\r\n'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-QA-AutoUser')

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Get the text of of the User Name from the first row\r\n'
String Name = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Name'))

'Verify the text of the User name from retireved from the previous step\r\n'
WebUI.verifyMatch(Name, 'SCB-QA-AutoUser', false)

'Select the Search dropdown with Code option'
WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Code', false)

'Enter the search text in input box'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'QA002')

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Get the text of User Code \r\n'
String Code = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Code'))

'Verify the text of the User code from retireved from the previous step\r\n'
WebUI.verifyMatch(Code, 'QA002', false)

'Select the Search dropdown for Email option'
WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Email', false)

'Enter the search text in input box'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'scb@omnex.com')

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Get the text of User Email\r\n'
String Email = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Email'))

'Verify the text of the User email from retireved from the previous step\r\n'
WebUI.verifyMatch(Email, 'scb@omnex.com', false)

'Select the Search dropdown Position option'
WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Position', false)

'Enter the search text in input box'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'User')

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Get the text of user position\r\n'
String Position = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Position'))

'Verify the text of the User position from retireved from the previous step\r\n'
WebUI.verifyMatch(Position, 'User', false)

'Click on the Close button'
WebUI.click(findTestObject('Groups_Page/userRightsClose_Button'))

'Delete the group created in the test case\r\n'
CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

