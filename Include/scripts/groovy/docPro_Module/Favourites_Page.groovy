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



/**
 * @author sibis
 *
 */
class Favourites_Page extends CommonClass{
	public static String favFolderName=getData('Fav folder name');

	public void createFavFolder(String folderName){
		WebUI.mouseOver(findTestObject('Object Repository/DocPro_Module/Favourites_Page/favoritesParentFolder'))
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Favourites_Page/addFolder_Icon'))
		WebUI.setText(findTestObject('Object Repository/DocPro_Module/Favourites_Page/favFoldername_TextBox'), folderName)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Favourites_Page/submitFav_Button'))
		KeywordUtil.logInfo("Favourite folder successfully created --> " + folderName)
	}

	public void gotoFavFolder(String folderName) {
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		DriverFactory.getWebDriver().findElement(By.xpath("//span[text()='"+folderName+"']")).click()
		WebUI.switchToDefaultContent()
	}

	public void deleteFavFolder(String folderName) {
		gotoFavFolder(folderName)
		WebUI.click(findTestObject('Object Repository/DocPro_Module/Favourites_Page/deleteFavFolder_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/DocPro_Module/Favourites_Page/favFolderdeletionMsg'))
		KeywordUtil.logInfo("Favourite folder successfully deleted --> " + folderName)
	}

	@When("Create Favourite folder")
	public void createFavouriteFolder() {
		if(favFolderName.equalsIgnoreCase("random")) {
			favFolderName=	"Automation-" + dateTimeStr;
		}
		createFavFolder(favFolderName)
	}

	@When("Validate the document in favourite folder")
	public void validateTheDocumentInFavouriteFolder() {
		gotoFavFolder(favFolderName)
		new Documents_Page().searchDocWithValidation("Document Number", NewDocRequest_Page.docNumber)
	}

	@When("Delete Favourite folder")
	public void deleteFavouriteFolder() {
		gotoFavFolder(favFolderName)
		deleteFavFolder(favFolderName)
	}
}