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

'Login to application with the Global Variables'
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Navigating to levels page to create new level'
CustomKeywords.'docPro.KeyWord.NavigateToLevelsPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'docPro.LevelsPage.createLevel'(LevelName)

//'Going to Doc pro setup page to make the level IN Use and Update it\r\n'
//CustomKeywords.'docPro.HomePage.NavigateToDocProSetupPage'()
if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 0)

WebUI.click(findTestObject('Object Repository/Home_Page/docProSetup_Option'))

'Select the Required Level in the Folder management'
CustomKeywords.'docPro.DocPro.goToLevelInDocproSetup'('Folder management', levName)

'Make the Level In Use'
CustomKeywords.'docPro.DocPro.MakeLevelInUse'()

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
CustomKeywords.'docPro.DocPro.AddLevelOwner'('Empcode', LevelOwner)

'Selecting tag'
CustomKeywords.'docPro.DocPro.tagSelection'('')

'Select "Records" Checkbox if need'
if (data.equals('yes')) {
    WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'), 15)

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'))

    KeywordUtil.logInfo('Records checkbox clicked.')
}

'Assigning Assign By Admin Route option\r\n'
CustomKeywords.'docPro.DocPro.AssignAutoApprovalRoute'('No', 'No', 'Assign by admin')

'Assigning Level PDF Preferences if need'
CustomKeywords.'docPro.DocPro.levelpdfPrefSelection'('Document Type', LevelPDFPreference)

'Setting Auto Publish value as Required\r\n'
CustomKeywords.'docPro.DocPro.setAutoPublish'('100', Module)

'Hitting Save button to update the Level'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

KeywordUtil.logInfo('Save button clicked..!')

'Navigating to "New Doc Request" Page '
CustomKeywords.'docPro.HomePage.NavigateToNewDocRequestPage'()

'Click the Site DropDown'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/site_DropDown'))

'Select new Site'
WebUI.setText(findTestObject('Object Repository/Suite_Module/City_Page/searchCountryState_TextBox'), siteName + Keys.ENTER)

KeywordUtil.logInfo('Site selected successfully --> ' + siteName)

'Select "Documnet Level"'
CustomKeywords.'docPro.NewDocRequest.levelSelection'(levName)

'Entering Document Number'
String DocumentNum = CustomKeywords.'docPro.DocPro.EnterDocumentNumber'(DocNumber)

'Entering Document Name'
CustomKeywords.'docPro.DocPro.EnterDocName'(DocName)

'Enter Revision Number'
CustomKeywords.'docPro.DocPro.enterRevisionNum'(Revison)

'Upload the New Document'
CustomKeywords.'docPro.DocPro.uploadFile'(FilePath)

WebUI.delay(6)

'Click the Add Button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/addDocument_Button'))

WebUI.delay(6)

'Navigatin to administration Action Page'
CustomKeywords.'docPro.Documents.NavigateToAdminActionsPage'()

'Assign Module Auto Approval Route'
CustomKeywords.'docPro.Documents.assignRouteForCreatedRequest'('Module Auto approval')

CustomKeywords.'docPro.Documents.NavigateToActionsPage'()

WebUI.click(findTestObject('DocPro_Module/Actions_Page/pending_Request_button'))

WebUI.click(findTestObject('DocPro_Module/Actions_Page/searchInPendingRequest'), FailureHandling.STOP_ON_FAILURE)

'Terminate the document wiht the help of Document Number to make the Test Scenario ready for next cycle'
WebUI.setText(findTestObject('DocPro_Module/Actions_Page/searchInPendingRequest'), DocumentNum)

WebUI.delay(3)

WebUI.click(findTestObject('DocPro_Module/Actions_Page/InProcess'))

WebUI.delay(3)

WebUI.click(findTestObject('DocPro_Module/Actions_Page/Terminate'))

WebUI.delay(3)

WebUI.click(findTestObject('DocPro_Module/Actions_Page/YesButton'))

WebUI.delay(3)

//'Going to Doc pro setup page\r\n'
//not_run: CustomKeywords.'docPro.HomePage.NavigateToDocProSetupPage'()
//
//'Select the Required Level in the Folder management'
//not_run: CustomKeywords.'docPro.DocPro.goToLevelInDocproSetup'('Document management', levName)
//
//not_run: CustomKeywords.'docPro.DocPro.moveAllAvailableFiles'('bin')
'Navigating to the levels page'
CustomKeywords.'docPro.KeyWord.NavigateToLevelsPage'()

'Delete the Newely created level'
CustomKeywords.'docPro.LevelsPage.levelDeletion'(levName)

