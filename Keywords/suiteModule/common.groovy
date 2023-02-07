package suiteModule

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
import java.util.concurrent.TimeUnit

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

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable
import org.openqa.selenium.JavascriptExecutor
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit
public class common {
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

	public void validateStateDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/City_Page/cityDeletionSuccessMessage'))
			KeywordUtil.logInfo("State deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("State deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	public void validateCountryDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/City_Page/cityDeletionSuccessMessage'))
			KeywordUtil.logInfo("Country deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Country deletion is failed.. ! Success message is not displayed.. !")
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

	public void selectUser(String userCode) {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Meetings_Page/userTable_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(userCode)) {
				Boolean flag1 =DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).isSelected()
				if(!flag1){
					DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).click()
				} else {
					KeywordUtil.logInfo("User already selected..!")
				}
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("User data found and selected.. !")
		} else {
			throw new Error("User not found.. Please check the data ! Actual is --> " + val);
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/Done_Button'))
	}

	public void selectTime(String time) {
		WebUI.scrollToPosition(0, 0)
		if(time.contains("AM") || time.contains("am")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/AM_Button'))
		} else {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Shift_Page/PM_Button'))
		}
		switchFrameAndDoActions("TimeSelect", "//td[contains(text(),'"+time+"')]", "click",findTestObject('Home_Page/detailView_iFrame'))
	}

	public void selectTeam() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/selectTeam_Icon'))
		WebUI.switchToWindowIndex(1)
		WebUI.maximizeWindow()
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/existingTeam_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/employeeSelect_CheckBox'))
		//		int size = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='TeamLeader']//tbody//tr")).size()
		//		for(int i =1; i<=size;i++) {
		//			DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='TeamLeader']//tbody//tr["+i+"]/td/input[@name='TeamLeader_selectCheck']")).click()
		//		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/teamSave_Button'))
		WebUI.switchToWindowIndex(0)
	}

	public void searchUser(String searchOption, String Code) {
		WebUI.waitForPageLoad(30);
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		if(rowSize>=1) {
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUserFilter_Dropdown'),
					searchOption, true)
			switch (searchOption) {
				case "empcode":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Code)
					break;

				case "FullName":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), "")
					break;

				case "UserName":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), "")
					break;
				case "Email":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), "")
					break;
				case "ItarCompliance":
					WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchITAR_DropDown'),
					'ITAR', true)
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Code)
			}
		}
	}

	public void selectCountry(String Country) {
		if(!Country.isEmpty()) {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), Country+Keys.ENTER);
		} else {
			KeywordUtil.logInfo("Continue with the default Country..!")
		}
	}

	public int validateSearchUser(String searchOption,String pageName, String Code) {
		DriverFactory.getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {

			int flag = 0;String dataValue=null;
			int data=0;

			WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
			int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
			WebUI.switchToDefaultContent()

			if(rowSize != 0) {
				pageRecordSizeChange("100");
			} else {
				KeywordUtil.logInfo("There is no data found.. !")
				throw new Error("There is no data found in the search.. ! Please check the data..!")
			}
			switch (searchOption) {
				case "empcode":
					if(pageName.contains("users_page")) {
						data = 2;
					} else {
						data=3;
					}
					dataValue=Code;
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					if(pageName.contains("users_page")) {
						data = 2;
					} else {
						data=3;
					}
					dataValue=Code;
			}
			WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
			Thread.sleep(2000)
			int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
			for(int i =1; i<=rowSize1;i++) {
				String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row']/td["+data+"])["+i+"]")).getText();
				if(val.equals(dataValue)) {
					flag=i;
					KeywordUtil.logInfo("User data found.. !")
					break;
				}
			}
			WebUI.switchToDefaultContent()
			return flag;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while validating search user option.. !");
		}
	}

	public void validateUserDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
			KeywordUtil.logInfo("User deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	public String RandomNumber() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String DateTimeStr = sdf1.format(timestamp);
		return DateTimeStr;
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

	public void pageRecordSizeChangeInAddUser(String size) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInAddUser_DropDown'), 20)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInAddUser_DropDown'),
				size, true)
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
}
