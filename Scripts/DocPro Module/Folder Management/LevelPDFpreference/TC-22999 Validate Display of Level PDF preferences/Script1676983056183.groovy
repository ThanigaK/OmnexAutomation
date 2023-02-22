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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the EWQIMS application\r\n'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'suiteModule.LevelsPage.createLevelwithSubLevelAccess'(LevelName)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Making the Level In Use'
CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Scroll to get Level PDF Preference Link\r\n'
WebUI.scrollToElement(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'), 10)

'Verify Level PDF preference present or not\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'), 5)

'Select the Level PDF Preference Link\r\n'
WebUI.click(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'))

'Get the Heading text in the newely opened window'
Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Heading'))

'Validate the Heading is "Level PDF Preferences"'
WebUI.verifyMatch(Heading, 'Level PDF Preferences', false)

'Verify Template DropDown Presence\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/TemplateDropDown'), 5)

'Verify Restricted View Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/RestrictedView'), 5)

'Verify input template Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/InputTemplate'), 5)

'Verify Enable Enhanced Pricing Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/EnableEnhancedPrinting'), 5)

'Verify Level Refresh button Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFRestart'), 5)

'Verify Level Save Button Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFSave'), 5)

'Verify Use Site Default Button Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFUseSiteDefault'), 5)

'Verify Apply to All Sub Level Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFApplyToAllSubLevels'), 5)

'Verify Show Sample PDF Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFShowSamplePDF'), 5)

'Verify Search Dropdown Presence'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFSearch'), 5)

'Verify Module Name in search option'
WebUI.verifyOptionPresentByValue(findTestObject('DocPro_Module/LevelPDF/LevelPDFSearch'), 'modname', false, 3)

'Verify Document Type in Search DropDown'
WebUI.verifyOptionPresentByValue(findTestObject('DocPro_Module/LevelPDF/LevelPDFSearch'), 'doctype', false, 3)

'Verify Pagination in the bottom of the page'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/LevelPDFpagination'), 5)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

