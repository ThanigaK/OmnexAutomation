package ewqims

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit
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

public class Meetings extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String VendorName;
	static String VendorCode;
	static String Position;
	static String tag;
	static String Sub


	@Keyword
	public void createNewMeeting(String City, String Subject, String recurringMeeting, String FromTime, String ToTime) {



		if (Subject.equalsIgnoreCase("random")) {
			Subject = "Test Automation - " + DateTimeStr;
			Sub = Subject;
		}
		if(recurringMeeting.equalsIgnoreCase("yes")) {
			waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/recurringMeeting_Button'), 20)
		} else {
			waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'), 20)
			WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingDate_DatePicker'),""+Keys.ENTER)
		}

		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/venue_TextBox'), City)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingSubject_TextBox'), Subject)
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingCategory_DropDown'), "Business", false)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/fromTime_Option'))
		selectTime(FromTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/toTime_Option'))
		selectTime(ToTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingMinutes_Icon'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/richText_TextBox'), "Test Automation")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/close_Icon'))
		selectTeam()
		if(recurringMeeting.equalsIgnoreCase("yes")) {
			fillRecurringDetails("Days","3")
		}
		meetingAgenda("Test automation",FromTime,ToTime)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	@Keyword
	public void duplicateMeetingCreation() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_Button'))
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingSubject_Dropdown'), Sub, false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_DatePicker'),""+Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeetingSave_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_SuccessMessage'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	@Keyword
	public void DeleteMeeting() {
		pageRecordSizeChange("100")
		selectMeeting(Sub)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}

	public void fillRecurringDetails(String occurs,String recursEvery) {
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Meetings_Page/occurs_Dropdown'), occurs , false)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/recursEvery_Textbox'), recursEvery)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/startDate_DatePicker'),""+Keys.ENTER)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/endDate_DatePicker'),""+Keys.ENTER)
	}

	public void meetingAgenda(String topic,String fromTime, String toTime) {
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/addMeetingAgenda_Button'), 10)
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/addMeetingAgenda_Button'),10)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/txtTopic_TextBox'), topic)
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaFrom_Option'), 0)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaFrom_Option'))
		selectTime(fromTime)
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaTo_Option'), 10)
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaTo_Option'),10)
		selectTime(toTime)
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenter_Icon'), 10)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenter_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/firstUser_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenterAddDone_Button'))
	}

	@Keyword
	public void createTag(String TagName) {
		if (TagName.equalsIgnoreCase("random")) {
			tag = "Automation - " + DateTimeStr;
		}
		tagCreation(tag)
	}

	public void tagCreation(String tagName) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Tags_Page/tags_TextBox'), tagName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/save_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'), 15)
		KeywordUtil.logInfo("Tag is created Successfully..! Tag name is : "+tagName)
	}

	public void selectMeeting(String meetingSubject) {

		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(25)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tr/td/a)["+i+"]")).getText();
			if(val.equals(meetingSubject)) {
				DriverFactory.getWebDriver().findElement(By.xpath("(//tr/td/input)["+i+"]")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Meeting data found and selected.. !")
		} else {
			throw new Error("Meeting not found.. Please check the data !");
		}
	}
}
