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
import junit.framework.Assert as Assert
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

WebUI.setText(findTestObject('Groups_Page/userSearch_Input'), 'SCB-AutoUser')

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/userSelection_Checkbox'))

WebUI.click(findTestObject('Groups_Page/userSave_Button'))

WebUI.verifyElementPresent(findTestObject('Groups_Page/alertSuccess_Message'), 0)

WebUI.delay(3)

WebUI.click(findTestObject('Groups_Page/alertMessage_Close'))

WebUI.click(findTestObject('Groups_Page/viewUsers_Icon'))

WebUI.click(findTestObject('Groups_Page/viewUser_RemoveButton'))

String alert = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUser_AlertMessage'))

Assert.assertTrue(alert.contains('Please Select the User(s)'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_AlertClose'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserCheckBox'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveButton'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserPopupOk'))

String alertsuccess = WebUI.getText(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserSuccessMessage'))

Assert.assertTrue(alertsuccess.contains('Removed Successfully'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUser_RemoveUserSuccessMessageClose'))

WebUI.click(findTestObject('Object Repository/Groups_Page/viewUsers_CloseButton'))

CustomKeywords.'docPro.GroupPage.modifyTheGroupName'('Audits')

CustomKeywords.'docPro.GroupPage.deleteTheGroup'('Audits')

