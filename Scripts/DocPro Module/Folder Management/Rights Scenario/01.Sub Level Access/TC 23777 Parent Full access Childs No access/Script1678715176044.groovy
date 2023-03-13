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
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'RightScenario', '5xx1bkCcAlw=')

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

CustomKeywords.'docProModule.DocPro_Setup.CreateNewSubLevel'(levName, NewSub1)

'Navigating to the DocPro Setup page'
not_run: CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

not_run: CustomKeywords.'docProModule.DocPro_Setup.CreateNewSubLevel'(levName, NewSub2)

'Enter Newly created level name in the folder management search box\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

not_run: WebUI.delay(3)

'Right Click on the newly created Level'
not_run: WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

not_run: WebUI.delay(3)

'Select the new option\r\n'
not_run: WebUI.click(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'))

not_run: WebUI.delay(2)

'Enter New Sub level name\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub2)

'Click save button'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to the DocPro Setup page'
not_run: CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

not_run: WebUI.delay(3)

'Right Click on the newly created Level'
not_run: WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

not_run: WebUI.delay(3)

'Select the new option\r\n'
not_run: WebUI.click(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'))

not_run: WebUI.delay(2)

'Enter New Sub level name\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub4)

'Click save button'
not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Navigating to "New Doc Request" Page '
not_run: CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

not_run: WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/selectLevel_Button'))

not_run: WebUI.delay(3)

'Try to select the Sub Level\r\n'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), levName + 
    Keys.ENTER)

'Validate Corporate should show the sub level '
not_run: WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Documents_Page/lbl_NoDatFound'), 3)

'Try to select the Sub Level\r\n'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), NewSub1 + 
    Keys.ENTER)

'Validate Corporate should show the sub level '
not_run: WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Documents_Page/lbl_NoDatFound'), 3)

'Try to select the Sub Level\r\n'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), NewSub2 + 
    Keys.ENTER)

'Validate Corporate should show the sub level '
not_run: WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Documents_Page/lbl_NoDatFound'), 3)

'Try to select the Sub Level\r\n'
not_run: WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), NewSub3 + 
    Keys.ENTER)

'Validate Corporate should show the sub level '
not_run: WebUI.verifyElementNotPresent(findTestObject('DocPro_Module/Documents_Page/lbl_NoDatFound'), 3)

'Navigating to levels page'
not_run: CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
not_run: CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

