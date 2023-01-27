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

import org.openqa.selenium.Keys


/**
 * @author sibis
 *
 */
class Meetings_Page extends CommonClass{

	String meetingSubject=getData("Meeting subject");

	public void meetingAgenda(String topic,String fromTime, String toTime) {
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/addMeetingAgenda_Button'), 10)
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/addMeetingAgenda_Button'),10)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/txtTopic_TextBox'), topic)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaFrom_Option'))
		new Shift_Page().selectTime(fromTime)
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaTo_Option'), 10)
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingAgendaTo_Option'),10)
		new Shift_Page().selectTime(toTime)
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenter_Icon'), 10)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenter_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/firstUser_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/presenterAddDone_Button'))
	}

	public void selectTeam() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/selectTeam_Icon'))
		WebUI.switchToWindowIndex(1)
		WebUI.maximizeWindow()
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/existingTeam_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/employeeSelect_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/teamSave_Button'))
		WebUI.switchToWindowIndex(0)
	}

	public void createMeeting(String venue, String subject,String meetingCategory,String minutesMeeting,String recurringMeeting) {
		if (getData("Meeting subject").equalsIgnoreCase("random")) {
			meetingSubject = "Test Automation - " + dateTimeStr;
		}
		subject=meetingSubject;
		if(recurringMeeting.equalsIgnoreCase("yes")) {
			waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Meetings_Page/recurringMeeting_Button'), 20)
		} else {
			waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'), 20)
			WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingDate_DatePicker'),""+Keys.ENTER)
		}

		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/venue_TextBox'), venue)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingSubject_TextBox'), subject)
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingCategory_DropDown'), meetingCategory, false)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/fromTime_Option'))
		new Shift_Page().selectTime(getData("From time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/toTime_Option'))
		new Shift_Page().selectTime(getData("To time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingMinutes_Icon'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/richText_TextBox'), minutesMeeting)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/close_Icon'))
		selectTeam()
		if(recurringMeeting.equalsIgnoreCase("yes")) {
			fillRecurringDetails("Days","3")
		}
		meetingAgenda("Test automation",getData("From time"),getData("To time"))
		WebUI.click(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	public void duplicateMeetingCreation(String subject) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_Button'))
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Meetings_Page/meetingSubject_Dropdown'), subject, false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_DatePicker'),""+Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeetingSave_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Meetings_Page/duplicateMeeting_SuccessMessage'))
		KeywordUtil.logInfo("Success message verified..!")
	}

	public void selectMeeting(String meetingSubject) {

		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
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

	public void meetingDeletion() {
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

	@And('Create new meeting')
	public void createNewMeeting() {
		createMeeting(getData("City"),meetingSubject,"Business","Test Automation","no")
	}

	@And('Duplicate meeting')
	public void duplicateMeeting() {
		duplicateMeetingCreation(meetingSubject)
	}

	@And('Delete meeting')
	public void deleteMeeting() {
		new Manufacturer_Page().pageRecordSizeChange("100")
		selectMeeting(meetingSubject)
		meetingDeletion()
	}

	@And('Create recurring meeting')
	public void createRecurringMeeting() {
		createMeeting(getData("City"),meetingSubject,"Business","Test Automation","yes")
	}
}