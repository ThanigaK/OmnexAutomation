package auditProModule

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit

import internal.GlobalVariable

public class common {

	public static String VendorName
	public static String VendorCode

	public String RandomNumber() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String DateTimeStr = sdf1.format(timestamp);
		return DateTimeStr;
	}

	@Keyword
	public void createVendorandVerify() {
		VendorName = "AutoVendor"+RandomNumber()
		VendorCode = "AutoCode"+RandomNumber()
		WebUI.click(findTestObject('Object Repository/Vendor_Page/add_Button'))
		WebUI.waitForPageLoad(5)
		WebUI.setText(findTestObject('Object Repository/Vendor_Page/vendorName_TextBox'),VendorName)
		WebUI.setText(findTestObject('Object Repository/Vendor_Page/vendorCode_TextBox'),VendorCode)
		WebUI.setText(findTestObject('Object Repository/Vendor_Page/vendorPhone_TextBox'),VendorCode)
		WebUI.setText(findTestObject('Object Repository/Vendor_Page/vendorEmail_TextBox'),VendorCode)
		WebUI.click(findTestObject('Object Repository/Vendor_Page/html_Element'))
	}

	@Keyword
	public void deleteVendor() {
	}
}

