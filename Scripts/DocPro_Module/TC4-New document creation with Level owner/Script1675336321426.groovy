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
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

CustomKeywords.'docPro.KeyWord.NavigateToLevelsPage'()

levName = CustomKeywords.'docPro.LevelsPage.createLevel'(LevelName)

'Going to Doc pro setup page\r\n'
CustomKeywords.'docPro.HomePage.NavigateToDocProSetupPage'()

'Select the Required Level in the Folder management'
CustomKeywords.'docPro.DocPro.goToLevelInDocproSetup'('Folder management', levName)

CustomKeywords.'docPro.DocPro.MakeLevelInUse'()

'Setting Day/Month value for "Documents Reviewed After"'
WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocReviewUnitName_DropDown'), 
    'Day', false)

'Setting Count value for "Documents Reviewed After"'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtDocReviewUnit_TextBox'), '1')

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

'Assigning Level PDF Preferences if need'
CustomKeywords.'docPro.DocPro.levelpdfPrefSelection'('Document Type', LevelPDFPreference)

CustomKeywords.'docPro.DocPro.setAutoPublish'('100', Module)

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

'Enter Document Number'
CustomKeywords.'docPro.DocPro.EnterDocNumber'(DocNumber)

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

CustomKeywords.'docPro.KeyWord.Logout'()

'Login to application with the Credentials'
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, 'donotdelete2', 'TtfzLQ/s9dQ=')

CustomKeywords.'docPro.Documents.NavigateToActionsPage'()

CustomKeywords.'docPro.DocPro.approveOrrejectRequestInRequestNeedingApproval'('Approve', 'TtfzLQ/s9dQ=')

'Going to Doc pro setup page\r\n'
CustomKeywords.'docPro.HomePage.NavigateToDocProSetupPage'()

'Select the Required Level in the Folder management'
CustomKeywords.'docPro.DocPro.goToLevelInDocproSetup'('Document management', levName)

CustomKeywords.'docPro.DocPro.moveAllAvailableFiles'('bin')

CustomKeywords.'docPro.KeyWord.NavigateToLevelsPage'()

CustomKeywords.'docPro.LevelsPage.levelDeletion'(levName)

