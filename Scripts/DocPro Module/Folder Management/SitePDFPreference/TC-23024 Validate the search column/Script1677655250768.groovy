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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By

'Login to the application\r\n'
CustomKeywords.'docProModule.Base.Login'()

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

'Select the Setup Icon'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Navigate to PDF Preferences page\r\n'
if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), 'class').contains('active')) {
    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/levels_PageObject Repository/Home_Page/Pdf_Preference page'))
} else {
    WebUI.click(findTestObject('Object Repository/Home_Page/SuiteSetup'))

    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/Pdf_Preference page'))
}

'Verify Site Preference search field\r\n'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Site_Preference_Search'), 10)

'Select Site Preference search field\r\n'
WebUI.click(findTestObject('PDF_Preference_page/Site_Preference_Search'))

'Verify Site Preference search Heading element present\r\n'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Heading_Search'), 10)

'Get text of Site Preference search Heading\r\n'
Heading = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

'Verify Site Preference search Heading macthes with the atual one\r\n\r\n'
WebUI.verifyMatch(Heading, 'Search (1)', false)

'Validate Refresh button inside the search field present or not'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/btn_Search_Refresh'), 10)

'Select the Refresh button inside the Search field'
WebUI.click(findTestObject('PDF_Preference_page/btn_Search_Refresh'))

'Validate search heading element present '
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Heading_Search'), 10)

'Get the search heading text'
Heading1 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

'Verify search heading text matches with the actuall text'
WebUI.verifyMatch(Heading1, 'Search', false)

'Validate add button inside the search element present '
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/btn_Add_Search'), 10)

'Select the add button inside the search field'
WebUI.click(findTestObject('PDF_Preference_page/btn_Add_Search'))

'Select again the add button inside the search field'
WebUI.click(findTestObject('PDF_Preference_page/btn_Add_Search'))

Heading2 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

'Verify heading shows "Search(2)" when we add 2 search rows'
WebUI.verifyMatch(Heading2, 'Search (2)', false)

WebUI.switchToFrame(findTestObject('IFrames/Iframe_DetailViewIframe'), 0)

'Remove one search row'
DriverFactory.getWebDriver().findElement(By.xpath('(//button[@title=\'Delete\'])[2]')).click()

WebUI.switchToDefaultContent()

'Get the Heading Text from the search field'
Heading3 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

'Verify heading shows "Search(1)" when we have 1 search rows'
WebUI.verifyMatch(Heading3, 'Search (1)', false)

'Apply value for Drop Down search Type'
WebUI.selectOptionByValue(findTestObject('PDF_Preference_page/Ddn_Search_Type'), '4', false)

'Apply value for Drop Down search condition'
WebUI.selectOptionByValue(findTestObject('PDF_Preference_page/Ddn_Search_Condition'), 'contains', false)

'Insert value for Search Value'
WebUI.setText(findTestObject('PDF_Preference_page/inp_Search_Value'), 'Project Charter Template')

'Select the "Search" button inside the search field'
WebUI.click(findTestObject('PDF_Preference_page/Btn_Search_Done'))

WebUI.delay(3)

List ele = DriverFactory.getWebDriver().findElements(By.xpath('//table[@id=\'gridModPDFDocTypes\']//tbody//tr'))

int size = ele.size()

'Validate the grid shows the value as per the search values'
if (size == 1) {
    WebUI.click(findTestObject('Object Repository/PDF_Preference_page/Btn_Refresh'))
} else {
}

'Select the Refresh button '
WebUI.click(findTestObject('PDF_Preference_page/Btn_Refresh'))

List ele1 = DriverFactory.getWebDriver().findElements(By.xpath('//table[@id=\'gridModPDFDocTypes\']//tbody//tr'))

'Validate the refresh functionality by checking all the module are present without any filter'
int size1 = ele1.size()

if (size1 > 1) {
    WebUI.click(findTestObject('Object Repository/PDF_Preference_page/Btn_Refresh'))
} else {
}

'Select the Search Icon '
WebUI.click(findTestObject('PDF_Preference_page/Site_Preference_Search'))

'Insert value for Search Value'
WebUI.setText(findTestObject('PDF_Preference_page/inp_Search_Value'), 'Dynamic Control Plan (Production Item)')

'Select the "Search" button inside the search field'
WebUI.click(findTestObject('PDF_Preference_page/Btn_Search_Done'))

'select the first result in the grid'
WebUI.click(findTestObject('PDF_Preference_page/chk_first Selection'))

'Select the "Update" button'
WebUI.click(findTestObject('PDF_Preference_page/Btn_Update'))

'Select the "Save" button'
WebUI.click(findTestObject('PDF_Preference_page/Btn_save'))

WebUI.delay(1.5)

'Verify "Success Alert" present'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Alert_Success'), 5)

'Select the Refresh button '
WebUI.click(findTestObject('PDF_Preference_page/Btn_Refresh'))

