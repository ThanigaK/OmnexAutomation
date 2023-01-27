package docPro_Module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

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



/**
 * @author sibis
 *
 */
class DocProSetup_Page extends CommonClass{

	public void goToFolderManagement() {
//		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
//			WebUI.click(findTestObject('Home_Page/menu_Icon'))
//		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/folderManagement_Option'))
	}

	public void goToDocumentManagement() {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/documentManagement_Option'))
	}

	public void goToLevel(String levelName) {
		switchFrameAndDoActions("level", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'))
	}

	public void inUse() {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/inUse_CheckBox'), 15)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/inUse_CheckBox'))
		KeywordUtil.logInfo("In use check box is clicked.")
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))
		KeywordUtil.logInfo("Save button clicked..!")
	}

	public void docReviewAfter(String timePeriod) {
		if(!timePeriod.isEmpty()) {
			String[] time = timePeriod.split("\\:")
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtDocReviewUnit_TextBox'), time[1])
			WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocReviewUnitName_DropDown'), time[0], false)
			KeywordUtil.logInfo("Doc review is updated --> "+timePeriod)
		}
	}

	public void revisionOption(String revisionOpt,String noOfSegments,String major){
		if(!revisionOpt.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOption_DropDown'))
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), revisionOpt+Keys.ENTER)
			if(revisionOpt.contains("Custom")){
				if(!noOfSegments.isEmpty()) {
					WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/noOfSegments_DropDown'), noOfSegments, false)
				}
				if(!major.isEmpty()) {
					WebUI.selectOptionByLabel(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevSegMajor_DropDown'), major, false)
				}
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/revisionOptOk_Button'))
			}
			KeywordUtil.logInfo("Revision Option is Selected --> "+revisionOpt)
		}
	}

	public void docNumOption(String docNumopt,String startNum,String incUnit){
		if(!docNumopt.isEmpty()) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpDocNumOpt_DropDown'))
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), docNumopt+Keys.ENTER)
			Thread.sleep(1000)
			if(docNumopt.contains("Document Number Auto Increment")){
				if(!startNum.isEmpty()) {
					WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtAutoDocNumStart_TextBox'), startNum)
				}
				if(!incUnit.isEmpty()) {
					WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/txtAutoDocNumUnit_textBox'), incUnit)
				}
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/docNumOptOK_Button'))
			}
			KeywordUtil.logInfo("Document Number Option is Selected --> "+docNumopt)
		}
	}

	public void revDateOpt(String revDateOpt){
		if(!revDateOpt.isEmpty()) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/drpRevDateOpt_DropDown'))
			WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDown_TextBox'), revDateOpt+Keys.ENTER)
			KeywordUtil.logInfo("Revision date Option is Selected --> "+revDateOpt)
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

	public void levelOwnerAdd(String search, String data){
		if(!data.isEmpty()) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/addLevelOwner_Icon'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/addLevelOwner_Icon'))
			searchAndSelectUser(search,data)
			KeywordUtil.logInfo("Level Owner selected --> "+data)
		}
	}

	public void records(String data){
		if(data.equalsIgnoreCase("yes")) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/records_Checkbox'))
			KeywordUtil.logInfo("Records checkbox clicked.")
		}
	}

	public void editRouteOptions(String resetLevel, String resetSubLevelRoute, String newRoute, String existing) {
		if(!newRoute.isEmpty() && !existing.isEmpty()) {
			WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'), 15)
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/route_Link'))

			if(resetLevel.equalsIgnoreCase("yes")) {
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetlevel_CheckBox'))
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))
				KeywordUtil.logInfo("Reset Level checkbox clicked..!")
			}

			if(resetSubLevelRoute.equalsIgnoreCase("yes")) {
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/resetsublevel_CheckBox'))
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/alertOk_Button'))
				KeywordUtil.logInfo("Reset Sub Level Routing checkbox clicked..!")
			}

			if(!newRoute.isEmpty()) {
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/newRoute_DropDown'))
				WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'), newRoute+Keys.ENTER)
			}

			if(!existing.isEmpty()) {
				WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/existingRoute_DropDown'))
				WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/searchBoxDropDownInRouteEdit_TextBox'), newRoute+Keys.ENTER)
			}
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/popupClose_Icon'))
			KeywordUtil.logInfo("Route options updated..! Selected Route is "+ newRoute)
		}
	}

	public void tableSizeUpdate(String size) {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/datTableSize_DropDown'), 15)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/datTableSize_DropDown'), size, false)
	}

	public void tableSizeUpdateInTagSelection(String size) {
		WebUI.scrollToElement(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/dataTableSizeInTagSelection_DropDown'), 15)
		WebUI.selectOptionByValue(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/dataTableSizeInTagSelection_DropDown'), size, false)
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

	public void setAutoPublish(String size, String module) {
		if(!module.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/moduleAutoPublish_Link'))
			tableSizeUpdate(size);
			selectModule(module)
			KeywordUtil.logInfo("Module selected for Auto publish --> "+module)
		}
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

	public void tagSelection(String tag) {
		if(!tag.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/tagSelection_Link'))
			tableSizeUpdateInTagSelection('100')
			selectTag(tag);
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

	public void searchAndSelectTemplate(String data) {

		boolean flag = false;
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/templateManagementSearch_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/DocPro_Module/iframeTempsel_IFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='TemplateManagementGrid']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(data)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='TemplateManagementGrid']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("Template found and selected.. !")
		} else {
			throw new Error("Template not found.. Please check the data !");
		}
		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/done_Button'))
		KeywordUtil.logInfo("Template selection is successful.. !")
	}

	public void templateSelection(String temp) {
		if(!temp.isEmpty()) {
			WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/templateSelection_Link'))
			searchAndSelectTemplate(temp);
		}
	}

	public void selectLevel(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/levelSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions('level', "//span[contains(@id,'tvDoc') and text()='"+levelName+"']", 'jsclick', findTestObject('Object Repository/DocPro_Module/ifrpopuplevelTreeMenudata_IFarme'))
		KeywordUtil.logInfo("Level selected succesfully..!")
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



	@When("Make the created level in use")
	public void makeTheCreatedLevelInUse() {
		inUse();
	}

	@When("Go to level in (.*)")
	public void goToLevelInDocproSetup(String page) {
		if(page.equalsIgnoreCase("Folder management")) {
			goToFolderManagement()
		} else if (page.equals("Document management")) {
			goToDocumentManagement()
		}
		goToLevel(Levels_Page.levelName)
	}


	@When("Update the level data in Folder Management")
	public void updateTheLevelDataInFolderManagement() {
		//goToLevel("Automation-20092022135931")
		docReviewAfter(getData("Review after"))

		if(getData("Revision option").contains(":")) {
			String[] str = getData("Revision option").split("\\:")
			revisionOption(str[0],str[1],str[2])
		} else {
			revisionOption(getData("Revision option"),"","")
		}

		if(getData("Doc num option").contains(":")) {
			String[] str1 = getData("Doc num option").split("\\:")
			docNumOption(str1[0], str1[1], str1[2])
		} else {
			docNumOption(getData("Doc num option"),"","")
		}

		revDateOpt(getData("Rev date option"))

		levelOwnerAdd("Empcode", getData("Level owner"))

		tagSelection(getData("Tag name"))

		records(getData("Records"))

		if(getData("Route edit").contains(":")) {
			String[] route = getData("Route edit").split("\\:")
			editRouteOptions(route[0],route[1],route[2],route[3])
		} else if(getData("Route name").contains("Random")){
			editRouteOptions("", "", DocumentRoute_Page.routeName, DocumentRoute_Page.routeName)
		}

		levelpdfPrefSelection("Document Type", getData("Level pdf preference"))

		templateSelection(getData("Template"))

		setAutoPublish("100",getData("Auto publish"))

		WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))
		KeywordUtil.logInfo("Save button clicked..!")
	}

	@When("Move all available files to (.*) level")
	public void moveAllAvailableFiles(String toLevel) {
		moveAllDoc(toLevel)
	}


}