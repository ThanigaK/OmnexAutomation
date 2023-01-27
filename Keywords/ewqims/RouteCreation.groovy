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

import cucumber.api.java.en.When

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

public class RouteCreation extends common {
	static int statePosition;

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	public static String DocNumber;
	public static String docName;
	public static int place;
	public static String routeName;


	@Keyword
	public void createNewRoute(String RouteName, String Code) {
		if (RouteName.equalsIgnoreCase("random")) {
			routeName = "Automation-" + DateTimeStr;
		}
		routeCreation(routeName,"Empcode",Code)
	}

	public void routeCreation(String name,String search, String user) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/newRouteAdd_Button'),15)
		Thread.sleep(3000)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/newRouteAdd_Button'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/routeName_TextBox'), name)
		if(!search.isEmpty()) {
			addUser(search,user)
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/save_Button'))
		KeywordUtil.logInfo("Route created successfully --> " + name)
	}

	public void addUser(String search,String data) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/addUser_TextBox'))
		pageRecordSizeChangeInAddUser("100")
		searchAndSelectUser(search,data)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/done_Button'))
	}

	public void searchAndSelectUser(String search,String data) {
		Boolean flag = false;
		Thread.sleep(3000)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/Groups_Page/searchUserOption_DropDown'), search, false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Groups_Page/searchUser_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Groups_Page/userTableList_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equalsIgnoreCase(data)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("User data found and selected.. !")
		} else {
			throw new Error("User data not found.. Please check the data !");
		}
	}

	@Keyword
	public void AssignRoute(String resetLevel, String resetSubLevelRoute) {
		'Scrolling to "Edit Route Option"'
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'), 15)

		WebUI.delay(3)

		'Navigate to Edit Route Option'
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'))

		'Select "Reset document routing" if need'
		if (resetLevel.equals('yes')) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetlevel_CheckBox'))

			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))

			KeywordUtil.logInfo('Reset Level checkbox clicked..!')
		}

		'Select "Reset Sub Level Routing" if need'
		if (resetSubLevelRoute.equals('yes')) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetsublevel_CheckBox'))

			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))

			KeywordUtil.logInfo('Reset Sub Level Routing checkbox clicked..!')
		}

		'Assign Value for "New Route"'
		if (!(routeName.toString().isEmpty())) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/newRoute_DropDown'))

			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'),
					routeName + Keys.ENTER)
		}

		'Assign Value for Existing Route"'
		if (!(routeName.toString().isEmpty())) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/existingRoute_DropDown'))

			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'),
					routeName + Keys.ENTER)
		}

		'Close the Route Option window'
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/popupClose_Icon'))
	}
	
	@Keyword
	public void deleteRoute() {
		routeDeletion(routeName)
	}
	
	public void routeDeletion(String route) {
		pageRecordSizeChangeInRouteHomePage("100")
		switchFrameAndDoActions("route", "//*[text()='"+route+"']", "click",findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
		WebUI.click(findTestObject("Object Repository/DocPro_Module/DocumentRoute_Page/delete_Button"))
		WebUI.click(findTestObject("Object Repository/Suite_Module/Groups_Page/popUpOk_Button"))
		KeywordUtil.logInfo("Route deleted sucessfully..!")
	}
	
	public void pageRecordSizeChangeInRouteHomePage(String size) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInRoutePage_DropDown'), 20)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInRoutePage_DropDown'),
				size, true)
	}
}
