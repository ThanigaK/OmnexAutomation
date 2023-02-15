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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By

'Login to the application'
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

WebUI.delay(3)

WebUI.scrollToElement(findTestObject('DocPro_Module/DocProSetup_Page/route_Link'), 0)

RouteOption = WebUI.getText(findTestObject('DocPro_Module/DocProSetup_Page/route_Link'))

WebUI.verifyMatch(RouteOption, 'Inherit from parent', false)

'Validate Route Link is available after make that level In Use'
WebUI.click(findTestObject('DocPro_Module/DocProSetup_Page/route_Link'))

WebUI.switchToFrame(findTestObject('IFrames/iFrameRoute'), 0)

'Find the total number of columns present in the table'
List Column = WebUI.findWebElements(findTestObject('Object Repository/DocPro_Module/Folder Management/Route Option/Route Heading'), 
    10)

'Iterating to validate the checkbox'
for (int i = 1; i <= Column.size(); i++) {
    String AttachRight = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@class=\'table table-bordered\']//th)[' + 
            i) + ']')).getText()

    if (AttachRight.contains('Levels')) {
        String ValueLevel = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@class=\'table table-bordered\']//tbody//tr[1]//td)[' + 
                i) + ']')).getText()

        WebUI.verifyMatch(ValueLevel, 'Default Routes', false)

        KeywordUtil.logInfo('Default Routes Text')
    }
    
    if (AttachRight.contains('New')) {
        String ValueLevel = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@class=\'table table-bordered\']//tbody//tr[1]//td)[' + 
                i) + ']')).getText()

        WebUI.verifyMatch(ValueLevel, 'Module Auto approval', false)

        KeywordUtil.logInfo('Module Auto Approal for New')
    }
    
    if (AttachRight.contains('Existing')) {
        String ValueLevel = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@class=\'table table-bordered\']//tbody//tr[1]//td)[' + 
                i) + ']')).getText()

        WebUI.verifyMatch(ValueLevel, 'Module Auto approval', false)

        KeywordUtil.logInfo('Module Auto Approal for Existing')
    }
}

WebUI.switchToDefaultContent()

DefaultNewRoute = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Route Option/Route New Drop Down'))

WebUI.verifyMatch(DefaultNewRoute, 'Inherit from parent', false)

WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/popupClose_Icon'))

'Assigning Assign By Admin Route option\r\n'
CustomKeywords.'suiteModule.DocPro.AssignAutoApprovalRoute'('Yes', 'No', 'Assign by admin')

RouteOption = WebUI.getText(findTestObject('DocPro_Module/DocProSetup_Page/route_Link'))

WebUI.verifyMatch(RouteOption, 'Assign by admin', false)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

