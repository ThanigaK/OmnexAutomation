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
WebUI.click(findTestObject('ProjectSetup_Page/TaskRatingOption'))

'Wait for the page to load for 5 seconds'
WebUI.waitForPageLoad(5)

'Verify Task Rating link is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Link_TaskRating'), 5)

'Verify Task Rating link is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_PageTitle'), 5)

'Get the text of the Page Title'
String PageTitle = WebUI.getText(findTestObject('ProjectSetup_Page/Label_PageTitle'))

'Verify the Page Title is displayed as - Task Rating - Deliverable Status'
WebUI.verifyMatch(PageTitle, 'Task Rating - Deliverable Status', false)

'Verify Deliverable Status - Not Started is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_DeliverableStatus1'), 5)

'Get the text of the Delivereable Status 1'
String DeliverableStatus = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Label_DeliverableStatus1'))

'Verify the Deliverable Status 1 is displayed as - Not Started'
WebUI.verifyMatch(DeliverableStatus, 'Not Started', false)

'Verify Deliverable Status - InProgress is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_DeliverableStatus2'), 5)

'Get the text of the Delivereable Status 2'
DeliverableStatus = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Label_DeliverableStatus2'))

'Verify the Deliverable Status 2 is displayed as - In Progress'
WebUI.verifyMatch(DeliverableStatus, 'In Progress', false)

'Verify Deliverable Status - Completed is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_DeliverableStatus3'), 5)

'Get the text of the Delivereable Status 3'
DeliverableStatus = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Label_DeliverableStatus3'))

'Verify the Deliverable Status 3 is displayed as - Completed'
WebUI.verifyMatch(DeliverableStatus, 'Completed', false)

'Verify Deliverable Status - Dealine Violated is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_DeliverableStatus4'), 5)

'Get the text of the Delivereable Status 4'
DeliverableStatus = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Label_DeliverableStatus4'))

'Verify the Deliverable Status 4 is displayed as - Deadline Violated'
WebUI.verifyMatch(DeliverableStatus, 'Deadline Violated', false)

'Verify Deliverable Status - Completed Deadline Violated is Displayed in Task Rating page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Label_DeliverableStatus5'), 5)

'Get the text of the Delivereable Status 5'
DeliverableStatus = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Label_DeliverableStatus5'))

'Verify the Deliverable Status 5 is displayed as - Completed Deadline Violated'
WebUI.verifyMatch(DeliverableStatus, 'Completed Deadline Violated', false)

