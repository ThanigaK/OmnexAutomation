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

'Login to the application'
CustomKeywords.'docProModule.Base.Login'()

'Select setup option in the side panel'
if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Navigate to PDF_Preference page'
if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), 'class').contains('active')) {
    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/levels_PageObject Repository/Home_Page/Pdf_Preference page'))
} else {
    WebUI.click(findTestObject('Object Repository/Home_Page/SuiteSetup'))

    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/Pdf_Preference page'))
}

'Verify Template Dropdown present'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/ddn_template'), 5)

'Verify Default option present inside the template dropdown'
WebUI.verifyOptionPresentByValue(findTestObject('PDF_Preference_page/ddn_template'), '1', false, 0)

'Verify another options present inside the Template Dropdown\r\n'
WebUI.verifyOptionPresentByValue(findTestObject('PDF_Preference_page/ddn_template'), '2', false, 0)

'Select the Another Template in the DropDown\r\n'
WebUI.selectOptionByValue(findTestObject('PDF_Preference_page/ddn_template'), '2', false)

'Select the document type for the other modules'
WebUI.click(findTestObject('PDF_Preference_page/chk_SelectAllModules'))

'Click Save Button'
WebUI.click(findTestObject('PDF_Preference_page/Btn_save'))

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

'Scroll to get Level PDF Preference Link\r\n'
WebUI.scrollToElement(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'), 10)

'Verify Level PDF preference present or not\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'), 5)

'Select the Level PDF Preference Link\r\n'
WebUI.click(findTestObject('DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'))

'Get the Heading text in the newely opened window'
Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Heading'))

'Verify Template DropDown Presence\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/LevelPDF/TemplateDropDown'), 5)

'Verify Module Name in search option'
WebUI.verifyOptionSelectedByLabel(findTestObject('DocPro_Module/LevelPDF/TemplateDropDown'), 'Automation Template', false, 
    3)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

