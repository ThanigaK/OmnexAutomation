import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the application\r\n'
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the meetings page\r\n'
CustomKeywords.'suiteModule.KeyWord.NavigateToMeetingsPage'()

'Create New meeting'
CustomKeywords.'suiteModule.Meetings.createNewMeeting'(City, Subject, recurringMeetingNo, FromTime, ToTime)

'Naviagting back to the meeting page'
CustomKeywords.'suiteModule.KeyWord.NavigateToMeetingsPage'()

'Duplicate the new meeting'
CustomKeywords.'suiteModule.Meetings.duplicateMeetingCreation'()

'Again Navigating to the meeting page'
CustomKeywords.'suiteModule.KeyWord.NavigateToMeetingsPage'()

'Delete the meeting'
CustomKeywords.'suiteModule.Meetings.DeleteMeeting'()

'Create the meeting'
CustomKeywords.'suiteModule.Meetings.createNewMeeting'(City, Subject, recurringMettingYes, FromTime, ToTime)

'Navigating to the meeting page'
CustomKeywords.'suiteModule.KeyWord.NavigateToMeetingsPage'()

'Delete the meeting'
CustomKeywords.'suiteModule.Meetings.DeleteMeeting'()
