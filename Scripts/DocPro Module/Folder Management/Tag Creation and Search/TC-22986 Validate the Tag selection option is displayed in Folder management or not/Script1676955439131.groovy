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

WebUI.scrollToElement(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'), 10)

WebUI.verifyElementPresent(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'), 10)

WebUI.mouseOver(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

WebUI.click(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Heading'))

WebUI.verifyMatch(Heading, 'Tag Selection', false)

WebUI.setText(findTestObject('DocPro_Module/Folder Management/tagSelection/TagSearch'), 'DoNotDelete')

WebUI.delay(5)

FirstValue = WebUI.getText(findTestObject('DocPro_Module/Folder Management/tagSelection/TagSearchedText'))

WebUI.verifyMatch(FirstValue, 'DoNotDelete', false)

Uncheck = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/tagSelection/ValidateTagCheckBox'), 'class')

WebUI.verifyMatch(Uncheck, 'odd', false)

WebUI.click(findTestObject('DocPro_Module/Folder Management/tagSelection/TagCheckBox'))

Check = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/tagSelection/ValidateTagCheckBox'), 'class')

WebUI.verifyMatch(Check, 'odd selected', false)

WebUI.click(findTestObject('DocPro_Module/Folder Management/tagSelection/DoneButton'))

SelectedTag = WebUI.getText(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

WebUI.verifyMatch(SelectedTag, 'DoNotDelete', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

