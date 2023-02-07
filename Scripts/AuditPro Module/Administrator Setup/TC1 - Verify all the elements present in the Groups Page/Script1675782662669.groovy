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
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

'Click on the Home logo to select the platform'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Groups option'
WebUI.click(findTestObject('Home_Page/groups_Option'))

'Verify the Groups link in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/groups_Link'), 10)

'Verify the Audits Module link in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/auditsModule_Link'), 10)

'Click on the Audits module link in the left pane'
WebUI.click(findTestObject('Groups_Page/auditsModule_Link'))

'Wait for the page to load'
WebUI.delay(3)

'Verify whether Site dropdown is present in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/site_DropDown'), 10)

'Verify whether Add group button is present in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/addGroup_Button'), 10)

'Verify whether Search Icon is present in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/search_Icon'), 10)

'Verify whether Refresh Icon is present in the page'
WebUI.verifyElementPresent(findTestObject('Groups_Page/refresh_Icon'), 10)

'Click on the Search Icon in Groups page to open the Search pop up'
WebUI.click(findTestObject('Groups_Page/search_Icon'))

'Select the Column name as -->  Group Name'
WebUI.selectOptionByValue(findTestObject('Groups_Page/searchColumn_DropDown'), '2', false)

'Click on the Search Button in the Search pop up'
WebUI.click(findTestObject('Groups_Page/searchButton'))

'Verify Group Name column has been added to the Groups Page Search'
WebUI.verifyElementPresent(findTestObject('Groups_Page/groupName_Dropdown'), 10)

'Click on the Site drop down element'
WebUI.click(findTestObject('Groups_Page/siteDropDown_span'))

'Verify the Site drop down has multiple values'
WebUI.verifyElementPresent(findTestObject('Groups_Page/site_DropDownList'), 10)

