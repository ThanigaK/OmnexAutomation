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

'Login to the application'
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the customer page'
CustomKeywords.'suiteModule.KeyWord.NavigateToCustomerPage'()

'Create new customer'
CustomKeywords.'suiteModule.KeyWord.createCustomer'(Name, Code)

'Add address to the customer'
CustomKeywords.'suiteModule.KeyWord.AddAddress'(Country, State, City)

'Navigating back to the customer page'
CustomKeywords.'suiteModule.KeyWord.NavigateToCustomerPage'()

'Change the page record size'
CustomKeywords.'suiteModule.KeyWord.pageRecordSizeChange'()

'Delete the customer'
CustomKeywords.'suiteModule.KeyWord.DeleteCustomer'()

'Close the browser'
WebUI.closeBrowser()

