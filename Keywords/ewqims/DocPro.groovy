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

public class DocPro extends common {
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
	public void goToLevelInDocproSetup(String page, String Level) {
		if(page.equalsIgnoreCase("Folder management")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/folderManagement_Option'))
		} else if (page.equals("Document management")) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/documentManagement_Option'))
		}
		goToLevel(Level)
	}

	public void goToLevel(String levelName) {
		switchFrameAndDoActions("level", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
	}

	@Keyword
	public void AddLevelOwner(String search, String data){
		if(!data.isEmpty()) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/addLevelOwner_Icon'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/addLevelOwner_Icon'))
			searchAndSelectUser(search,data)
			KeywordUtil.logInfo("Level Owner selected --> "+data)
		}
	}

	public void searchAndSelectUser(String search,String data) {
		Boolean flag = false;
		Thread.sleep(3000)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchUserOption_DropDown'), search, false)
		WebUI.sendKeys(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchUser_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/ifrUsers_IFrame'), 15)
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
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/doneButtonInTemplate_Button'))
	}

	@Keyword
	public void tagSelection(String tag) {
		if(!tag.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/tagSelection_Link'))
			tableSizeUpdateInTagSelection('100')
			selectTag(tag);
		}
	}

	public void tableSizeUpdateInTagSelection(String size) {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/dataTableSizeInTagSelection_DropDown'), 15)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/dataTableSizeInTagSelection_DropDown'), size, false)
	}

	public void selectTag(String tag) {
		boolean flag = false;
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/ifrselecttags_IFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(tag)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("Tag found and selected.. !")
		} else {
			throw new Error("Tag not found.. Please check the data !");
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/tagSelectionDone_Button'))
	}

	@Keyword
	public void levelpdfPrefSelection(String search,String pdf) {
		if(!pdf.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/levelPdfPrefernce_Link'))
			searchAndSelectPdfPref(search,pdf)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/saveInpdfDocType_Button'))
			WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/pdfDocTypeSaveSuccessMsg'))
			KeywordUtil.logInfo("Level pdf preference selection is successful.. ! Success message verified..!")
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/closeInLevPdfPreferences_Icon'))
		}
	}

	public void searchAndSelectPdfPref(String search, String data) {
		boolean flag = false;
		WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/pdfDocTypeSearch_DropDown'), search, false)
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchPdf_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeWater_IFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridModPDFDocTypes']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(data)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='gridModPDFDocTypes']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("pdf document type found and selected.. !")
		} else {
			throw new Error("pdf document type not found.. Please check the data !");
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/update_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/pdfDocTypeUpdateSuccessMsg'))
		KeywordUtil.logInfo("Pdf document type selection is successful.. ! Success message verified..!")
	}

	@Keyword
	public void setAutoPublish(String size, String module) {
		if(!module.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/moduleAutoPublish_Link'))
			tableSizeUpdate(size);
			selectModule(module)
			KeywordUtil.logInfo("Module selected for Auto publish --> "+module)
		}
	}

	public void tableSizeUpdate(String size) {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/datTableSize_DropDown'), 15)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/datTableSize_DropDown'), size, false)
	}

	public void selectModule(String module) {
		boolean flag = false;
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/menuTree_IFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='ModuleGrid']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(module)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='ModuleGrid']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("Module found and selected.. !")
		} else {
			throw new Error("Module not found.. Please check the data !");
		}

		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/autoPublishDone_Button'),15)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/autoPublishDone_Button'))
	}

	@Keyword
	public void EnterDocNumber(String Doc) {
		if (Doc.toString().equalsIgnoreCase("random")) {
			DocNumber =  DateTimeStr;
			if(!WebUI.getAttribute(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), "readonly").equals("readonly")) {
				WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), DocNumber)
				KeywordUtil.logInfo("Document number is entered successfully --> "+DocNumber)
			}
		}
	}

	@Keyword
	public void EnterDocName(String Doc) {
		if (Doc.equalsIgnoreCase("random")) {
			docName = "Test Automation - " + DateTimeStr;
		}

		WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docName_TextBox'), docName)
		KeywordUtil.logInfo("Document name is entered successfully --> "+docName)
	}

	@Keyword
	public void enterRevisionNum(String rev) {
		if(!rev.isEmpty()) {
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/revValue_TextBox'), rev)
			KeywordUtil.logInfo("Entered revision value is  --> "+rev)
		}
	}

	@Keyword
	public void uploadFile(String path) {
		if(!path.isEmpty()) {
			WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docUpload_Button'), path)
			KeywordUtil.logInfo("Document uploaded successfully --> "+path)
		} else {
			KeywordUtil.logInfo("Proceeding without document..!")
		}
	}

	@Keyword
	public void validateDocNum(String expected) {
		String txt;
		if(expected.equals("User Defined Document Number")) {
			txt = WebUI.getText(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox')).trim()
			if(txt.isEmpty()) {
				KeywordUtil.markPassed("Doc number is empty-- PASSED")
			} else {
				KeywordUtil.markFailedAndStop("Documnet number text box has value-- FAILED. Actual is  "+txt)
			}
		} else if (expected.contains("Use Internal Document ID As Document Number")){
			WebUI.verifyElementAttributeValue(findTestObject('Object Repository/DocPro_Module/New Documnet Request/docNumber_TextBox'), "readonly", "true", 20)
			KeywordUtil.markPassed("Doc number used internal document as ID-- PASSED")
		}
	}

	@Keyword
	public int DeleteAddedDocument() {
		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/tree_Iframe'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/Documents_Page/filtercolumn_DropDown'), "Document Number", false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/search_TextBox'), DocNumber)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/tree_Iframe'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='DPTOCgrid']/tbody/tr["+i+"]/td[3]/div/a")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(DocNumber)) {
				place = i;
				KeywordUtil.logInfo("Document found..!")
				break;
			}
		}
		if(place==0) {
			KeywordUtil.markErrorAndStop("Document not available.. Failed..!")
		}
		return place;
	}

	@Keyword
	public void rightClickOnDoc() {
		switchFrameAndDoActions("doc", "//*[@id='DPTOCgrid']/tbody/tr["+place+"]/td[3]/div/a", "rightclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
	}

	@Keyword
	public void clickOnRightClickOptions(String option) {
		switchFrameAndDoActions("option", "//*[text()='"+option+"']", "jsclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
		KeywordUtil.logInfo(option + " is clicked successfully..!")
	}

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

	@Keyword
	public void MakeLevelInUse() {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/inUse_CheckBox'), 15)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/inUse_CheckBox'))
		KeywordUtil.logInfo("In use check box is clicked.")
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))
		KeywordUtil.logInfo("Save button clicked..!")
	}

	@Keyword
	public void approveOrrejectRequestInRequestNeedingApproval(String action, String SecondPassword) {
		clickReqNeedingApproval()
		searchDocReqNeedingApproval("Document Number", DocNumber)
		approveOrRejectReq(action,SecondPassword,'Automation Action')
	}

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

	@Keyword
	public void moveAllAvailableFiles(String toLevel) {
		moveAllDoc(toLevel)
	}

	public void moveAllDoc(String levelName) {
		Thread.sleep(2000)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/selectAllDocInDocManagement_CheckBox'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/levelSelection_Icon'))
		selectLevel(levelName)
		Thread.sleep(2000)
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/move_Button'), 15)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/move_Button'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/moveReason_TextBox'), "Moving files")
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/reasonForMoveOK_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/tableEmpty'))
		KeywordUtil.logInfo("Documents Moved successfully..!")
	}

	public void selectLevel(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/levelSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions('level', "//span[contains(@id,'tvDoc') and text()='"+levelName+"']", 'click', findTestObject('Object Repository/DocPro_Module/ifrpopuplevelTreeMenudata_IFarme'))
		KeywordUtil.logInfo("Level selected succesfully..!")
	}
	
	@Keyword
	public void AssignAutoApprovalRoute(String resetLevel, String resetSubLevelRoute, String RouteName) {
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
		if (!(RouteName.toString().isEmpty())) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/newRoute_DropDown'))

			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'),
					RouteName + Keys.ENTER)
		}

		'Assign Value for Existing Route"'
		if (!(RouteName.toString().isEmpty())) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/existingRoute_DropDown'))

			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'),
					RouteName + Keys.ENTER)
		}

		'Close the Route Option window'
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/popupClose_Icon'))
	}
}
