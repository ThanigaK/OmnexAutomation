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
not_run: CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Navigating to levels page'
not_run: CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
not_run: levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

'Logging out from the application'
not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to the EWQIMS application with user without any access\r\n\r\n\r\n'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'dummyUser', 'QJblfja5Cso=')

//'Select the Side Menu Setup Icon\r\n'
//CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

//
//if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
//    WebUI.click(findTestObject('Home_Page/menu_Icon'))
//}
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

//'Validate the DocPro Setup option is not available for the user with no module access\r\n'
//WebUI.verifyElementPresent(findTestObject('Home_Page/Sidebar/SuiteSetUp DropDown'), 5, FailureHandling.STOP_ON_FAILURE)

'Validate the DocPro Setup option is not available for the user with no module access\r\n'
WebUI.verifyElementPresent(findTestObject('Home_Page/setup_OptionIcon'), 3)

'Validate the DocPro Setup option is not available for the user with no module access\r\n'
WebUI.verifyElementNotPresent(findTestObject('Home_Page/Sidebar/SuiteSetUp DropDown'), 5, FailureHandling.STOP_ON_FAILURE)

'Enter Newly created level name in the folder management search box\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

not_run: WebUI.delay(3)

'Validate the level is displaying or not\r\n'
not_run: WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'), 5)

'Again logging out from the application'
not_run: CustomKeywords.'suiteModule.KeyWord.Logout'()

'Login to the EWQIMS application\r\n'
not_run: CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Navigating to the DocPro Setup page'
not_run: CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
not_run: WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

not_run: WebUI.delay(3)

'Right Click on the newly created Level'
not_run: WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'), 5)

'Navigating to levels page'
not_run: CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
not_run: CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

