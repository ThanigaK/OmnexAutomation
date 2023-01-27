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



/**
 * @author sibis
 *
 */
class Actions_Page extends CommonClass{

	public void clickReqNeedingApproval() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/reqNeedingApproval_Option'))
		KeywordUtil.logInfo("Clicked on Request Needing Approval option")
	}

	public void clickDocumentNeedingRevision() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/docNeedingRevision_Option'))
		KeywordUtil.logInfo("Clicked on Request Needing Revision option")
	}

	public void clickOnResubmitRejectedRequest() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/ResubmitRejectedRequest_Option'))
		KeywordUtil.logInfo("Clicked on Resubmit Rejected Request option")
	}

	public void searchDocReqNeedingApproval(String searchOpt , String docNumber) {
		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchOptionInReqNeedApproval_DropDown'), searchOpt, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchInReqNeedApproval_TextBox'), docNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='requestNeedingApprovalGrid']/tbody/tr["+i+"]/td[1]/div")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(docNumber)) {
				switchFrameAndDoActions("reply", "//*[@id='requestNeedingApprovalGrid']/tbody/tr["+i+"]/td[5]/div/a", "jsClick", findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'))
				KeywordUtil.logInfo("Document found and Clicked on Reply..!")
				break;
			}
		}
	}

	public void searchDocInDocNeedingRevision(String searchOpt , String docNumber) {
		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchInDocNeedingRevision_DropDown'), searchOpt, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchInDocNeedingRevision_TextBox'), docNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='documentNeedingRevisionGrid']/tbody/tr["+i+"]/td[1]/div")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(docNumber)) {
				switchFrameAndDoActions("reply", "//*[@id='documentNeedingRevisionGrid']/tbody/tr["+i+"]/td[5]/div/a", "jsClick", findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'))
				KeywordUtil.logInfo("Document found and Clicked on Attach document..!")
				break;
			}
		}
	}

	public void searchDocAndClickResubmitRejectedRequest(String searchOpt , String docNumber) {

		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchDocInResubmitRejectedRequest_DropDown'), searchOpt, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Actions_Page/searchInResubmitRejectRequest_TextBox'), docNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='resubmitRejectedRequestGrid']/tbody/tr["+i+"]/td[1]/div")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(docNumber)) {
				switchFrameAndDoActions("resubmit", "//*[@id='resubmitRejectedRequestGrid']/tbody/tr["+i+"]/td[5]/div/a", "jsClick", findTestObject('Object Repository/DocPro_Module/iframeActions_IFrame'))
				KeywordUtil.logInfo("Document found and Clicked on Resubmit..!")
				break;
			}
		}
	}

	public void approveOrRejectReq(String action,String secondPassword,String reason) {
		if(action.equalsIgnoreCase("approve")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/approve_RadioButton'))
		} else {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/reject_RadioButton'))
			WebUI.sendKeys(findTestObject('Object Repository/DocPro_Module/Actions_Page/richTextInReqNeedinApproval'), reason)
		}

		if(!secondPassword.isEmpty()) {
			WebUI.setEncryptedText(findTestObject('Object Repository/DocPro_Module/Actions_Page/secondPassword_TextBox'), secondPassword)
		}

		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/submit_Button'))

		KeywordUtil.logInfo("Document "+action.toUpperCase()+"ED successfully")
	}

	public void uploadDocument(String path) {
		WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/Actions_Page/fileUpload_Button'), path)
		KeywordUtil.logInfo("File uploaded successfully -->"+path)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/save_Button'))
	}

	public void resubmitRejectedRequest(String path) {
		WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/Actions_Page/attachDoc_Button'), path)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/continue_Button'))
		KeywordUtil.logInfo("Document uploaded and resubmitted the request. Doc path --> "+path)
	}


	public void reassignApprover(String user,String reason,secondPassword) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/reassignApproval_RadioButton'))
		WebUI.sendKeys(findTestObject('Object Repository/DocPro_Module/Actions_Page/richTextInReqNeedinApproval'), reason)
		waitForClickableAndClick(findTestObject('Object Repository/DocPro_Module/Actions_Page/selectUser_Icon'), 15)
		new AdministratorActions_Page().searchAndSelectUser('Empcode', user)
		WebUI.setEncryptedText(findTestObject('Object Repository/DocPro_Module/Actions_Page/secondPassword_TextBox'), secondPassword)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/submit_Button'))
	}

	@And("(.*) request in Request Needing Approval")
	public void approveOrrejectRequestInRequestNeedingApproval(String action) {
		clickReqNeedingApproval()
		searchDocReqNeedingApproval("Document Number", NewDocRequest_Page.docNumber)
		approveOrRejectReq(action,getData("Second password"),'Automation Action')
	}

	@And("Attach document in Document Needing Revision")
	public void attachDocumentInDocumentNeedingRevision() {
		clickDocumentNeedingRevision()
		searchDocInDocNeedingRevision("Document Number", NewDocRequest_Page.docNumber)
		uploadDocument(getData("Revision doc path"))
	}

	@And("Resubmit the Rejected request in Resubmit Rejected Request")
	public void resubmitTheRejectedRequestInResubmitRejectedRequest() {
		clickOnResubmitRejectedRequest()
		searchDocAndClickResubmitRejectedRequest("Document Number", NewDocRequest_Page.docNumber)
		resubmitRejectedRequest(getData("Revision doc path"))
	}

	@And("Assign user in Reassign Approval")
	public void assignUserInReassignApproval() {
		clickReqNeedingApproval()
		searchDocReqNeedingApproval("Document Number", NewDocRequest_Page.docNumber)
		reassignApprover(getData("Reassign approver"), 'Automation Action', getData("Second password"))
	}
}