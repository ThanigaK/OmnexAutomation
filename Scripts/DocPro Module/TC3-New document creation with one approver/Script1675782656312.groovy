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

'Login to application with the Credentials'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

CustomKeywords.'suiteModule.HomePage.goToDocumentRoutePage'()

CustomKeywords.'suiteModule.RouteCreation.createNewRoute'(RouteName, RouteCode)

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/levels_Page'), 15)

WebUI.click(findTestObject('Object Repository/Home_Page/levels_Page'))

levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 0)

WebUI.click(findTestObject('Object Repository/Home_Page/docProSetup_Option'))

'Select the Required Level in the Folder management'
CustomKeywords.'suiteModule.DocPro.goToLevelInDocproSetup'('Folder management', levName)

CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Setting Day/Month value for "Documents Reviewed After"'
WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocReviewUnitName_DropDown'), 
    'Month', false)

'Setting Count value for "Documents Reviewed After"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtDocReviewUnit_TextBox'), '2')

'Clicking Revision Option drop down'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))

'assigning value for "Revision Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), RevOption + 
    Keys.ENTER)

'Scrolling to "Document Number Option" Filed\r\n'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 15)

'Clicking the Doc Num Options drop down\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))

'Assigning valu for "Doc Num Option"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), DocNumOption + 
    Keys.ENTER)

'Scrolling to "Revision Date Option" field'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'), 15)

'Clicking the Revision Date Options drop down\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'))

'Assigning value for Revision Date option\r\n'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), revDateOpt + 
    Keys.ENTER)

'Adding Level Owner'
CustomKeywords.'suiteModule.DocPro.AddLevelOwner'('Empcode', LevelOwner)

'Selecting tag'
CustomKeywords.'suiteModule.DocPro.tagSelection'('')

'Select "Records" Checkbox if need'
if (data.equals('yes')) {
    WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'), 15)

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'))

    KeywordUtil.logInfo('Records checkbox clicked.')
}

CustomKeywords.'suiteModule.RouteCreation.AssignRoute'('No', 'No')

'Assigning Level PDF Preferences if need'
CustomKeywords.'suiteModule.DocPro.levelpdfPrefSelection'('Document Type', LevelPDFPreference)

CustomKeywords.'suiteModule.DocPro.setAutoPublish'('100', Module)

WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

KeywordUtil.logInfo('Save button clicked..!')

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Click the Site DropDown'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/site_DropDown'))

'Select new Site'
WebUI.setText(findTestObject('Object Repository/Suite_Module/City_Page/searchCountryState_TextBox'), siteName + Keys.ENTER)

KeywordUtil.logInfo('Site selected successfully --> ' + siteName)

'Select "Documnet Level"'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Enter Document Number'
CustomKeywords.'suiteModule.DocPro.EnterDocNumber'(DocNumber)

'Entering Document Name'
CustomKeywords.'suiteModule.DocPro.EnterDocName'(DocName)

'Enter Revision Number'
CustomKeywords.'suiteModule.DocPro.enterRevisionNum'(Revison)

'Upload the New Document'
CustomKeywords.'suiteModule.DocPro.uploadFile'(FilePath)

WebUI.delay(6)

'Click the Add Button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/addDocument_Button'))

WebUI.delay(6)

CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to application with the Credentials'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'donotdelete2', 'TtfzLQ/s9dQ=')

CustomKeywords.'suiteModule.Documents.NavigateToActionsPage'()

CustomKeywords.'suiteModule.DocPro.approveOrrejectRequestInRequestNeedingApproval'('Approve', 'TtfzLQ/s9dQ=')

'Going to Doc pro setup page\r\n'
CustomKeywords.'suiteModule.HomePage.NavigateToDocProSetupPage'()

'Select the Required Level in the Folder management'
CustomKeywords.'suiteModule.DocPro.goToLevelInDocproSetup'('Document management', levName)

CustomKeywords.'suiteModule.DocPro.moveAllAvailableFiles'('bin')

CustomKeywords.'suiteModule.KeyWord.NavigateToLevelsPage'()

CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

CustomKeywords.'suiteModule.HomePage.goToDocumentRoutePage'()

CustomKeywords.'suiteModule.RouteCreation.deleteRoute'()

