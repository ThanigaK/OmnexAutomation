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
class EntityAndEntityOwner_Page extends CommonClass{
	String entityName ="";
	public void addContactPerson(String user) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/addContactPerson_Button'))
		pageRecordSizeChange('100')
		selectUser(user)
	}

	public void selectUser(String userCode) {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Meetings_Page/userTable_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(userCode)) {
				Boolean flag1 =DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).isSelected()
				if(!flag1){
					DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).click()
				} else {
					KeywordUtil.logInfo("User already selected..!")
				}
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("User data found and selected.. !")
		} else {
			throw new Error("User not found.. Please check the data ! Actual is --> " + val);
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/Done_Button'))
	}

	public void entityCreation(String entity){
		WebUI.setText(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/entityName_TextBox'), entity)
		addContactPerson(code)
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/entitySave_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Entity is created successfully.. Success message verified..!")
	}

	public void pageRecordSizeChange(String size) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/tableRecordSize_DropDown'), 20)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/tableRecordSize_DropDown'),
				size, true)
	}

	public void selectEntityOwner(String owner) {
		switchFrameAndDoActions("adminIcon", "//span[contains(text(),'"+entityName+"')]/img", "jsclick",findTestObject('Home_Page/detailView_iFrame'))
		pageRecordSizeChange("100")
		selectUser(owner)
	}

	public void entityDeletion() {
		switchFrameAndDoActions("entity", "//*[text()='"+entityName+"']", "rightclick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/deleteEntity_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
		KeywordUtil.logInfo("Entity deleted successfully.. ! Deletion success message verified..!")
	}

	@When('Create new Entity')
	public void createNewEntity() {
		if (getData("Entity name").equalsIgnoreCase("random")) {
			entityName = "Test-" + dateTimeStr;
		} else {
			entityName = getData("Entity name")
		}
		entityCreation(entityName)
	}

	@When('Assign entity owner')
	public void assignEntityOwner() {
		selectEntityOwner(code)
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Entity owner assigned.. Success message verified..!")
	}
	@When('Delete Entity')
	public void deleteEntity() {
		entityDeletion()
	}
}