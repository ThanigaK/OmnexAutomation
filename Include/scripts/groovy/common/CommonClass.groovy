package common
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

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.WebDriver
import org.apache.commons.collections4.map.HashedMap
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;



/**
 * @author sibis
 *
 */
class CommonClass {
	public static int rowNum = 0;
	public static String code;
	public static String userName;
	public static String email;
	public static String dateTimeStr;
	public static WebDriver driver1;
	public static WebDriver driver2;
	public static HashMap<String , String> allWindows = new HashMap<String,String>()

	public static String getData(String dataName) {
		String value = null;
		try {
			String fileName = getPropertyValue("Global", "ExcelFileName");
			value = findTestData(fileName).getValue(dataName, GlobalAnnotations.rowNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error("Error while getting data value from excel sheet");
		}
		return value;
	}

	public static String getPropertyValue(String propertyFileName, String dataName) {
		try {
			FileReader reader = new FileReader(propertyFileName + ".properties");
			Properties p = new Properties();
			p.load(reader);
			String propValue = p.getProperty(dataName);
			return propValue;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error("Error while getting an property value");
		}
	}

	public static void waitForClickableAndClick(TestObject to, int timeOut) {
		try {
			WebUI.waitForElementClickable(to, timeOut);
			WebUI.scrollToElement(to,timeOut)
			WebUI.click(to)
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while waiting and clicking the element");
		}
	}

	public static void switchFrameAndDoActions(String objectName, String dynamicXpath,String actionType,TestObject frame) {
		WebUI.switchToFrame(frame, 20)
		TestObject to = new TestObject(objectName)
		to.addProperty("xpath", ConditionType.EQUALS, dynamicXpath)
		WebUI.waitForElementClickable(to, 30);
		if(actionType.equalsIgnoreCase("click")) {
			WebUI.click(to);
		} else if(actionType.equalsIgnoreCase("doubleClick")) {
			WebUI.doubleClick(to);
		} else if(actionType.equalsIgnoreCase("rightClick")) {
			WebUI.rightClick(to);
		} else if (actionType.equalsIgnoreCase("jsClick")){
			WebElement element = DriverFactory.getWebDriver().findElement(By.xpath(dynamicXpath));
			JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getWebDriver();
			executor.executeScript("arguments[0].click();", element);
		} else if (actionType.equalsIgnoreCase("mouseOver")){
			WebUI.mouseOver(to)
		} else {
			WebUI.click(to);
		}
		WebUI.switchToDefaultContent();
	}

	public static void switchFrameAndDoActions(String objectName, String dynamicXpath,String actionType,String data,TestObject frame) {
		WebUI.switchToFrame(frame, 20)
		TestObject to = new TestObject(objectName)
		to.addProperty("xpath", ConditionType.EQUALS, dynamicXpath)
		WebUI.waitForElementClickable(to, 20);
		if(actionType.equalsIgnoreCase("sendkeys")) {
			WebUI.sendKeys(to, data)
		} else {
			WebUI.sendKeys(to, data)
		}
		WebUI.switchToDefaultContent();
	}

	public static void compareDateWithCurrentDate(String dateFromApplication) {

		SimpleDateFormat formatter = new SimpleDateFormat(getData("Date format").replace('DD', 'dd'));
		Date date = new Date();
		if(dateFromApplication.contains(formatter.format(date))) {
			KeywordUtil.logInfo("Date format matched..!")
		} else {
			KeywordUtil.markFailed("Date format not matched..! Actual date is--> "+dateFromApplication)
		}
	}

	public void pageRecordSizeChange(String size) {
		if(WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'))) {
			WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'), 20)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'),
					size, true)
		} else {
			KeywordUtil.logInfo("Page size dropdown not displaying.. Continue the flow")
		}
	}

	public static void switchToNextWindow() {
		String wid = DriverFactory.getWebDriver().getWindowHandle()
		Set<String> allWid = DriverFactory.getWebDriver().getWindowHandles()
		for(String win:allWid) {
			if(!win.equals(wid)) {
				DriverFactory.getWebDriver().switchTo().window(win)
				KeywordUtil.logInfo("Window switched..!")
				break;
			}
		}
	}

	public static boolean isElementPresent(String objectName , String dynamicXpath) {
		TestObject to = new TestObject(objectName)
		to.addProperty("xpath", ConditionType.EQUALS, dynamicXpath)
		Boolean flag = WebUI.verifyElementPresent(to, 15);
		if(flag==true) {
			KeywordUtil.logInfo("Element present")
		} else {
			KeywordUtil.markErrorAndStop("Element not present")
		}
		WebUI.switchToDefaultContent();
		return flag;
	}
}