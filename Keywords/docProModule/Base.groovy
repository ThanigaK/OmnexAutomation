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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import org.openqa.selenium.JavascriptExecutor

import internal.GlobalVariable

public class Base {

	@Keyword
	public static void Login() 
	{
			WebUI.openBrowser('')
			WebUI.maximizeWindow()
			WebUI.deleteAllCookies();
			WebUI.navigateToUrl(GlobalVariable.url)
			WebUI.setText(findTestObject('Login_Page/userName_Input'), GlobalVariable.username)
			WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), GlobalVariable.Password)
			WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))
			WebUI.click(findTestObject('Login_Page/login_Button'))
			if(WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/popupOk_Button'), 5 , FailureHandling.OPTIONAL))
			{
				WebUI.click(findTestObject('Object Repository/Home_Page/popupOk_Button'))
			}
	}

	@Keyword
	public static void LoginwithCredential(String Url, String UserName, String Password) {
		try {
			WebUI.openBrowser('')
			WebUI.maximizeWindow()
			WebUI.deleteAllCookies();
			WebUI.navigateToUrl(Url)
			WebUI.setText(findTestObject('Login_Page/userName_Input'), UserName)
			WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), Password)
			WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))
			WebUI.click(findTestObject('Login_Page/login_Button'))
			if(WebUI.verifyElementPresent(findTestObject('Object Repository/Home_Page/popupOk_Button'), 5 , FailureHandling.OPTIONAL))
				WebUI.click(findTestObject('Object Repository/Home_Page/popupOk_Button'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error Login");
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

}
