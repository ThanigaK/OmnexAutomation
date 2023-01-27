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
class Supplier_Page extends CommonClass{

	String supplierName = "";
	String supplierCode = "";

	public void supplierCreation(String noOfUser,String email,String user) {

		if (getData("Supplier name").equalsIgnoreCase("random")) {
			supplierName = "Test Automation - " + dateTimeStr;
		} else {
			supplierName = getData("Supplier name")
		}

		if (getData("Supplier code").equalsIgnoreCase("random")) {
			supplierCode = "T-" + dateTimeStr;
		} else {
			supplierCode = getData("Supplier code")
		}
		Thread.sleep(3000)
		switchFrameAndDoActions("add button", "//*[@id = 'btnAdd' or @id='btnadd']", "jsclick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'),  supplierName)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), supplierCode)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/NoOfSupplier_TextBox'), noOfUser)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/email_TextBox'), email)

		addSQIManager(user)
		addSQIEngineer(user)

		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/manufacturingLocation_TextBox'), "Test Automation Location")

		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Supplier created Successfully..! Success message verified..!")
	}

	public void addSQIManager(String user) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/TeamSQManager_Icon'))
		new EntityAndEntityOwner_Page().pageRecordSizeChange('100')
		new EntityAndEntityOwner_Page().selectUser(user)
	}

	public void addSQIEngineer(String user) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/TeamSQEngineer_Icon'))
		new EntityAndEntityOwner_Page().pageRecordSizeChange('100')
		new EntityAndEntityOwner_Page().selectUser(user)
	}

	public void selectSupplier(String name) {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(name)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Supplier data found and selected.. !")
		} else {
			throw new Error("Supplier not found.. Please check the data ! Actual data is : " + val);
		}
	}

	public void clickAndConfirmDeletion() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/delete_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}


	@And("Create Supplier")
	public void createSupplier() {
		supplierCreation("10",email,code)
	}

	@And("Delete Supplier")
	public void deleteSupplier() {
		selectSupplier(supplierName)
		clickAndConfirmDeletion()
	}
}