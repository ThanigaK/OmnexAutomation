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

CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'DocProAdmin', 'QJblfja5Cso=')

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', 'LevelWithDocument')

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), 'LevelWithDocument' + Keys.ENTER)

WebUI.delay(3)

'Right click on the Level\r\n'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

WebUI.click(findTestObject('DocPro_Module/Folder Management/Rights for Sites'))

WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Plus icon'))

WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Click Link'))

Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Level Rights/Manage site document access header'))

WebUI.verifyMatch(Heading, 'Manage Site Document Access - LevelWithDocument', false)

WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Plus icon'))

WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Full Access Checkbox'))

WebUI.delay(3)

Status = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/Level Rights/Full Access Checkbox'), 'value')

WebUI.verifyMatch(Status, 'true', false)

