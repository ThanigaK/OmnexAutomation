package suiteModule

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

public class LevelsPage extends common {
	static int statePosition;

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String levelname;

	@Keyword
	public String createLevel(String LevelName) {
		if (LevelName.equalsIgnoreCase("random")) {
			levelname = "Automation-" + DateTimeStr;
		}
		levelCreation(DateTimeStr,levelname,"DOC-")
		levelNameVerification(levelname)
		return levelname;
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

	public void levelNumberUpdate(String num) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelNumber_TextBox'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/popupOk_Button'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelNumber_TextBox'), num)
	}

	public void levelNameVerification(String levelName) {
		if(WebUI.getAttribute(findTestObject('Object Repository/DocPro_Module/Levels_Page/docProExpand_Icon'), "class").contains("close")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/docProExpand_Icon'));
		}
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		isElementPresent("level","//li/a/span[text()='"+levelName+"']")
		KeywordUtil.logInfo("Level is created / Level name is updated successfully.Actual Level name is --> "+levelName)
	}

	@Keyword
	public void modifyLevelData() {
		levelDataModification(levelname)
		levelNameVerification("Mod_"+levelname)
	}

	public void levelDataModification(String levelName) {
		switchFrameAndDoActions("level", "//li/a/span[text()='"+levelName+"']", "rightClick",findTestObject('Object Repository/Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/editLevel_Icon'))
		levelCreation("","Mod_"+levelName,"")
	}

	@Keyword
	public void deleteModifiedLevel() {
		levelDeletion("Mod_"+levelname)
	}

	@Keyword
	public void levelDeletion(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/searchLevel_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("level", "//li/a/span[text()='"+levelName+"']", "rightClick",findTestObject('Object Repository/Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/deleteLevel_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelDeletedMessage'), 20)
		KeywordUtil.logInfo("Level deleted succesfully--> "+levelName)
	}
	
	@Keyword
	public String createLevelwithSubLevelAccess(String LevelName) {
		if (LevelName.equalsIgnoreCase("random")) {
			levelname = "Automation-" + DateTimeStr;
		}
		levelCreationwithSublevelAccess(DateTimeStr,levelname,"DOC-")
		levelNameVerification(levelname)
		return levelname;
	}
	
	public void levelCreationwithSublevelAccess(String levelNum,String name,String prefix) {
		if(!levelNum.isEmpty()) {
			levelNumberUpdate(levelNum)
		}
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/levelName_TextBox'), name)

		if(!prefix.isEmpty()) {
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Levels_Page/prefix_TextBox'), prefix)
		}
		
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/Page_EwQIMS/label_Allow Site Sub-levels Creation'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Levels_Page/saveLevel_Button'))
	}
	
	
}
