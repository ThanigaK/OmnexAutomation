package docPro

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

public class PreferencePage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String SupplierName;
	static String SupplierCode;
	static String id;

	@Keyword
	public void updateThePreferencesSettingsBasedOnExcelData(String SessionTimeout, String PageSize, String DefaultLanguage, String DateFormat) {
		if(!SessionTimeout.isEmpty() && !SessionTimeout.equals("0")) {
			WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/sessionTimeOut_Textbox'), SessionTimeout)
		}

		if(!PageSize.isEmpty() && !PageSize.equals("0")) {
			WebUI.setText(findTestObject('Suite_Module/UserPreferences_Page/pageSize_TextBox'), PageSize)
		}

		if(!DefaultLanguage.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Suite_Module/UserPreferences_Page/defaultLanguage_DropDown'),DefaultLanguage , true)
		}

		if(!DateFormat.isEmpty()) {
			String[] data =DateFormat.split("/");
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat1_DropDown'), data[0], true)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat2_DropDown'), data[1], true)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UserPreferences_Page/dateFormat3_DropDown'), data[2], true)
		}

		WebUI.click(findTestObject('Suite_Module/UserPreferences_Page/save_Button'))

		WebUI.verifyElementPresent(findTestObject('Suite_Module/UserPreferences_Page/PreferencesSettingUpdateSuccessMessage'), 15)
	}
	
	@Keyword
	public void validateTimezoneUnderProfileIcon(String Format) {
		WebUI.refresh();
		WebUI.click(findTestObject('Home_Page/userProfile_Icon'))
		String time = WebUI.getText(findTestObject('Home_Page/time_Text'))
		compareDateWithCurrentDate(time, Format);
	}
	
	public static void compareDateWithCurrentDate(String dateFromApplication,String Format) {
		
				SimpleDateFormat formatter = new SimpleDateFormat(Format.replace('DD', 'dd'));
				Date date = new Date();
				if(dateFromApplication.contains(formatter.format(date))) {
					KeywordUtil.logInfo("Date format matched..!")
				} else {
					KeywordUtil.markFailed("Date format not matched..! Actual date is--> "+dateFromApplication)
				}
			}

	
}
