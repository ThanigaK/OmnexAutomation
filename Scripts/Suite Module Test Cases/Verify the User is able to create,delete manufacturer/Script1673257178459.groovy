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
CustomKeywords.'ewqims.KeyWord.Login'()

'Navigating to the manufacturer page'
CustomKeywords.'ewqims.HomePage.goToManufacturerPage'()

'Create new manufacturer'
CustomKeywords.'ewqims.ManufacturerPage.CreateManufacturer'(Name, Code)

'Add address to the manufacturer'
CustomKeywords.'ewqims.KeyWord.AddAddress'('', '', '')

'Add contact to the same'
CustomKeywords.'ewqims.VendorPage.addContact'()

'Navigating back to the Manufacturer page'
CustomKeywords.'ewqims.HomePage.goToManufacturerPage'()

'Change the record count to 100'
CustomKeywords.'ewqims.KeyWord.pageRecordSizeChange'()

'Delete the manufacturer'
CustomKeywords.'ewqims.ManufacturerPage.DeleteManufacturer'()

'Close the browser'
WebUI.closeBrowser()

