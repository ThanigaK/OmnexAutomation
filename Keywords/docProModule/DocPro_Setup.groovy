package docProModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import internal.GlobalVariable

public class DocPro_Setup extends Base {

	@Keyword
	public void goToLevelInDocproSetup(String page, String Level) {
		if(page.equalsIgnoreCase("Folder management")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/folderManagement_Option'))
		} else if (page.equals("Document management")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/documentManagement_Option'))
		}
		switchFrameAndDoActions("level", "//*[text()='"+Level+"']", "jsclick", findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
	}

	@Keyword
	public void RightClickOntheLevel(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/searchLevel_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("level", "//li/a/span[text()='"+levelName+"']", "rightClick",findTestObject('Object Repository/Home_Page/detailView_iFrame'))
	}

	@Keyword
	public void SelectRightforSite() {
	}

	@Keyword
	public void CreateNewSubLevel(String ParentLevelName, String SubLevelName) {
		WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), ParentLevelName + Keys.ENTER)
		WebUI.delay(3)
		WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))
		WebUI.delay(3)
		WebUI.click(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'))
		WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), SubLevelName)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))
	}
}
