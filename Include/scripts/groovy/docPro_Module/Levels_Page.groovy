package docPro_Module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import common.CommonClass
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.openqa.selenium.Keys

/**
 * @author sibis
 *
 */
class Levels_Page extends CommonClass{

	public static String levelName = getData("Level name");
	public void levelNumberUpdate(String num) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelNumber_TextBox'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/popupOk_Button'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelNumber_TextBox'), num)
	}

	public void levelCreation(String levelNum,String name,String prefix) {
		if(!levelNum.isEmpty()) {
			levelNumberUpdate(levelNum)
		}
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelName_TextBox'), name)

		if(!prefix.isEmpty()) {
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/prefix_TextBox'), prefix)
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/saveLevel_Button'))
	}

	public void levelNameVerification(String levelName) {
		if(WebUI.getAttribute(findTestObject('Object Repository/DocPro_Module/Levels_Page/docProExpand_Icon'), "class").contains("close")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/docProExpand_Icon'));
		}
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		isElementPresent("level","//li/a/span[text()='"+levelName+"']")
		KeywordUtil.logInfo("Level is created / Level name is updated successfully.Actual Level name is --> "+levelName)
	}

	public void levelDataModification(String levelName) {
		switchFrameAndDoActions("level", "//li/a/span[text()='"+levelName+"']", "rightClick",findTestObject('Object Repository/Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/editLevel_Icon'))
		levelCreation("","Mod_"+levelName,"")
	}

	public void levelDeletion(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/searchLevel_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("level", "//li/a/span[text()='"+levelName+"']", "rightClick",findTestObject('Object Repository/Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/deleteLevel_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelDeletedMessage'), 20)
		KeywordUtil.logInfo("Level deleted succesfully--> "+levelName)
	}

	@When("Create level")
	public void createLevel() {
		if (getData("Level name").equalsIgnoreCase("random")) {
			levelName = "Automation-" + dateTimeStr;
		}
		levelCreation(dateTimeStr,levelName,"DOC-")
		levelNameVerification(levelName)
	}

	@When("Modify level data")
	public void modifyLevelData() {
		levelDataModification(levelName)
		levelNameVerification("Mod_"+levelName)
	}

	@When("Delete modified level")
	public void deleteModifiedLevel() {
		levelDeletion("Mod_"+levelName)
	}

	@When("Delete level")
	public void deleteLevel() {
		levelDeletion(levelName)
	}
}