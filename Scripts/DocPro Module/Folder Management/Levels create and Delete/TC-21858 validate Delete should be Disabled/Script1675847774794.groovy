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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the EWQIMS application\r\n'
CustomKeywords.'docProModule.Base.Login'()

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter the newly created Level name in the Folder Management search field'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(3)

'Right click on the Level\r\n'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

'get the newly created level\'s  Delete option attribute to check the Delete option is disabled'
DeleteAttribute = WebUI.getAttribute(findTestObject('DocPro_Module/Folder Management/Delete_RightClick'), 'cursor')

WebUI.verifyMatch(DeleteAttribute, 'not-allowed !important', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

