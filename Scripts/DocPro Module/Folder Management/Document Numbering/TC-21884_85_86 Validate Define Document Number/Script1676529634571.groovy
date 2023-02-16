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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By

'Login to the application'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'DocProAdmin', 'QJblfja5Cso=')

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Making the Level In Use'
CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Scrolling to "Document Number Option" Filed\r\n'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 15)

Result = WebUI.getText(findTestObject('DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))

'Validate User Defined Doc Number is the Default option set in Doc Number option\r\n\r\n'
WebUI.verifyMatch(Result, 'User Defined Document Number', false)

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

WebUI.delay(3)

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Getting Doc number inuput field value\r\n'
DocNum = WebUI.getAttribute(findTestObject('DocPro_Module/New Documnet Request/Document Number Value'), 'value')

'Doc Number input field is empty '
WebUI.verifyMatch(DocNum, '', false)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Scrolling to "Document Number Option" Filed\r\n'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 15)

'Clicking the Doc Num Options drop down\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))

'Assigning valu Document Number Auto Increment'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), DocNumOption + 
    Keys.ENTER)

'Click Save button to save the new changes\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Getting Doc number inuput field value\r\n'
DocNum = WebUI.getAttribute(findTestObject('DocPro_Module/New Documnet Request/Document Number Value'), 'value')

'Validate Inherit From parent is the Default option set in Route Link\r\n'
WebUI.verifyMatch(DocNum, '1', false)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Scrolling to "Document Number Option" Filed\r\n'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 15)

'Clicking the Doc Num Options drop down\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))

'Assigning valu for "Doc Num Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), 'Use Internal Document ID As Document Number' + 
    Keys.ENTER)

WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Getting Doc number inuput field value\r\n'
Status = WebUI.getAttribute(findTestObject('DocPro_Module/New Documnet Request/Document Number Value'), 'readonly')

'Validate the number should be automatically generated and can\'t able to edit'
WebUI.verifyMatch(Status, 'true', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

