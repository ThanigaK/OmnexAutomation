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

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(3)

'Right Click on the newly created Level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

WebUI.delay(3)

'Validate the New Option is Disabled'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'), 5)

'Select the new option\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'))

WebUI.delay(2)

'Validate the user can able to edit the level '
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/New_Level_Heading'), 0)

'Enter New Sub level name\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub)

'Click save button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Click on the newly created level folder arrow\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/FolderArrow'))

WebUI.delay(2)

'Select the new sub level'
WebUI.click(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Get the header text '
SubFolder = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Folger Management level Heading'))

'Validate user can able to create new sub level'
WebUI.verifyMatch(SubFolder, 'Level - ' + NewSub, false)

'Clicking Revision Option drop down'
RevOptValue = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Route Option/SubLevel/SubLevel_InheritFromParent'))

WebUI.verifyMatch(RevOptValue, 'Inherit from parent', false)

WebUI.click(findTestObject('DocPro_Module/Folder Management/Route Option/SubLevel/SubLevel_InheritFromParent'))

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

