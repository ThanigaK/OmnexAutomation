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
CustomKeywords.'docPro.KeyWord.Login'()

'Navigating to the city page\r\n'
CustomKeywords.'docPro.KeyWord.NavigateToCityPage'()

'Create new city'
CustomKeywords.'docPro.KeyWord.CreateCity'(City, Country, State)

'Navigating back to the city page'
CustomKeywords.'docPro.KeyWord.NavigateToCityPage'()

'Select the input city\t\t'
WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), City)

WebUI.delay(5)

'Getting the value for validation\r\n'
CityValue = WebUI.getText(findTestObject('Suite_Module/City_Page/CityText'))

'Validating City text'
WebUI.verifyMatch(City, CityValue, false)

'Select the check box'
WebUI.click(findTestObject('Suite_Module/City_Page/CityCheckBox'))

'Select the delete button'
WebUI.click(findTestObject('Suite_Module/City_Page/delete'))

'Select pop-up Yes\r\n'
WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'))

WebUI.verifyElementVisible(findTestObject('Suite_Module/City_Page/cityDeletionSuccessMessage'))

