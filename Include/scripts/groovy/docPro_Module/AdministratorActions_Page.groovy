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
class AdministratorActions_Page extends CommonClass {

	public void clickOnAssignRoute() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignRoute_Option'))
		KeywordUtil.logInfo("Clicked on Assign Route option")
	}

	public void clickOnAssignAuthor() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignAuthor_Option'))
		KeywordUtil.logInfo("Clicked on Assign Author option")
	}



	public void searchDocAndClickAssignInAssignRoute(String searchOpt , String docNumber) {

		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchOptionInAssignRoute_DropDown'), searchOpt, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchInAssignRoute_TextBox'), docNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='assignRouteGrid']/tbody/tr["+i+"]/td[1]/div")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(docNumber)) {
				switchFrameAndDoActions("assign", "//*[@id='assignRouteGrid']/tbody/tr["+i+"]/td[5]/div/a", "jsClick", findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'))
				KeywordUtil.logInfo("Document found and Clicked on Assign..!")
				break;
			}
		}
	}

	public void searchDocAndClickAssignInAssignAuthor(String searchOpt , String docNumber) {

		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignAuthor_DropDown'), searchOpt, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchInAssignAuthor_TextBox'), docNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='assignAuthorGrid']/tbody/tr["+i+"]/td[1]/div")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(docNumber)) {
				switchFrameAndDoActions("assign", "//*[@id='assignAuthorGrid']/tbody/tr["+i+"]/td[5]/div/a", "jsClick", findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'))
				KeywordUtil.logInfo("Document found and Clicked on Assign..!")
				break;
			}
		}
	}


	public void searchAndSelectUser(String search,String data) {
		Boolean flag = false;
		Thread.sleep(3000)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchUser_DropDown'), search, false)
		WebUI.sendKeys(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchUser_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/ifrUsersActions_Iframe'), 15)
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
			KeywordUtil.logInfo("User data found and selected..!")
		} else {
			throw new Error("User data not found.. Please check the data !");
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/done_Button'))
	}

	public void assignRoute(String route) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignRoute_DropDown'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchInApprovalRoute_TextBox'), route+Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/saveInAssignRoute_Button'))
		KeywordUtil.logInfo("Route Assigned successfully -- > " + route)
	}

	public void assignAuthor(String searchOption , String author) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignAuthor_Icon'))
		searchAndSelectUser(searchOption, author)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/saveInAssignAuthor_Button'))
	}

	@And('Assign route for created request')
	public void assignRouteForCreatedRequest() {
		clickOnAssignRoute()
		searchDocAndClickAssignInAssignRoute("Document Number", NewDocRequest_Page.docNumber)
		assignRoute(getData("Assign route"))
	}

	@And("Assign author for created request")
	public void assignAuthorForCreatedRequest() {
		clickOnAssignAuthor()
		searchDocAndClickAssignInAssignAuthor("Document Number", NewDocRequest_Page.docNumber)
		assignAuthor("Empcode", getData("Code"))
	}
}