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
import groovy.util.ObservableList.ElementAddedEvent

import org.openqa.selenium.Keys



/**
 * @author sibis
 *
 */
class NewDocRequest_Page extends CommonClass{
	public static String docNumber = getData("Doc number");
	public static String docName = getData("Doc name");

	public void siteSelection(String siteName) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/site_DropDown'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/City_Page/searchCountryState_TextBox'), siteName+Keys.ENTER)
		KeywordUtil.logInfo("Site selected successfully --> "+ siteName)
	}

	public void levelSelection(String levelName) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/selectLevel_Button'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/levelSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("lev", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/DocPro_Module/ifrpopuplevel_IFrame'))
		KeywordUtil.logInfo("Level selected successfully --> "+ levelName)
	}

	public void docPrefixValidation(String expected) {
		if(!expected.isEmpty()) {
			String txt = WebUI.getText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docPrefix_Text'));
			if(txt.equals(expected)) {
				KeywordUtil.logInfo("Document prefix matched..! --> Actuals is "+ txt)
			} else {
				KeywordUtil.markErrorAndStop("Document prefix is not matched..! --> Actuals is "+ txt)
			}
		}
	}

	public void enterDocNumber(String docNumber) {
		try {
			if(!WebUI.getAttribute(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), "readonly").equals("readonly")) {
				WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), docNumber)
				KeywordUtil.logInfo("Document number is entered successfully --> "+docNumber)
			}
		} catch(Exception e) {
			KeywordUtil.logInfo("Doc number is already generated and entered..!")
		}
	}

	public void enterDocName(String docName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docName_TextBox'), docName)
		KeywordUtil.logInfo("Document name is entered successfully --> "+docName)
	}

	public void uploadFile(String path) {
		if(!path.isEmpty()) {
			WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docUpload_Button'), path)
			KeywordUtil.logInfo("Document uploaded successfully --> "+path)
		} else {
			KeywordUtil.logInfo("Proceeding without document..!")
		}
	}

	public void enterRevisionNum(String rev) {
		if(!rev.isEmpty()) {
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/revValue_TextBox'), rev)
			KeywordUtil.logInfo("Entered revision value is  --> "+rev)
		}
	}

	public void validateDocUploadStatus(){

		WebUI.waitForElementAttributeValue(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docUploadStatus_Icon'), "id", "imgCompleted", 30)
		Thread.sleep(5000);
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		List<WebElement> ele = DriverFactory.getWebDriver().findElements(By.xpath("//tr[contains(@id,'tr')]/td/div[contains(@id,'divcam')]/img"));
		for(int i=0;i<ele.size();i++) {
			if(ele.get(i).getAttribute("id").equals("imgCompleted")) {
				KeywordUtil.markPassed("All documents are uploaded successfully")
			} else {
				KeywordUtil.markFailedAndStop("Documents upload Failed. Please check..!")
			}
		}
	}

	public void addDocs() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/New Documnet Request/addDocument_Button'))
		Thread.sleep(4000)
	}

	public void validateDocNum(String expected) {
		String txt;
		if(getData("Doc num option").equals("User Defined Document Number")) {
			txt = WebUI.getText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox')).trim()
			if(txt.isEmpty()) {
				KeywordUtil.markPassed("Doc number is empty-- PASSED")
			} else {
				KeywordUtil.markFailedAndStop("Documnet number text box has value-- FAILED. Actual is  "+txt)
			}
		} else if (getData("Doc num option").contains("Use Internal Document ID As Document Number")){
			WebUI.verifyElementAttributeValue(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), "readonly", "true", 20)
			KeywordUtil.markPassed("Doc number used internal document as ID-- PASSED")
		}
	}

	@When("Create new doc with details")
	public void createNwDocWithDetails() {
		siteSelection(getData("Site name"))
		levelSelection(Levels_Page.levelName)
		if (getData("Doc number").equalsIgnoreCase("random")) {
			docNumber =  dateTimeStr;
		}
		//enterDocNumber(docNumber)

		if (getData("Doc name").equalsIgnoreCase("random")) {
			docName = "Test Automation - " + dateTimeStr;
		}
		enterDocName(docName)
		enterRevisionNum(getData("Revision"))
		uploadFile(getData("File path"))
		addDocs()
		if(getData("File path").isEmpty()) {
			WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
			WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
			KeywordUtil.logInfo("Proceeding without document")
		}
		//	validateDocUploadStatus()
	}

	@Then("Validate the document number based on document number option")
	public void validateTheDocumentNumberBasedOnDocumentNumberOption() {
		String expected=getData("Expected doc num");
		validateDocNum(expected)
	}
}