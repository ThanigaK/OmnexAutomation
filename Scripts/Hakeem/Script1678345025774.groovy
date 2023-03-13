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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://cloudqa1.ewqims.com/Common/EwIMSNew/HomePage/Index?url=http%3a%2f%2fcloudqa1.ewqims.com%2fcommon%2fEwIMSNew%2fIndex%2fIndex')

WebUI.setText(findTestObject('Object Repository/Hakeem/Page_EwQIMS - Enterprise-wide Quality and I_7e1186/input_Techsupportomnex.com_username'), 
    'sandy')

WebUI.setEncryptedText(findTestObject('Object Repository/Hakeem/Page_EwQIMS - Enterprise-wide Quality and I_7e1186/input_Techsupportomnex.com_password'), 
    'W6ph6IQp1m0=')

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS - Enterprise-wide Quality and I_7e1186/button_Login'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS - Enterprise-wide Quality and I_7e1186/button_Yes'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/div_Products'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/span_AQuAPro'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/span_Main2'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/img_Global (Manufactured Production Item)_imgFormats'))

WebUI.rightClick(findTestObject('Object Repository/Hakeem/Page_EwQIMS/img_Global (Manufactured Production Item)_imgFormats'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/div_New Format'))

WebUI.doubleClick(findTestObject('Object Repository/Hakeem/Page_EwQIMS/td_Name_et1 CLR_CELL_FOCUS'))

WebUI.setText(findTestObject('Object Repository/Hakeem/Page_EwQIMS/textarea_Global_etext'), 'Automation Run')

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/div_yes'))

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/div_yes'))

WebUI.doubleClick(findTestObject('Object Repository/Hakeem/Page_EwQIMS/div_yes'))

WebUI.delay(5)

WebUI.sendKeys(findTestObject('Object Repository/Hakeem/Page_EwQIMS/textarea_Global_etext'), 'no' + Keys.ENTER)

WebUI.click(findTestObject('Object Repository/Hakeem/Page_EwQIMS/button_Ok'))

