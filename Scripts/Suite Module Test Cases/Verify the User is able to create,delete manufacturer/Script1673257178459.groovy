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

'Navigating to the manufacturer page'
CustomKeywords.'docPro.HomePage.goToManufacturerPage'()

'Create new manufacturer'
CustomKeywords.'docPro.ManufacturerPage.CreateManufacturer'(Name, Code)

'Add address to the manufacturer'
CustomKeywords.'docPro.KeyWord.AddAddress'('', '', '')

'Add contact to the same'
CustomKeywords.'docPro.VendorPage.addContact'()

'Navigating back to the Manufacturer page'
CustomKeywords.'docPro.HomePage.goToManufacturerPage'()

'Change the record count to 100'
CustomKeywords.'docPro.KeyWord.pageRecordSizeChange'()

'Delete the manufacturer'
CustomKeywords.'docPro.ManufacturerPage.DeleteManufacturer'()

'Close the browser'
WebUI.closeBrowser()

