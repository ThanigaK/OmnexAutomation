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
class VendorOrRegistrar_Page extends CommonClass {

	String vendorName = "";
	String vendorCode = "";

	public void vendorCreation(String name, String code) {
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),20)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'), name)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), code+Keys.TAB)

		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'), 30)
	}

	@And("Create Vendor")
	public void createVendor() {
		if (getData("Vendor name").equalsIgnoreCase("random")) {
			vendorName = "Test Automation - " + dateTimeStr;
		} else {
			vendorName = getData("Vendor name")
		}

		if (getData("Vendor code").equalsIgnoreCase("random")) {
			vendorCode = "T-" + dateTimeStr;
		} else {
			vendorCode = getData("Vendor code")
		}
		vendorCreation(vendorName,vendorCode)
		new Manufacturer_Page().addAddress()
		new Manufacturer_Page().addContact()
	}
	@And("Delete Vendor")
	public void deleteVendor() {
		pageRecordSizeChange("100")
		new Manufacturer_Page().selectManufacturer(vendorName);
		new Manufacturer_Page().clickAndConfirmDeletion()
	}
}