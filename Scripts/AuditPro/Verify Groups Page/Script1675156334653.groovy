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

'Get the text of Module'
auditText = WebUI.getText(findTestObject('Groups_Page/audits_text'))

WebUI.verifyMatch(auditText, 'Audits', false)

WebUI.verifyElementPresent(findTestObject('Groups_Page/site_DropDown'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/addGroup_Button'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/search_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/refresh_Icon'), 10)

WebUI.click(findTestObject('Groups_Page/search_Icon'))

WebUI.selectOptionByValue(findTestObject('Groups_Page/searchColumn_DropDown'), '2', false)

WebUI.click(findTestObject('Groups_Page/searchButton'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_Dropdown'), 10)

WebUI.click(findTestObject('Groups_Page/siteDropDown_span'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/site_DropDownList'), 10)
