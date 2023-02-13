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

'Login to the EWQIMS application\r\n'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'DocAccess', 'QJblfja5Cso=')

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select any level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', 'bin')

'validate we can able to view level by validating the Level Heading '
WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Folder Management/Folger Management level Heading'), 5)

WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/Document Pro Heading'), 5)

