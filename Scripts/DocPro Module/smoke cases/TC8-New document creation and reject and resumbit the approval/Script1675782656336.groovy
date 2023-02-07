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
not_run: CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

not_run: CustomKeywords.'suiteModule.HomePage.goToDocumentRoutePage'()

not_run: CustomKeywords.'suiteModule.RouteCreation.createNewRoute'(RouteName, RouteCode)

not_run: CustomKeywords.'suiteModule.KeyWord.NavigateToLevelsPage'()

not_run: levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

'Going to Doc pro setup page\r\n'
not_run: CustomKeywords.'suiteModule.HomePage.NavigateToDocProSetupPage'()

'Select the Required Level in the Folder management'
not_run: CustomKeywords.'suiteModule.DocPro.goToLevelInDocproSetup'('Folder management', levName)

not_run: CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Setting Day/Month value for "Documents Reviewed After"'
not_run: WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocReviewUnitName_DropDown'), 
    'Day', false)

'Setting Count value for "Documents Reviewed After"'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtDocReviewUnit_TextBox'), '1')

'Clicking Revision Option drop down'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))

'assigning value for "Revision Option"'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), RevOption + 
    Keys.ENTER)

'Scrolling to "Document Number Option" Filed\r\n'
not_run: WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 
    15)

'Clicking the Doc Num Options drop down\r\n'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))

'Assigning valu for "Doc Num Option"'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), DocNumOption + 
    Keys.ENTER)

'Scrolling to "Revision Date Option" field'
not_run: WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'), 
    15)

'Clicking the Revision Date Options drop down\r\n'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'))

'Assigning value for Revision Date option\r\n'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), revDateOpt + 
    Keys.ENTER)

'Adding Level Owner'
not_run: CustomKeywords.'suiteModule.DocPro.AddLevelOwner'('Empcode', LevelOwner)

'Selecting tag'
not_run: CustomKeywords.'suiteModule.DocPro.tagSelection'('')

'Select "Records" Checkbox if need'
not_run: if (data.equals('yes')) {
    WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'), 15)

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'))

    KeywordUtil.logInfo('Records checkbox clicked.')
}

not_run: CustomKeywords.'suiteModule.RouteCreation.AssignRoute'('No', 'No')

'Assigning Level PDF Preferences if need'
not_run: CustomKeywords.'suiteModule.DocPro.levelpdfPrefSelection'('Document Type', LevelPDFPreference)

not_run: CustomKeywords.'suiteModule.DocPro.setAutoPublish'('100', Module)

not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

not_run: KeywordUtil.logInfo('Save button clicked..!')

'Navigating to "New Doc Request" Page '
not_run: CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Click the Site DropDown'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/site_DropDown'))

'Select new Site'
not_run: WebUI.setText(findTestObject('Object Repository/Suite_Module/City_Page/searchCountryState_TextBox'), siteName + 
    Keys.ENTER)

not_run: KeywordUtil.logInfo('Site selected successfully --> ' + siteName)

'Select "Documnet Level"'
not_run: CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Enter Document Number'
not_run: CustomKeywords.'suiteModule.DocPro.EnterDocNumber'(DocNumber)

'Entering Document Name'
not_run: CustomKeywords.'suiteModule.DocPro.EnterDocName'(DocName)

'Enter Revision Number'
not_run: CustomKeywords.'suiteModule.DocPro.enterRevisionNum'(Revison)

not_run: WebUI.delay(10)

'Upload the New Document'
not_run: CustomKeywords.'suiteModule.DocPro.uploadFile'(FilePath)

not_run: WebUI.delay(15)

'Click the Add Button'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/addDocument_Button'))

not_run: WebUI.delay(20)

not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to application with the Credentials'
not_run: CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'donotdelete2', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.Documents.NavigateToActionsPage'()

'Rejecting the Document'
not_run: CustomKeywords.'suiteModule.DocPro.approveOrrejectRequestInRequestNeedingApproval'('Reject', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to application with the Credentials\r\n'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

CustomKeywords.'suiteModule.Documents.NavigateToActionsPage'()

'Re Submitting the Document\r\n'
CustomKeywords.'suiteModule.Documents.resubmitTheRejectedRequestInResubmitRejectedRequest'('D:\\\\Omnex\\\\Repo\\\\OmnexAutomation\\\\Files')

not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to application with the Credentials'
not_run: CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'donotdelete2', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.Documents.NavigateToActionsPage'()

'ReAssigning Approval'
not_run: CustomKeywords.'suiteModule.Documents.assignUserInReassignApproval'('doNotDelete_01', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to application with the Credentials'
not_run: CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'doNotDelete_01', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.Documents.NavigateToActionsPage'()

'Approving the Document'
not_run: CustomKeywords.'suiteModule.DocPro.approveOrrejectRequestInRequestNeedingApproval'('Approve', 'TtfzLQ/s9dQ=')

not_run: CustomKeywords.'suiteModule.HomePage.goToDocumentsPage'()

'Select the Required Level in the Folder management'
not_run: CustomKeywords.'suiteModule.DocPro.goToLevelInDocproSetup'('Document management', levName)

not_run: CustomKeywords.'suiteModule.DocPro.moveAllAvailableFiles'('bin')

not_run: CustomKeywords.'suiteModule.KeyWord.NavigateToLevelsPage'()

not_run: CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

not_run: CustomKeywords.'suiteModule.HomePage.goToDocumentRoutePage'()

not_run: CustomKeywords.'suiteModule.RouteCreation.deleteRoute'()

