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

'Login to the application as module admin user'
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, 'apqp', '5xx1bkCcAlw=')

'Click on the Home logo to select the platform'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the EVAV NPD/APQP Platform'
WebUI.click(findTestObject('Home_Page/EVAV_Platform'))

'Click on the Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Projects Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/projectsSetup_Option'))

'Click on the Charter Template Option'
WebUI.click(findTestObject('ProjectSetup_Page/Menu_CharterTemplate'))

'Wait for the page to load for 5 seconds'
WebUI.waitForPageLoad(5)

'Verify the New Button is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Button_Add'), 5)

'Verify the Search Button is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchButton'), 5)

'Verify the Refresh Button is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Button_Refresh'), 5)

'Verify the Delete Button is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Button_Add'), 5)

'Verify the Search Status Dropdown is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchButton'), 5)

'Verify the Search Dropdown Option is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PrioritySearchDropdown_Grid'), 5)

'Verify the Table with Titles of  is present in the Charter Template page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PrioritySearchInput_Grid'), 5)

'Click on the New Button '
WebUI.click(findTestObject('ProjectSetup_Page/Button_Add'), FailureHandling.STOP_ON_FAILURE)

'Verify the New Charter creation page is displayed'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_AddTitle'), 5)

'Verify the charter name input box for creating charter is displayed'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PriorityDescription_InputBox'), 5)

'Set Group Rights to Project Charter'
CustomKeywords.'apqpModule.ProjectSetup.setGroupRightsMenu'('random')

'Verify Project Management menu is displayed properly'
CustomKeywords.'apqpModule.ProjectSetup.verifyProjectManagementMenu'('random')

