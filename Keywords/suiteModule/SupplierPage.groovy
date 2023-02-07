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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
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
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.util.KeywordUtil

public class SupplierPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String SupplierName;
	static String SupplierCode;
	static String Mail;

	public void addSQIManager(String user) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/TeamSQManager_Icon'))
		selectUser(user)
	}

	public void addSQIEngineer(String user) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/TeamSQEngineer_Icon'))
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

	@Keyword
	public void CreateSupplier(String Name,String Code,String user) {
		Rand = DateTimeStr;
		Mail = "Test"+Rand+"@gmail.com";
		if (Name.equalsIgnoreCase("random")) {
			SupplierName = "Test Automation - " + Rand;
		} else {
			SupplierName = Rand
		}

		if (Code.equalsIgnoreCase("random")) {
			SupplierCode = "T-" + Rand;
		} else {
			SupplierCode = Rand
		}
		Thread.sleep(3000)
		switchFrameAndDoActions("add button", "//*[@id = 'btnAdd' or @id='btnadd']", "jsclick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'),  SupplierName)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), SupplierCode)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/NoOfSupplier_TextBox'), "10")
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/email_TextBox'), Mail)

		addSQIManager(user)
		addSQIEngineer(user)

		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/manufacturingLocation_TextBox'), "Test Automation Location")
		WebUI.delay(4)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/SupplierTypeDropDown'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Supplier_Page/SupplierTypeSearch'), "Other"+Keys.ENTER)

		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Supplier created Successfully..! Success message verified..!")
	}

	@Keyword
	public void DeleteSupplier() {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(SupplierName)) {
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

		WebUI.click(findTestObject('Object Repository/Suite_Module/Supplier_Page/delete_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}
}
