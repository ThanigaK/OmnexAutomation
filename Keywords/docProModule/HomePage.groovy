package docProModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import junit.framework.Assert

public class HomePage extends Base {

	@Keyword
	public void NavigateToDocProSetup() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), "class").contains("active")) {

			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
		}
		else {
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)
			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
		}
	}

	@Keyword
	public void goToLevelPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), "class").contains("active")) {

			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
		}
		else {
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)
			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
		}
	}

	@Keyword
	public void gotoPdfPref() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/Sidebar/SuiteSetUp DropDown'), "class").contains("active")) {

			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/levels_PageObject Repository/Home_Page/Pdf_Preference page'), 15)
		}
		else {
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)
			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)
			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/Pdf_Preference page'), 15)
		}
	}
}
