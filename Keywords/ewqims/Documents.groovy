package ewqims

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

public class Documents extends common {
	static int statePosition;

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	public static String DocNumber;
	public static String docName;

	@Keyword
	public void openLevel(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/folderSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("lev", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/Home_Page/detailView_iFrame'))
	}
	
	@Keyword
	public void NavigateToActionsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/actions_Option'), 15)
	}
	
	
}
