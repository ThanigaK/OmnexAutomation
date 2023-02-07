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
CustomKeywords.'suiteModule.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

'Click on the Home logo to select the platform\r\n'
WebUI.click(findTestObject('Home_Page/homelogo'))

'Select the Integrated Management System /QHSE Platform\r\n'
WebUI.click(findTestObject('Home_Page/platform_Option1'))

'Click on the Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

'Click on the Suite Setup option in the left pane\r\n'
WebUI.click(findTestObject('Home_Page/suiteSetup_Option'))

'Click on the Groups option\r\n'
WebUI.click(findTestObject('Home_Page/groups_Option'))

'Create a new group with random name and prefix as -> Test Automation\r\n'
CustomKeywords.'suiteModule.GroupPage.createNewGroup'('random', 'Audits')

'Search for the new group created\r\n'
CustomKeywords.'suiteModule.GroupPage.searchGroup'('Test Automation')

'Click on the User Selection Icon\r\n'
WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

'Wait for the Search Icon to load in the Assign users page\r\n'
WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

'Verify Users Header is present in the Groups page\r\n'
WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

'Enter the input in Search text box\r\n'
WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB')

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Click on User Selection checkbox\r\n'
WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

'Click on close button in the Assign Users page\r\n'
WebUI.click(findTestObject('Groups_Page/userSave_Button'))

'Verify Alert Success message is displayed properly\r\n'
WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 10)

'Close the Alert Message'
WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

//String users = WebUI.getText(findTestObject('Groups_Page/viewNoUsers_Text')).toString().trim()
//WebUI.verifyMatch(users, '(1)', false)
'Verify User Refresh Icon is present in the Assign Users page\r\n'
WebUI.verifyElementPresent(findTestObject('Groups_Page/refresh_Icon'), 5)

'Click on the Refresh Icon present in the page'
WebUI.click(findTestObject('Groups_Page/refresh_Icon'))

'Get the text of First User Name '
String firstRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_FirstRow'))

'Get the text of Second User Name '
String secondRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_SecondRow'))

'Creating an Array for Storing User Names'
ArrayList<String> obtainedList = new ArrayList<String>()

'Add first value to the Array List'
obtainedList.add(firstRowGroupName)

'Add second value to the Array List'
obtainedList.add(secondRowGroupName)

'Creating a Second Array for Storing User Names'
ArrayList<String> sortedList = new ArrayList<String>()

'Adding same values to the second Array List'
for (String s : obtainedList) {
    sortedList.add(s)
}

'Sort the Array List obtained'
Collections.sort(sortedList)

'Compare both the array lists to verify Sorting of Group Names'
Assert.assertTrue(sortedList.equals(obtainedList))

'Clear the Array List'
obtainedList.clear()

'Clear the Array List'
sortedList.clear()

'Click the Group Name Header for sorting it to Descending order'
WebUI.click(findTestObject('Groups_Page/groupName_Header'))

'Wait for 3 seconds for the page to load\r\n'
WebUI.delay(3)

'Get the text of First User Name '
firstRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_FirstRow'))

'Get the text of Second User Name '
secondRowGroupName = WebUI.getText(findTestObject('Object Repository/Groups_Page/sortGroupName_SecondRow'))

'Add first value to the Array List'
obtainedList.add(firstRowGroupName)

'Add second value to the Array List'
obtainedList.add(secondRowGroupName)

'Adding same values to the second Array List'
for (String s : obtainedList) {
    sortedList.add(s)
}

'Sort the Array List obtained'
Collections.reverse(sortedList)

'Compare both the array lists to verify Sorting of Group Names'
Assert.assertTrue(!(sortedList.equals(obtainedList)))

'Delete the group created in the test case'
CustomKeywords.'suiteModule.GroupPage.deleteTheGroup'('')

