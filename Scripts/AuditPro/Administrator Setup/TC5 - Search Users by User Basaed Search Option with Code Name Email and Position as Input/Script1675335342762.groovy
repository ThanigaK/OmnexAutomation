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

CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-AutoUser')

WebUI.delay(3)

String Name = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Name'))

WebUI.verifyMatch(Name, 'SCB-AutoUser', false)

WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Code', false)

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB001')

WebUI.delay(3)

String Code = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Code'))

WebUI.verifyMatch(Code, 'SCB001', false)

WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Email', false)

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'scb@omnex.com')

WebUI.delay(3)

String Email = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Email'))

WebUI.verifyMatch(Email, 'scb@omnex.com', false)

WebUI.selectOptionByLabel(findTestObject('Groups_Page/userSearch_Dropdown'), 'Position', false)

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'User')

WebUI.delay(3)

String Position = WebUI.getText(findTestObject('Object Repository/Groups_Page/userFirstRow_Position'))

WebUI.verifyMatch(Position, 'User', false)

WebUI.click(findTestObject('Groups_Page/userRightsClose_Button'))

CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

