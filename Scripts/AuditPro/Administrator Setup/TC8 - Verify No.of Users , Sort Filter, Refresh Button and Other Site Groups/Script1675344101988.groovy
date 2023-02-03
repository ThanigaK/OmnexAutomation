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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.junit.Assert as Assert
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys

'Login to the application as module admin user'
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

WebUI.click(findTestObject('Home_Page/homelogo'))

WebUI.click(findTestObject('Home_Page/platform_Option1'))

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

WebUI.click(findTestObject('Home_Page/groups_Option'))

CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB')

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

WebUI.click(findTestObject('Groups_Page/userSave_Button'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 0)

WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

String users = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewNoUsers_Text'))

WebUI.verifyMatch(users, '(1)', false)

WebUI.verifyElementPresent(findTestObject('Groups_Page/refresh_Icon'), 5)

WebUI.click(findTestObject('Groups_Page/refresh_Icon'))

String firstRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_FirstRow'))

String secondRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_SecondRow'))

ArrayList<String> obtainedList = new ArrayList<String>()

obtainedList.add(firstRowGroupName)

obtainedList.add(secondRowGroupName)

ArrayList<String> sortedList = new ArrayList<String>()

for (String s : obtainedList) {
    sortedList.add(s)
}

Collections.sort(sortedList)

Assert.assertTrue(sortedList.equals(obtainedList))

obtainedList.clear()

sortedList.clear()

WebUI.click(findTestObject('Groups_Page/groupName_Header'))

WebUI.delay(3)

firstRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_FirstRow'))

secondRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_SecondRow'))

obtainedList.add(firstRowGroupName)

obtainedList.add(secondRowGroupName)

for (String s : obtainedList) {
    sortedList.add(s)
}

Collections.reverse(sortedList)

Assert.assertTrue(sortedList.equals(obtainedList))

CustomKeywords.'docPro.GroupPage.deleteTheGroup'('')

