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

'Select the EVAV NPD/APQP Platform Platform'
WebUI.click(findTestObject('Home_Page/EVAV_Platform'))

'Click on the Setup option in the left menu'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'), FailureHandling.STOP_ON_FAILURE)

'Click on the Suite Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/projectsSetup_Option'))

'Wait for 5 seconds for the page to load'
WebUI.waitForPageLoad(5)

'Verify whether Project Groups option is present in Projects Setup page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/ProjectGroupsOption'), 10)

'Click on Project Groups Option in Projects Setup menu'
WebUI.click(findTestObject('ProjectSetup_Page/ProjectGroupsOption'))

'Verify whether Add Button is present in Projects Groups page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/AddButton'), 10)

'Verify whether Refresh Button is present in Projects Groups page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RefreshButton'), 10)

'Verify whether Search Button is present in Projects Groups page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchButton'), 10)

'Verify whether Search DropDown is present in Projects Groups page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchDropdown_Grid'), 10)

'Verify whether Search Input box is present in Projects Groups page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchInput_Grid'), 10)

'Create a New group and verify the group is created properly'
CustomKeywords.'apqpModule.ProjectSetup.createNewProjectGroup'('random')

'Edit the new group created and verify the edited name'
CustomKeywords.'apqpModule.ProjectSetup.editProjectGroup'('Automation_Edited')

'Search for the created Project Group'
CustomKeywords.'apqpModule.ProjectSetup.ResetAndSearchProjectGroup'()

'Delete the group created'
CustomKeywords.'apqpModule.ProjectSetup.DeleteProjectGroup'()

