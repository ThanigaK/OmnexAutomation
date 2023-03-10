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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to the application\r\n'
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the Country Page\r\n'
CustomKeywords.'suiteModule.KeyWord.NavigateToCountryPage'()

WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 10)

'Select Add Button\r\n'
WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))

'Enter Country Name\r\n'
WebUI.setText(findTestObject('Suite_Module/Country_Page/country_TextBox'), NewCountry)

'Click Save Button\r\n'
WebUI.click(findTestObject('Suite_Module/Country_Page/save_Button'))

'Verify Success Message\r\n'
WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))

KeywordUtil.logInfo('Country creation is successful.. ! Success message verified..!')

'Navigating to the State Page\r\n'
CustomKeywords.'suiteModule.HomePage.NavigatetoStatepage'()

'Create New state\r\n'
CustomKeywords.'suiteModule.StatePage.createNewState'(NewCountry, State)

CustomKeywords.'suiteModule.KeyWord.NavigateToCityPage'()

'Create New City\r\n'
CustomKeywords.'suiteModule.KeyWord.CreateCity'(NewCountry, State, City)

'Navigate to city page'
CustomKeywords.'suiteModule.KeyWord.NavigateToCityPage'()

WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))

WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), NewCountry + Keys.ENTER)

WebUI.delay(5)

'Get added city value for validation\r\n'
CityValue = WebUI.getText(findTestObject('Suite_Module/City_Page/CityText'))

'validaing names'
WebUI.verifyMatch(City, CityValue, false)

'Select the newely created City\r\n\r\n'
WebUI.click(findTestObject('Suite_Module/City_Page/CityCheckBox'))

'Delete the selected city\r\n'
WebUI.click(findTestObject('Suite_Module/City_Page/delete'))

'Select Pop-up Yes\r\n'
WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'))

'Validate Deleteion message'
WebUI.verifyElementVisible(findTestObject('Suite_Module/City_Page/cityDeletionSuccessMessage'))

'Navigating to state page\r\n'
CustomKeywords.'suiteModule.HomePage.NavigatetoStatepage'()

'Validate City Create\r\n'
CustomKeywords.'suiteModule.StatePage.validateCityCreation'(NewCountry, State)

'Delete the newely created state\r\n'
CustomKeywords.'suiteModule.StatePage.deleteState'()

'Navigating to the Country page\r\n'
CustomKeywords.'suiteModule.KeyWord.NavigateToCountryPage'()

'Validate County '
CustomKeywords.'suiteModule.KeyWord.validateCountryCreation'(NewCountry)

'Delete the Country'
CustomKeywords.'suiteModule.KeyWord.deleteCountry'()

