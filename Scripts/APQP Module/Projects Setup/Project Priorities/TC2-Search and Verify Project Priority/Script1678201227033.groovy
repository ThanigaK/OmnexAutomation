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

'Click on the Project Priority Option'
WebUI.click(findTestObject('ProjectSetup_Page/Menu_IssueSetup'))

'Wait for the page to load for 5 seconds'
WebUI.waitForPageLoad(5)

'Verify New Button is present in Project Priority page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_CauseCategory'), 5)

'Verify Search icon is present in Project Priority page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Button_SearchIconCC'), 5)

'Verify Refresh icon is present in Project Priority page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Btn_RefreshCC'), 5)

'Verify Add button is present in Project Priority page'
WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Button_Add'), 5)

'Verify all fields and add new in Project Priority'
CustomKeywords.'apqpModule.ProjectSetup.addProjectPriorities'()

'Search for the newly created Project Priority'
CustomKeywords.'apqpModule.ProjectSetup.searchProjectPriorities'()

'Verify the Project Priority has been created properly'
CustomKeywords.'apqpModule.ProjectSetup.verifyProjectPriorities'()

