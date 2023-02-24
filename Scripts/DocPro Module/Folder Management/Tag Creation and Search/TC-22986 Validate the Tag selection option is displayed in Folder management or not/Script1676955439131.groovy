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

'Scrolling to Tag Selection Link\r\n'
WebUI.scrollToElement(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'), 10)

'Verify Tag Selection Link is Present\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'), 10)

'Mouse Hover over the Tag "Select" Link\r\n'
WebUI.mouseOver(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

'Click the Tag Selection Link\r\n'
WebUI.click(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

'Get the Heading text in the newely opened window'
Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Heading'))

'Validate the Heading is "Tag Selection"'
WebUI.verifyMatch(Heading, 'Tag Selection', false)

'Search for Tags'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/tagSelection/TagSearch'), 'DoNotDelete')

'Waiting for some sec to load '
WebUI.delay(5)

'Get the Firt value in the Searched List'
FirstValue = WebUI.getText(findTestObject('DocPro_Module/Folder Management/tagSelection/TagSearchedText'))

'Validate the Searched value and the shown value are same\r\n'
WebUI.verifyMatch(FirstValue, 'DoNotDelete', false)

'Get Attribute of the empty checkbox\r\n'
Uncheck = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/tagSelection/ValidateTagCheckBox'), 'class')

'Make Sure the Check box is empty'
WebUI.verifyMatch(Uncheck, 'odd', false)

'Select the Checkbox\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/tagSelection/TagCheckBox'))

'Again get the attribute of same checkbox'
Check = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/tagSelection/ValidateTagCheckBox'), 'class')

'Now make sure the checkbox is selected'
WebUI.verifyMatch(Check, 'odd selected', false)

'Now click the Done button'
WebUI.click(findTestObject('DocPro_Module/Folder Management/tagSelection/DoneButton'))

'Get Text at the "Tag Selection" Link\r\n'
SelectedTag = WebUI.getText(findTestObject('DocPro_Module/DocProSetup_Page/tagSelection_Link'))

'Valifdate the Selected tag is shown in the Folder management Level page\r\n'
WebUI.verifyMatch(SelectedTag, 'DoNotDelete', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

