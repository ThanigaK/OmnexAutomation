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
CustomKeywords.'suiteModule.KeyWord.Login'()

'Navigating to the Entity Page'
CustomKeywords.'suiteModule.HomePage.NavigateToEntityPage'()

'Creatin new Entity'
CustomKeywords.'suiteModule.HomePage.createNewEntity'(EntityName, Code)

'Navigating to the Entity Owner Page\r\n'
CustomKeywords.'suiteModule.HomePage.NavigateToEntityOwnerPage'()

'Assign Enitity Owner\r\n'
CustomKeywords.'suiteModule.HomePage.assignEntityOwner'(Code)

if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
	WebUI.click(findTestObject('Home_Page/menu_Icon'))
}

'Navigating back to the Entity Page'
WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/entity_Option'),15)

WebUI.click(findTestObject('Object Repository/Home_Page/entity_Option'))

'Delete the Entity'
CustomKeywords.'suiteModule.HomePage.DeleteEntity'()

