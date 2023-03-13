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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Login to the application\r\n'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
    WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

'Select the Setup option'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), 'class').contains('active')) {
    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/levels_PageObject Repository/Home_Page/Pdf_Preference page'))
} else {
    WebUI.click(findTestObject('Object Repository/Home_Page/SuiteSetup'))

    WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)

    WebUI.click(findTestObject('Object Repository/Home_Page/Pdf_Preference page'))
}

'Verify Enable Enhanced Pricing Presence'
WebUI.verifyElementPresent(findTestObject('PDF_Preference_page/chk_Enable_Restricted_View'), 5)

WebUI.click(findTestObject('PDF_Preference_page/chk_Enable_Restricted_View'))

