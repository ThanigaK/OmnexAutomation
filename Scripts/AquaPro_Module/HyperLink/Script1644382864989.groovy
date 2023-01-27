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

WebUI.navigateToUrl('https://qa46a.ewqims.com/Common/EwIMSNew/homepage/Index')

WebUI.setText(findTestObject('null'), 'test-19')

WebUI.setEncryptedText(findTestObject('null'), 'EtKeOlN9fg4=')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('Home_Page/products_Icon'))

WebUI.click(findTestObject('Home_Page/aquaPro_Icon'))

WebUI.click(findTestObject('Home_Page/main2_Icon'))

WebUI.mouseOver(findTestObject('Home_Page/menu_Icon'))

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/globalExpand_Icon'))

WebUI.selectOptionByValue(findTestObject('null'), 'Test', false)

WebUI.clickImage(findTestObject('null'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('Page_EwQIMS/openExportCount_option'))

WebUI.click(findTestObject('null'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/createDocProHyperLink_Option'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/deleteHyperLink_Option'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/deleteHyperLink_Option'))

WebUI.rightClick(findTestObject('null'))

WebUI.verifyElementNotPresent(findTestObject('null'), 0)

WebUI.selectOptionByValue(findTestObject('null'), 'Design FMEA', true)

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/ok_Button'))

WebUI.rightClick(findTestObject('null'))

WebUI.click(findTestObject('AquaPro_Module/Main2_Page/deleteHyperLink_Option'))

