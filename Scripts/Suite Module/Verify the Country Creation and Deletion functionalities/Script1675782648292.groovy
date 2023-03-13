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

'Navigating to the country page\r\n'
CustomKeywords.'suiteModule.KeyWord.NavigateToCountryPage'()

'Wait for add button to exist'
WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 10)

'Select add button'
WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))

'Select country'
WebUI.setText(findTestObject('Suite_Module/Country_Page/country_TextBox'), Country)

'Click Save button'
WebUI.click(findTestObject('Suite_Module/Country_Page/save_Button'))

'Validate Success message'
WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))

KeywordUtil.logInfo('Country creation is successful.. ! Success message verified..!')

'Navigating back to the country page'
CustomKeywords.'suiteModule.KeyWord.NavigateToCountryPage'()

'Validate the newely created country'
CustomKeywords.'suiteModule.HomePage.validateCountryCreation'(Country)

'Delete the country'
CustomKeywords.'suiteModule.HomePage.DeleteCountry'()

