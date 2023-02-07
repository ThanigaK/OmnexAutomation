package suiteModule

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

import cucumber.api.java.en.And

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
	public static String favFolderName;
	int place =0;

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

	@Keyword
	public void NavigateToAdminActionsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/administratorActions_Option'), 15)
	}

	@Keyword
	public void assignAuthorForCreatedRequest(String author) {
		clickOnAssignAuthor()
		searchDocAndClickAssignInAssignAuthor("Document Number", DocPro.DocNumber)
		assignAuthor("Empcode", author)
	}

	public void clickOnAssignAuthor() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignAuthor_Option'))
		KeywordUtil.logInfo("Clicked on Assign Author option")
	}


	public void assignAuthor(String searchOption , String author) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignAuthor_Icon'))
		searchAndSelectUser(searchOption, author)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/saveInAssignAuthor_Button'))
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

	@Keyword
	public void attachDocumentInDocumentNeedingRevision(String RevisionDocPath) {
		clickDocumentNeedingRevision()
		searchDocInDocNeedingRevision("Document Number", DocPro.DocNumber)
		uploadDocument(RevisionDocPath)
	}

	public void clickDocumentNeedingRevision() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/docNeedingRevision_Option'))
		KeywordUtil.logInfo("Clicked on Request Needing Revision option")
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

	public void uploadDocument(String path) {
		WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/Actions_Page/fileUpload_Button'), path)
		KeywordUtil.logInfo("File uploaded successfully -->"+path)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/save_Button'))
	}

	@Keyword
	public void assignUserInReassignApproval(String ReApprover, String Password) {
		clickReqNeedingApproval()
		searchDocReqNeedingApproval("Document Number", DocPro.DocNumber)
		reassignApprover(ReApprover, 'Automation Action', Password)
	}

	public void clickReqNeedingApproval() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/reqNeedingApproval_Option'))
		KeywordUtil.logInfo("Clicked on Request Needing Approval option")
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

	public void reassignApprover(String user,String reason,secondPassword) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/reassignApproval_RadioButton'))
		WebUI.sendKeys(findTestObject('Object Repository/DocPro_Module/Actions_Page/richTextInReqNeedinApproval'), reason)
		waitForClickableAndClick(findTestObject('Object Repository/DocPro_Module/Actions_Page/selectUser_Icon'), 15)
		searchAndSelectUser('Empcode', user)
		WebUI.setEncryptedText(findTestObject('Object Repository/DocPro_Module/Actions_Page/secondPassword_TextBox'), secondPassword)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/submit_Button'))
	}

	@Keyword
	public void addCreatedDocumentAsFavouriteDocument(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/folderSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("lev", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/Home_Page/detailView_iFrame'))
		int place = searchDocWithValidation("Document Number",DocPro.DocNumber);
		switchFrameAndDoActions('chkbox', "(//*[@name='DPTOCgrid_selectCheck'])["+String.valueOf(place)+"]", "jsclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Documents_Page/selectFavFolder_Icon'))
		switchToNextWindow();
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/searchFav_TextBox'), favFolderName +Keys.ENTER)
		DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='"+favFolderName+"']")).click()
		WebUI.switchToWindowTitle('EwQIMS')
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Documents_Page/addToFavouritesPlus_Icon'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/Documents_Page/addedToFav_SuccessMsg'))
		KeywordUtil.logInfo("Document sucessfully added to the selected folder.. "+DocPro.DocNumber)
	}

	public void searchAndSelectDoc(String search , String doc) {
		int place = searchDocWithValidation(search,doc);
		switchFrameAndDoActions('chkbox', "(//*[@name='DPTOCgrid_selectCheck'])["+String.valueOf(place)+"]", "jsclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
	}

	public int searchDocWithValidation(String searchOption, String doc) {
		String val;

		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/tree_Iframe'), 15)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(size>1) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/Documents_Page/filtercolumn_DropDown'), searchOption, false)
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/search_TextBox'), doc)
		}

		Thread.sleep(5000)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/tree_Iframe'), 20)
		int size1 = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size1;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='DPTOCgrid']/tbody/tr["+i+"]/td[3]/div/a")).getText();
			WebUI.switchToDefaultContent()
			if(val.contains(doc)) {
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
	public void createFavouriteFolder(String FolderName) {
		if(FolderName.equalsIgnoreCase("random")) {
			favFolderName=	"Automation-" + DateTimeStr;
		}
		createFavFolder(favFolderName)
	}

	public void createFavFolder(String folderName){
		WebUI.mouseOver(findTestObject('Object Repository/DocPro_Module/Favourites_Page/favoritesParentFolder'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Favourites_Page/addFolder_Icon'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Favourites_Page/favFoldername_TextBox'), folderName)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Favourites_Page/submitFav_Button'))
		KeywordUtil.logInfo("Favourite folder successfully created --> " + folderName)
	}

	@Keyword
	public void assignRouteForCreatedRequest(String AssignRoute) {
		clickOnAssignRoute()
		searchDocAndClickAssignInAssignRoute("Document Number", DocPro.DocNumber)
		assignRoute(AssignRoute)
	}

	public void clickOnAssignRoute() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignRoute_Option'))
		KeywordUtil.logInfo("Clicked on Assign Route option")
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

	public void assignRoute(String route) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/assignRoute_DropDown'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/searchInApprovalRoute_TextBox'), route+Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/AdministratorActions_Page/saveInAssignRoute_Button'))
		KeywordUtil.logInfo("Route Assigned successfully -- > " + route)
	}

	@Keyword
	public void resubmitTheRejectedRequestInResubmitRejectedRequest(String RevisedPath) {
		clickOnResubmitRejectedRequest()
		searchDocAndClickResubmitRejectedRequest("Document Number", DocPro.DocNumber)
		resubmitRejectedRequest(RevisedPath)
	}

	public void clickOnResubmitRejectedRequest() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/ResubmitRejectedRequest_Option'))
		KeywordUtil.logInfo("Clicked on Resubmit Rejected Request option")
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

	public void resubmitRejectedRequest(String path) {
		WebUI.uploadFile(findTestObject('Object Repository/DocPro_Module/Actions_Page/attachDoc_Button'), path)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Actions_Page/continue_Button'))
		KeywordUtil.logInfo("Document uploaded and resubmitted the request. Doc path --> "+path)
	}
}
