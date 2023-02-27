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

CustomKeywords.'docProModule.Base.Login'()

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), 'class').contains('active')) {
    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/levels_PageObject Repository/Home_Page/Pdf_Preference page'))
} else {
    WebUI.click(findTestObject('Object Repository/Home_Page/SuiteSetup'))

    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/Pdf_Preference page'))
}

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Site_Preference_Search'), 10)

WebUI.click(findTestObject('PDF_Preference_page/Site_Preference_Search'))

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Heading_Search'), 10)

Heading = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

WebUI.verifyMatch(Heading, 'Search (1)', false)

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/btn_Search_Refresh'), 10)

WebUI.click(findTestObject('PDF_Preference_page/btn_Search_Refresh'))

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Heading_Search'), 10)

Heading1 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

WebUI.verifyMatch(Heading1, 'Search', false)

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/btn_Add_Search'), 10)

WebUI.click(findTestObject('PDF_Preference_page/btn_Add_Search'))

WebUI.click(findTestObject('PDF_Preference_page/btn_Add_Search'))

Heading2 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

WebUI.verifyMatch(Heading2, 'Search (2)', false)

WebUI.switchToFrame(findTestObject('IFrames/Iframe_DetailViewIframe'), 0)

DriverFactory.getWebDriver().findElement(By.xpath('(//button[@title=\'Delete\'])[2]')).click()

WebUI.switchToDefaultContent()

Heading3 = WebUI.getText(findTestObject('PDF_Preference_page/Heading_Search'))

WebUI.verifyMatch(Heading3, 'Search (1)', false)

WebUI.selectOptionByValue(findTestObject('PDF_Preference_page/Ddn_Search_Type'), '4', false)

WebUI.selectOptionByValue(findTestObject('PDF_Preference_page/Ddn_Search_Condition'), 'contains', false)

WebUI.setText(findTestObject('PDF_Preference_page/inp_Search_Value'), 'Project Charter Template')

WebUI.click(findTestObject('PDF_Preference_page/Btn_Search_Done'))

WebUI.delay(3)

List ele = DriverFactory.getWebDriver().findElements(By.xpath('//table[@id=\'gridModPDFDocTypes\']//tbody//tr'))

int size = ele.size()

if (size == 1) {
	WebUI.click(findTestObject('Object Repository/PDF_Preference_page/Btn_Refresh'))
} else {
}

WebUI.click(findTestObject('PDF_Preference_page/Btn_Refresh'))

List ele1 = DriverFactory.getWebDriver().findElements(By.xpath('//table[@id=\'gridModPDFDocTypes\']//tbody//tr'))

int size1 = ele1.size()

if (size1 > 1) {
	WebUI.click(findTestObject('Object Repository/PDF_Preference_page/Btn_Refresh'))
} else {
}

WebUI.click(findTestObject('PDF_Preference_page/Site_Preference_Search'))

WebUI.setText(findTestObject('PDF_Preference_page/inp_Search_Value'), 'Dynamic Control Plan (Production Item)')

WebUI.click(findTestObject('PDF_Preference_page/Btn_Search_Done'))

WebUI.click(findTestObject('PDF_Preference_page/chk_first Selection'))

WebUI.click(findTestObject('PDF_Preference_page/Btn_Update'))

WebUI.click(findTestObject('PDF_Preference_page/Btn_save'))

WebUI.delay(1.5)

WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/Alert_Success'), 5)


