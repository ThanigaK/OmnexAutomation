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

'Clicking Revision Option drop down'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))

'assigning value for "Revision Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), RevOption + 
    Keys.ENTER)

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

InputValue = WebUI.getAttribute(findTestObject('DocPro_Module/Documents_Page/Revision Value'), 'value')

WebUI.verifyMatch(InputValue, '1', false)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Clicking Revision Option drop down'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))

'assigning value for "Revision Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), 'Users define revision' + 
    Keys.ENTER)

'Click Save button to save the new changes\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

Value = WebUI.getAttribute(findTestObject('DocPro_Module/Documents_Page/Revision Value'), 'value')

WebUI.verifyMatch(Value, '', false)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Clicking Revision Option drop down'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))

'assigning value for "Revision Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), 'Custom' + Keys.ENTER)

WebUI.delay(3)

WebUI.selectOptionByIndex(findTestObject('DocPro_Module/Folder Management/NumberOfSegmentsDrpDwn'), '1', FailureHandling.STOP_ON_FAILURE)

WebUI.selectOptionByIndex(findTestObject('DocPro_Module/Folder Management/NumberOfSegmentMajor'), '1', FailureHandling.STOP_ON_FAILURE)

WebUI.selectOptionByIndex(findTestObject('DocPro_Module/Folder Management/NumberOfSegmentsMinor'), '2', FailureHandling.STOP_ON_FAILURE)

'Click Save button to save the new changes\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

Value = WebUI.getAttribute(findTestObject('DocPro_Module/Documents_Page/Revision Value'), 'value')

WebUI.verifyMatch(Value, 'A.a', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

