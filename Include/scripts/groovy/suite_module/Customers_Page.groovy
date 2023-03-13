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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;



/**
 * @author sibis
 *
 */
class Customers_Page extends CommonClass{

	String customerName = "";
	String customerCode = "";

	public void createCustomer() {
		if (getData("Customer name").equalsIgnoreCase("random")) {
			customerName = "Test Automation - " + dateTimeStr;
		} else {
			customerName = getData("Customer name");
		}

		if (getData("Customer code").equalsIgnoreCase("random")) {
			customerCode = "T-" + dateTimeStr;
		} else {
			customerCode = getData("Customer code")
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),20)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), customerCode)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'), customerName+Keys.TAB)

		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'), 30)
	}

	public void selectCustomer() {
		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(customerName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Customer data found and selected.. !")
		} else {
			throw new Error("Customer not found.. Please check the data !");
		}
	}

	@And('Create Customer and add address')
	public void createCustomerAndAddAddress() {
		createCustomer()
		new Manufacturer_Page().addAddress()
	}

	@And('Delete Customer')
	public void deleteCustomer() {
		pageRecordSizeChange("100")
		selectCustomer()
		new Manufacturer_Page().clickAndConfirmDeletion()
	}
}
