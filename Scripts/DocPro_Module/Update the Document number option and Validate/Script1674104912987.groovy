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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import java.sql.Timestamp as Timestamp

'Login to application with the Credentials'
CustomKeywords.'ewqims.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Going to Doc pro setup page\r\n'
CustomKeywords.'ewqims.HomePage.NavigateToDocProSetupPage'()

'Select the Required Level in the Folder management'
CustomKeywords.'ewqims.DocPro.goToLevelInDocproSetup'(Page, Level)

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
CustomKeywords.'ewqims.DocPro.AddLevelOwner'('Empcode', LevelOwner)

'Selecting tag'
CustomKeywords.'ewqims.DocPro.tagSelection'('')

'Select "Records" Checkbox if need'
if (data.equals('yes')) {
    WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'), 15)

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'))

    KeywordUtil.logInfo('Records checkbox clicked.')
}

'Scrolling to "Edit Route Option"'
WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'), 15)

'Navigate to Edit Route Option'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'))

'Select "Reset document routing" if need'
if (resetLevel.equals('yes')) {
    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetlevel_CheckBox'))

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))

    KeywordUtil.logInfo('Reset Level checkbox clicked..!')
}

'Select "Reset Sub Level Routing" if need'
if (resetSubLevelRoute.equals('yes')) {
    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetsublevel_CheckBox'))

    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))

    KeywordUtil.logInfo('Reset Sub Level Routing checkbox clicked..!')
}

'Assign Value for "New Route"'
if (!(newRoute.toString().isEmpty())) {
    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/newRoute_DropDown'))

    WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'), 
        newRoute + Keys.ENTER)
}

'Assign Value for Existing Route"'
if (!(existing.toString().isEmpty())) {
    WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/existingRoute_DropDown'))

    WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'), 
        newRoute + Keys.ENTER)
}

'Close the Route Option window'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/popupClose_Icon'))

'Assigning Level PDF Preferences if need'
CustomKeywords.'ewqims.DocPro.levelpdfPrefSelection'('Document Type', LevelPDFPreference)

CustomKeywords.'ewqims.DocPro.setAutoPublish'('100', Module)

//WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))
//KeywordUtil.logInfo("Save button clicked..!")
'Navigating to "New Doc Request" Page '
CustomKeywords.'ewqims.HomePage.NavigateToNewDocRequestPage'()

'Click the Site DropDown'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/site_DropDown'))

'Select new Site'
WebUI.setText(findTestObject('Object Repository/Suite_Module/City_Page/searchCountryState_TextBox'), siteName + Keys.ENTER)

KeywordUtil.logInfo('Site selected successfully --> ' + siteName)

'Select "Documnet Level"'
CustomKeywords.'ewqims.NewDocRequest.levelSelection'(LevelName)

'Enter Document Number'
CustomKeywords.'ewqims.DocPro.EnterDocNumber'(DocNumber)

'Entering Document Name'
CustomKeywords.'ewqims.DocPro.EnterDocName'(DocName)

'Enter Revision Number'
CustomKeywords.'ewqims.DocPro.enterRevisionNum'(Revison)

'Upload the New Document'
CustomKeywords.'ewqims.DocPro.uploadFile'(FilePath)

'Click the Add Button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/addDocument_Button'))

if (FilePath.toString().isEmpty()) {
    WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))

    WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))

    KeywordUtil.logInfo('Proceeding without document')
}

'Validate the Document added as per the Level setting\r\n'
CustomKeywords.'ewqims.DocPro.validateDocNum'(DocNumOption)

'Navigate to TOC'
CustomKeywords.'ewqims.HomePage.goToDocumentsPage'()

'Open the Level'
CustomKeywords.'ewqims.Documents.openLevel'(Level)

'Validate the documet added is present in the list and get it\'s position to delete'
CustomKeywords.'ewqims.DocPro.DeleteAddedDocument'()

'Right Click on the Newely created Document Number'
CustomKeywords.'ewqims.DocPro.rightClickOnDoc'()

'Select "Change Request" option'
CustomKeywords.'ewqims.DocPro.clickOnRightClickOptions'('Change Request')

'Scrolling to "Delete Document" check box'
WebUI.scrollToElement(findTestObject('DocPro_Module/Documents_Page/DeleteDocument'), 25)

'Select "Delete Document" Checkbox'
WebUI.click(findTestObject('DocPro_Module/Documents_Page/DeleteDocument'))

'Hit the Continue button & Delete the Newely created Document\r\n\r\n'
WebUI.click(findTestObject('DocPro_Module/Documents_Page/ContinueButton'))

