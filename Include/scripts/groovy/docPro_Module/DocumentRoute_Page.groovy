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

import common.CommonClass
import internal.GlobalVariable
import javassist.bytecode.stackmap.BasicBlock.Catch
import suite_module.Groups_Page

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



/**
 * @author sibis
 *
 */
class DocumentRoute_Page extends CommonClass{

	public static String routeName = getData("Route name");

	public void pageRecordSizeChangeInAddUser(String size) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInAddUser_DropDown'), 20)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInAddUser_DropDown'),
				size, true)
	}

	public void pageRecordSizeChangeInRouteHomePage(String size) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInRoutePage_DropDown'), 20)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/tableRecordSizeInRoutePage_DropDown'),
				size, true)
	}

	public void addUser(String search,String data) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/addUser_TextBox'))
		pageRecordSizeChangeInAddUser("100")
		new Groups_Page().searchAndSelectUser(search,data)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/done_Button'))
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
	public void validateRouteCreation(String route) {
		pageRecordSizeChangeInRouteHomePage("100")
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'), 15)

		isElementPresent("Route","//*[text()='"+route+"']")
		KeywordUtil.logInfo("Route is created successfully. Route name is --> " + routeName)
	}

	public void validateRouteDeletion(String route) {
		pageRecordSizeChangeInRouteHomePage("100")
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'), 15)
		try {
			isElementPresent("Route","//*[text()='"+route+"']")
			KeywordUtil.markErrorAndStop("Route not deleted.. Check the screenshot for more detail..!")
		} catch(Exception e) {
			KeywordUtil.markPassed("Route is deleted successfully. Deleted Route name is --> " + routeName)
		}
	}

	public void editRoute(String route,String updateRoute) {
		switchFrameAndDoActions("route", "//*[text()='"+route+"']", "click",findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/routeName_TextBox'), updateRoute)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocumentRoute_Page/save_Button'))
	}

	public void routeDeletion(String route) {
		pageRecordSizeChangeInRouteHomePage("100")
		switchFrameAndDoActions("route", "//*[text()='"+route+"']", "click",findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
		WebUI.click(findTestObject("Object Repository/DocPro_Module/DocumentRoute_Page/delete_Button"))
		WebUI.click(findTestObject("Object Repository/Suite_Module/Groups_Page/popUpOk_Button"))
		KeywordUtil.logInfo("Route deleted sucessfully..!")
	}

	@When("Create new Route")
	public void createNewRoute() {
		if (getData("Route name").equalsIgnoreCase("random")) {
			routeName = "Automation-" + dateTimeStr;
		}
		routeCreation(routeName,"Empcode",code)
	}

	@When("Validate Document Route creation")
	public void validateDocumentRouteCreation() {
		validateRouteCreation(routeName)
	}

	@When("Validate Document Route details")
	public void validateDocumentRouteDetails() {
		validateRouteCreation(routeName)
	}

	@When("Edit Route details")
	public void editRouteDetails() {
		editRoute(routeName,routeName+"_Update")
		routeName = routeName+"_Update";
	}

	@When("Delete Route")
	public void deleteRoute() {
		routeDeletion(routeName)
		//validateRouteDeletion(routeName)
	}
}