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

'Click on the Deliverable Priorities Option'
WebUI.click(findTestObject('ProjectSetup_Page/DeliverablePrioritiesOption'))

'Wait for the page to load for 5 seconds'
WebUI.waitForPageLoad(5)

'Verify the Add Button is present in the Deliverable Priorities page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/AddButton'), 5)

'Verify the Search Button is present in the Deliverable Priorities page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SearchButton'), 5)

'Verify the Refresh Button is present in the Deliverable Priorities page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/RefreshButton'), 5)

'Verify the Priority DropDown is present in the Deliverable Priorities page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PrioritySearchDropdown_Grid'), 5)

'Verify the Priority Search Input box is present in the Deliverable Priorities page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PrioritySearchInput_Grid'), 5)

'Click on the Add Button '
WebUI.click(findTestObject('ProjectSetup_Page/AddButton'), FailureHandling.STOP_ON_FAILURE)

'Verify the Add Title is Shown in the Add Priority pop up box'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/AddTitle'), 5)

'Verify the Priority Description is Shown in the Add Priority pop up box'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/PriorityDescription_InputBox'), 5)

'Click on Cancel Button'
WebUI.click(findTestObject('ProjectSetup_Page/CancelButton'), FailureHandling.STOP_ON_FAILURE)

'Create a new priority and verify the priority is added successfully'
CustomKeywords.'apqpModule.ProjectSetup.createNewDeliverablePriorities'('random')

