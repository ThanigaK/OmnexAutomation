package suite_module
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
import org.openqa.selenium.interactions.Actions
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
class Tags_Page extends CommonClass{
	String tagName = getData("Tag name");

	public void tagCreation(String tagName) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Tags_Page/tags_TextBox'), tagName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/save_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'), 15)
		KeywordUtil.logInfo("Tag is created Successfully..! Tag name is : "+tagName)
	}

	public void tagNameModification(String name) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/editTag_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Tags_Page/tags_TextBox'), name)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/save_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/Tags_Page/updateMessage_Text'), 15)
		KeywordUtil.logInfo("Tag name is updated Successfully..! Tag name is : "+name)
	}

	public void tagDeletion(String name) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/delete_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'), 15)
		KeywordUtil.logInfo("Tag is deleted Successfully..! Tag name is : "+name)
	}

	public void searchTag(String name) {
		Boolean flag=false;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)


		WebUI.switchToDefaultContent()
		pageRecordSizeChange('100')

		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(name)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Tag data found and selected.. !")
		} else {
			throw new Error("Tag not found.. Please check the data ! Actual data is : " + val);
		}
	}

	@When("Create tag")
	public void createTag() {
		if (getData("Tag name").equalsIgnoreCase("random")) {
			tagName = "Automation - " + dateTimeStr;
		}
		tagCreation(tagName)
	}

	@When("Modify tag")
	public void modifyTag() {
		searchTag(tagName)
		tagName = "Updated-"+tagName;
		tagNameModification(tagName)
	}

	@When("Delete tag")
	public void deleteTag() {
		searchTag(tagName)
		tagDeletion(tagName)
	}
}