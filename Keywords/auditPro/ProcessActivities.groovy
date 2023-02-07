package auditPro

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.apache.xerces.dom3.as.ASDataType
import org.junit.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

public class ProcessActivities extends common{

	public static String Rand;
	public static String processName;
	public static String processScopeName;
	public static String processNameVerify

	@Keyword
	public void createNewProcessScopeandVerify(String ProcessName) {
		if (ProcessName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			processName = "QAAutoScope_" + Rand;
		} else {
			processName = ProcessName
		}
		processScopeName = "Scope_"+RandomNumber()		

		WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/processName_InputBox'), 20)
		WebUI.sendKeys(findTestObject('Object Repository/ProcessActivities_Page/processName_InputBox'), processName)
		WebUI.sendKeys(findTestObject('Object Repository/ProcessActivities_Page/processScope_InputBox'), processScopeName)
		WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/processScope_Header'))

		WebUI.waitForPageLoad(5)
		WebUI.back();
		WebUI.waitForPageLoad(5)
		
		WebUI.waitForElementPresent(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 10)
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 10)
		List<WebElement> processScopeList = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='Grid_EditProcessScopeGrid']//tbody/tr/td/a"))
		int count = processScopeList.size()		
		for(int i=0;i<count;i++)
		{			
			if(processScopeList[i].getText().equals(processScopeName))
			{				
				Assert.assertTrue(true)
				KeywordUtil.logInfo("ProcessScope Name Verified !!!")
				break;
			}
			if(i==count)
				Assert.fail()
		}
		WebUI.switchToDefaultContent()
		KeywordUtil.logInfo("Switched out of Iframe")
	}
	
	@Keyword
	public void deleteProcessScope(String process = processScopeName)
	{
		KeywordUtil.logInfo("Starting Deletion of Process Scope !!!")
		WebUI.waitForElementPresent(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 10)
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 10)
		List<WebElement> processScopeList = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='Grid_EditProcessScopeGrid']//tbody/tr/td/a"))
		List<WebElement> inputProcessCheckBox = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='Grid_EditProcessScopeGrid']//tbody/tr/td/input"))
		
		int count = processScopeList.size()
		for(int i=0;i<count;i++)
		{			
			if(processScopeList[i].getText().equals(processScopeName))
			{
				inputProcessCheckBox[i].click()
				processScopeList[i].click()				
				WebUI.waitForElementClickable(findTestObject('Object Repository/ProcessActivities_Page/processInput_Checkbox'), 10)
				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/processInput_Checkbox'))
				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/deleteProcessScope_Button'))
				WebUI.waitForElementClickable(findTestObject('Object Repository/ProcessActivities_Page/deleteConfirmation_Button'),10)
				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/deleteConfirmation_Button'))
				
				WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/scopeDeletedMessage'), 10)
				Assert.assertTrue(WebUI.getText(findTestObject('Object Repository/ProcessActivities_Page/scopeDeletedMessage')).contains('Deleted Successfully'))
				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/scopeDeletedMessage_CloseButton'))
				
				WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/noRecords_Table'),10)
				Assert.assertTrue(WebUI.getText(findTestObject('Object Repository/ProcessActivities_Page/noRecords_Table')).contains('No Record Found'))
				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/proccessScope_Link'))
				
				WebUI.delay(5)
				
//				WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 10)
//				WebUI.mouseOver(DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='Grid_EditProcessScopeGrid']//tbody/tr/td/a[contains(text(),'Scope_07022')]//preceding::td[2]")))
//				
//				WebUI.click(DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='Grid_EditProcessScopeGrid']//tbody/tr/td/a[contains(text(),'Scope_07022')]//preceding::td[2]")))
//			
//				WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/deleteProcess_Button'),10)
//				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/deleteProcess_Button'))
//				WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/deleteConfirmation_Button'),10)
//				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/deleteConfirmation_Button'))
//
//				WebUI.waitForElementPresent(findTestObject('Object Repository/ProcessActivities_Page/processDeletedMessage'), 10)
//				Assert.assertTrue(WebUI.getText(findTestObject('Object Repository/ProcessActivities_Page/processDeletedMessage')).contains('Deleted successfully'))
//				WebUI.click(findTestObject('Object Repository/ProcessActivities_Page/processDeletedMessage_CloseButton'))
//				KeywordUtil.logInfo("Process Deleted Successfully !!!")
//				break;
//				
			}
//			if(i==count)
//				Assert.fail() 
			} 
//				
//		
//		//Verify Deleted Process is not listed in the Table
//		for(int i=0;i<count;i++)
//		{
//			if(processScopeList[i].getText().equals(processScopeName))
//				Assert.fail()
//			else
//				Assert.assertTrue(true)				
//		}
		WebUI.switchToDefaultContent()
	}
}
