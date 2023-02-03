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

WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

WebUI.click(findTestObject('Home_Page/processActivities_Option'))

WebUI.waitForElementPresent(findTestObject('ProcessActivities_Page/proccessScope_Link'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/proccessScope_Link'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/addProccessScope_Button'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/search_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/refresh_Icon'), 10)

WebUI.click(findTestObject('ProcessActivities_Page/selectQAScope_link'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processName_Header'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processScope_Header'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processName_Header'), 10)

WebUI.verifyElementPresent(findTestObject('ProcessActivities_Page/processName_Header'), 10)

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/entityBasedSearch_Option'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userBasedSearch_Option'), 10)

