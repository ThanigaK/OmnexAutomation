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

'Login to the application as module admin user'
CustomKeywords.'docPro.KeyWord.LoginwithCredential'(GlobalVariable.url, GlobalVariable.auditprouser, GlobalVariable.auditpropassword)

WebUI.click(findTestObject('Home_Page/homelogo'))

WebUI.click(findTestObject('Home_Page/platform_Option1'))

WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.click(findTestObject('Home_Page/groups_Option'))

CustomKeywords.'docPro.GroupPage.createNewGroup'('random', 'Audits')

CustomKeywords.'docPro.GroupPage.searchGroup'('Test Automation')

WebUI.click(findTestObject('Groups_Page/userSelection_Icon'))

WebUI.waitForElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/selectUsers_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/entityBasedSearch_Option'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userBasedSearch_Option'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userRefresh_Icon'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userSearch_Filter'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userName_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userCode_header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userEmail_Header'), 10)

WebUI.verifyElementPresent(findTestObject('Groups_Page/userPosition_Header'), 10)

CustomKeywords.'docPro.GroupPage.verifyPagination'()

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-AutoUser')

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

WebUI.click(findTestObject('Groups_Page/entityBasedSearch_RadioButton'))

WebUI.waitForElementPresent(findTestObject('Groups_Page/entitySearch_Tree'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/entitySearch_Tree'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/entitySearch_TreeList'), 5)

WebUI.click(findTestObject('Groups_Page/entitySearchCorporate_Option'))

CustomKeywords.'docPro.GroupPage.verifyPagination'()

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-AutoUser')

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

WebUI.click(findTestObject('Groups_Page/userSave_Button'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 0)

WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/viewUsers_Icon'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Code'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Entity'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_Name'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_SearchIcon'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_RefreshIcon'), 5)

WebUI.verifyElementPresent(findTestObject('Groups_Page/viewUser_RemoveButton'), 5)

String Name = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUserFirstRow_Name'))

WebUI.verifyMatch(Name, 'SCB-AutoUser', false)

WebUI.click(findTestObject('Groups_Page/viewUsers_CloseButton'))

CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

