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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By

'Navigating to the application'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, 'DocProAdmin', 'QJblfja5Cso=')

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', 'LevelWithDocument')

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), 'LevelWithDocument' + Keys.ENTER)

WebUI.delay(3)

'Right click on the Level\r\n'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

'Select Right for Users'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Rights for Users'))

'Get the Heading'
Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Level Rights/Manage site document access header'))

'Validate the Right for site page opened \r\n'
WebUI.verifyMatch(Heading, 'Manage User Rights - LevelWithDocument', false)

WebUI.setText(findTestObject('DocPro_Module/Folder Management/Level Rights/search'), 'Docpro')

WebUI.delay(5)

'Switching to new iframe\r\n'
WebUI.switchToFrame(findTestObject('Object Repository/IFrames/iframeTree'), 5)

'Find out the total number of columns'
List Row = WebUI.findWebElements(findTestObject('Object Repository/DocPro_Module/Folder Management/Level Rights/Sites column'),
	10)

'Iteratin to validate the checkbox\r\n'
for (int i = 1; i <= Row.size(); i++) {
	String AttachRight = DriverFactory.getWebDriver().findElement(By.xpath(("(//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//td[2]"))).getText()

	if (AttachRight.contains('DocPro- -Admin')) 
	{
		String InheritCheckBox = DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Inherit')])[1]")).getAttribute('value')

		WebUI.verifyMatch(InheritCheckBox, 'true', false)
		
		String AttachRight1 = DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Attachright')])[1]")).getAttribute('value')
		
		WebUI.verifyMatch(AttachRight1, 'true', false)
		
		String RequestRight = DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Requestright')])[1]")).getAttribute('value')
		
		WebUI.verifyMatch(RequestRight, 'false', false)

		KeywordUtil.logInfo('Sub Level Attach to Right inherit Checkbox was Checked by default')
		
		DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Inherit')])[1]")).click()

		String RequestRight1 = DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Requestright')])[1]")).getAttribute('value')
		
		WebUI.verifyMatch(RequestRight1, 'false', false)
		
		DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Requestright')])[1]")).click()
		
		WebUI.delay(3)
		
		DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Attachright')])[1]")).click()
		
		DriverFactory.getWebDriver().findElement(By.xpath("((//table[@id='DPLevelgrid']//tbody//tr)["+i+"]//input[contains(@id,'Inherit')])[1]")).click()
		
		break
	}
}
