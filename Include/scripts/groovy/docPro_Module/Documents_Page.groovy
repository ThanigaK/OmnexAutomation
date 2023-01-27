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
class Documents_Page extends CommonClass{
	int place =0;

	public void openLevel(String levelName) {
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/folderSearch_TextBox'), levelName+Keys.ENTER)
		switchFrameAndDoActions("lev", "//*[text()='"+levelName+"']", "jsclick", findTestObject('Object Repository/Home_Page/detailView_iFrame'))
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

	public void rightClickOnDoc() {
		switchFrameAndDoActions("doc", "//*[@id='DPTOCgrid']/tbody/tr["+place+"]/td[3]/div/a", "rightclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
	}


	public void clickOnRightClickOptions(String option) {
		switchFrameAndDoActions("option", "//*[text()='"+option+"']", "jsclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
		KeywordUtil.logInfo(option + " is clicked successfully..!")
	}

	public void validateRevisionCount(String expected){
		String val = WebUI.getAttribute(findTestObject('Object Repository/DocPro_Module/Documents_Page/revision_TextBox'), "value")

		if(val.equalsIgnoreCase(expected)) {
			KeywordUtil.logInfo("Revision count is matched. Actual --> "+ val)
		} else {
			KeywordUtil.markErrorAndStop("Revision count is not matched. Actual --> "+ val)
		}
	}

	public void selectFavFolder(String folderName) {
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Documents_Page/selectFavFolder_Icon'))
		switchToNextWindow();
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Documents_Page/searchFav_TextBox'), folderName +Keys.ENTER)
		DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='"+folderName+"']")).click()
		WebUI.switchToWindowTitle('EwQIMS')
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Documents_Page/addToFavouritesPlus_Icon'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/Documents_Page/addedToFav_SuccessMsg'))
		KeywordUtil.logInfo("Document sucessfully added to the selected folder.. "+NewDocRequest_Page.docNumber)
	}

	public void searchAndSelectDoc(String search , String doc) {
		int place = searchDocWithValidation(search,doc);
		switchFrameAndDoActions('chkbox', "(//*[@name='DPTOCgrid_selectCheck'])["+String.valueOf(place)+"]", "jsclick", findTestObject('Object Repository/DocPro_Module/tree_Iframe'))
	}

	@When("Validate revision number in (.*)")
	public void validateRevisionNumberInDocumentsPage(String option) {
		openLevel(Levels_Page.levelName)
		searchDocWithValidation("Document Number",NewDocRequest_Page.docNumber)
		rightClickOnDoc()
		clickOnRightClickOptions(option)
		validateRevisionCount(getData("Expected revision"))
	}

	@When("Validate uploaded document in Documents TOC")
	public void validateUploadedDocumentInDocumentsTOC() {
		openLevel(Levels_Page.levelName)
		searchDocWithValidation("Document Number",NewDocRequest_Page.docNumber)
	}

	@When("Add created document as favourite document")
	public void addCreatedDocumentAsFavouriteDocument() {
		openLevel(Levels_Page.levelName)
		searchAndSelectDoc("Document Number",NewDocRequest_Page.docNumber)
		selectFavFolder(Favourites_Page.favFolderName)
	}
}