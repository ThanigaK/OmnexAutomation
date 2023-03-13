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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

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
WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub1)

'Click save button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

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
WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub2)

'Click save button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

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

WebUI.delay(2)

'Right click on the new sub level'
WebUI.click(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Making the Level In Use'
CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/selectLevel_Button'))

WebUI.delay(3)

'Try to select the Sub Level\r\n'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), NewSub1 + Keys.ENTER)

'Validate Corporate should not show the sub level '
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Documents_Page/lbl_NoDatFound'), 3)

'Try to select the Sub Level\r\n'
WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), NewSub1 + Keys.ENTER)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

