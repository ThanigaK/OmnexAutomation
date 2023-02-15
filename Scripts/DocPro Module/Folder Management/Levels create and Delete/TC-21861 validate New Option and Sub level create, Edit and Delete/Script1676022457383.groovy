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

'Right click on the new sub level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Click on the Edit option to validate we can able to access it or not\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Edit_RightClick'))

'Rename the sub level\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), RenameSub)

'Click save button\r\n'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Select Site Dropdown'
WebUI.click(findTestObject('DocPro_Module/Folder Management/SiteDrpDwn'))

'Select another site'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/siteSearch'), 'DocProSite' + Keys.ENTER)

KeywordUtil.logInfo('Site selected successfully --> ' + 'DocProSite')

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(3)

'Validate the level can also be accessed in another site\r\n'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'), 5)

'Validate the sub level cannot be accessed in another site'
WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Folder Management/NewSubFolder'), 5)

'Select site drop down'
WebUI.click(findTestObject('DocPro_Module/Folder Management/SiteDrpDwn'))

'Change the site back'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/siteSearch'), 'Corporate' + Keys.ENTER)

KeywordUtil.logInfo('Site selected successfully --> ' + 'DocProSite')

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(2)

'select the newly created sub folder\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Get the Header text'
SubFolder = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Folger Management level Heading'))

'validate the sub level edit is successfull'
WebUI.verifyMatch(SubFolder, 'Level - ' + RenameSub, false)

'Right click on the new sub level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Click the sub level delete button \r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Delete_RightClick'))

'Salect Yes button'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Delete_Yes_Button'))

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

