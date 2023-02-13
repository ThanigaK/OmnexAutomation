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
import org.openqa.selenium.By

CustomKeywords.'docProModule.Base.Login'()

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'suiteModule.LevelsPage.createLevel'(LevelName)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Making the Level In Use'
CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

WebUI.delay(3)

'Right click on the Level\r\n'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

WebUI.click(findTestObject('DocPro_Module/Folder Management/Rights for Sites'))

WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Object Repository/IFrames/iframeTree'), 5)

List Column = WebUI.findWebElements(findTestObject('Object Repository/DocPro_Module/Folder Management/Level Rights/Rights for sites Row'),10)

for(int i=1; i<=Column.size(); i++)
{

	String AttachRight = DriverFactory.getWebDriver().findElement(By.xpath("(//table[@id='DPLevelgrid']//thead//tr[2]//th)["+i+"]")).getText()
	if(AttachRight.contains("Inherit"))
	{
		String CheckBox = DriverFactory.getWebDriver().findElement(By.xpath("(//table[@id='DPLevelgrid']//tbody//tr//td)["+i+"]//input")).getAttribute("value")
		WebUI.verifyMatch(CheckBox, "false", false)
		KeywordUtil.logInfo("Attach to Right inherit Checkbox was not Checked by default")
		break
	}
}

WebUI.switchToDefaultContent()

WebUI.delay(5)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

