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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.By as By

'Login to the EWQIMS application\r\n'
CustomKeywords.'docProModule.Base.LoginwithCredential'(GlobalVariable.url, GlobalVariable.username1, GlobalVariable.Password1)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Create New leavel specific to this scenario\r\n'
levName = CustomKeywords.'suiteModule.LevelsPage.createLevelwithSubLevelAccess'(LevelName)

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Select newely created level in Folder Management'
CustomKeywords.'docProModule.DocPro_Setup.goToLevelInDocproSetup'('Folder Management', levName)

'Making the Level In Use'
CustomKeywords.'suiteModule.DocPro.MakeLevelInUse'()

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(3)

'Right Click on the newly created Level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

WebUI.delay(3)

'Validate the New Option is Disabled'
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'), 5)

'Select the new option\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/New_RightClick Enable'))

WebUI.delay(2)

'Validate the user can able to edit the level '
WebUI.verifyElementPresent(findTestObject('DocPro_Module/Folder Management/New_Level_Heading'), 0)

'Enter New Sub level name\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/New Level Name'), NewSub)

'Click save button'
WebUI.click(findTestObject('Object Repository/DocPro_Module/DocProSetup_Page/save_Button'))

'Click on the newly created level folder arrow\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/FolderArrow'))

WebUI.delay(2)

'Right click on the new sub level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'select right for Sites'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Rights for Sites'))

WebUI.delay(2)

'Switching to new iframe\r\n'
WebUI.switchToFrame(findTestObject('Object Repository/IFrames/iframeTree'), 5)

'Find out the total number of columns'
List Column = WebUI.findWebElements(findTestObject('Object Repository/DocPro_Module/Folder Management/Level Rights/Rights for sites Row'), 
    10)

'Iteratin to validate the checkbox\r\n'
for (int i = 1; i <= Column.size(); i++) {
    String AttachRight = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@id=\'DPLevelgrid\']//thead//tr[2]//th)[' + 
            i) + ']')).getText()

    if (AttachRight.contains('Inherit')) {
        String CheckBox = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@id=\'DPLevelgrid\']//tbody//tr//td)[' + 
                i) + ']//input')).getAttribute('value')

        WebUI.verifyMatch(CheckBox, 'true', false)

        KeywordUtil.logInfo('Sub Level Attach to Right inherit Checkbox was Checked by default')

        break
    }
}

'Switching back to default content'
WebUI.switchToDefaultContent()

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

WebUI.delay(2)

'Right click on the new sub level'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/NewSubFolder'))

'Select Right for groups\r\n'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Rights for Groups'))

WebUI.delay(5)

'Switching to I frame'
WebUI.switchToFrame(findTestObject('Object Repository/IFrames/iframeTree'), 5)

'Get the total number of columns '
List Column1 = WebUI.findWebElements(findTestObject('Object Repository/DocPro_Module/Folder Management/Level Rights/Rights for sites Row'), 
    10)

'Iterating to validate the Checkbox'
for (int i = 1; i <= Column1.size(); i++) {
    String AttachRight = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@id=\'DPLevelgrid\']//thead//tr[2]//th)[' + 
            i) + ']')).getText()

    if (AttachRight.contains('Attach Right')) {
        String CheckBox = DriverFactory.getWebDriver().findElement(By.xpath(('(//table[@id=\'DPLevelgrid\']//tbody//tr//td)[' + 
                i) + ']//input')).getAttribute('value')

        WebUI.verifyMatch(CheckBox, 'true', false)

        KeywordUtil.logInfo('Attach to Right Checkbox Checked by default')

        break
    }
}

'Switching back to default frame\r\n'
WebUI.switchToDefaultContent()

'Navigating to the DocPro Setup page'
CustomKeywords.'docProModule.HomePage.NavigateToDocProSetup'()

'Enter Newly created level name in the folder management search box\r\n'
WebUI.setText(findTestObject('DocPro_Module/Folder Management/SearchByFolder Input'), levName + Keys.ENTER)

'Right click on the Level\r\n'
WebUI.rightClick(findTestObject('DocPro_Module/Folder Management/SearchedFolder_1'))

'Select Right for Users'
WebUI.click(findTestObject('DocPro_Module/Folder Management/Level Rights/Rights for Users'))

'Get the Heading'
Heading = WebUI.getText(findTestObject('DocPro_Module/Folder Management/Level Rights/Manage site document access header'))

'Validate the Right for site page opened \r\n'
WebUI.verifyMatch(Heading, 'Manage User Rights - ' + levName, false)

'Navigating to "New Doc Request" Page '
CustomKeywords.'suiteModule.HomePage.NavigateToNewDocRequestPage'()

'Select "Child Level" to validate the We can access child level for new document request'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(NewSub)

'Select "Parent Level" to validate We can access child level for new document request'
CustomKeywords.'suiteModule.NewDocRequest.levelSelection'(levName)

'Navigating to levels page'
CustomKeywords.'docProModule.HomePage.goToLevelPage'()

'Delete the newly created level'
CustomKeywords.'suiteModule.LevelsPage.levelDeletion'(levName)

