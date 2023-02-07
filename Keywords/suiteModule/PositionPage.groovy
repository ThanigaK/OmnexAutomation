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

public class PositionPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String SupplierName;
	static String SupplierCode;
	static String id;

	@Keyword
	public void CreatePosition(String Site, String Position) {
		if(!Site.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject("Suite_Module/Positions_Page/site_DropDown"), Site, false)
		}
		Thread.sleep(3000)
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.sendKeys(findTestObject("Suite_Module/Positions_Page/postion_TextBox"), Position);
		WebUI.sendKeys(findTestObject("Suite_Module/Positions_Page/positionDescription_TextBox"), "Test Automation");
		switchFrameAndDoActions("saveButton", "//div/*[@id='btnSave']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
		KeywordUtil.logInfo("City creation is successful.. ! Success message verified..!")
	}

	@Keyword
	public void validatePositionCreation(String Site, String Position) {
		if(!Site.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject("Suite_Module/Positions_Page/site_DropDown"), Site, false)
		}
		Thread.sleep(2000)
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		System.out.println("Size is - " +rowSize)
		if(rowSize>1) {
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Position)
		}
		pageRecordSizeChange("100");
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row'])["+i+"]/td[2]/a")).getText()
			if(val.equals(Position)) {
				id=i;
				KeywordUtil.logInfo("Position data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(id!=0) {
			KeywordUtil.logInfo("Position found in the search option.. !")
		} else {
			throw new Error("Position not found in the search option.. !")
		}
	}

	@Keyword
	public void deletePosition() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='PositionGrid_selectCheck'])["+id+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
			KeywordUtil.logInfo("Position deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Position deletion is failed.. ! Success message is not displayed.. !")
		}
	}
}
