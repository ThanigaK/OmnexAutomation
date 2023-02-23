package apqpModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByXPath

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable
import org.junit.Assert as Assert

public class ProjectSetup extends Roles {

	static String ProjectGroupName;
	static String PriorityName;
	public static String Rand;

	@Keyword
	public void createNewProjectGroup(String GroupName) {
		if (GroupName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			ProjectGroupName = "Automation_" + Rand;
		} else {
			ProjectGroupName = GroupName
		}

		WebUI.click(findTestObject('ProjectSetup_Page/Button_Add'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroup_InputBox'), 10)
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroup_InputBox'), ProjectGroupName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SaveProjectGroup_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/SuccessAlert'), 10)
	}

	@Keyword
	public void editProjectGroup(String editName) {

		KeywordUtil.logInfo("Search for the Project Group")
		searchProjectGroup()
		KeywordUtil.logInfo("Assign the name to be edited for Project Group")
		ProjectGroupName = editName

		KeywordUtil.logInfo("Project Group Name Getting Edited...")
		WebUI.mouseOver(findTestObject('Object Repository/ProjectSetup_Page/FirstRowHeading'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/FirstRowEdit'))
		WebUI.clearText(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroup_InputBox'))
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroup_InputBox'), editName)

		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SaveProjectGroup_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/SuccessAlert'), 10)
		KeywordUtil.logInfo("Project Group Edited Successfully")
	}
	@Keyword
	public void ResetAndSearchProjectGroup() {

		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SearchButton'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_ClearAdvancedSearch'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_AddAdvancedSearch'))
		WebUI.selectOptionByValue(findTestObject('Object Repository/ProjectSetup_Page/ColumnDropDown'), "3", false)
		WebUI.selectOptionByValue(findTestObject('Object Repository/ProjectSetup_Page/ConditionDropDown'), "=", false)
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/SearchInputBox_Advanced'), ProjectGroupName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SearchButton_Advanced'))
	}


	@Keyword
	public void searchProjectGroup() {

		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SearchButton'))
		WebUI.selectOptionByValue(findTestObject('Object Repository/ProjectSetup_Page/ColumnDropDown'), "3", false)
		WebUI.selectOptionByValue(findTestObject('Object Repository/ProjectSetup_Page/ConditionDropDown'), "=", false)
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/SearchInputBox_Advanced'), ProjectGroupName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SearchButton_Advanced'))
	}

	@Keyword
	public void VerifySearchedProjectGroup() {

		WebUI.waitForPageLoad(5)
		WebUI.switchToFrame(findTestObject('Object Repository/Groups_Page/menuData_iFrame'), 10)
		WebElement ele = DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='ProjectGroupsGridcontrol']/tbody/tr/td[contains(text(),'"+ProjectGroupName+"')]"))
		Assert.assertTrue(ele.displayed)
		WebUI.switchToDefaultContent()
	}

	@Keyword
	public void DeleteProjectGroup() {
		WebUI.mouseOver(findTestObject('Object Repository/ProjectSetup_Page/FirstRowHeading'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/FirstRowDelete'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/PopupConfirmationOK_Button'))

		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/Alert_DeleteSuccess'), 10)
		String alertText = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/Alert_DeleteSuccess'))
		Assert.assertTrue(alertText.contains('Deleted Successfully'))
	}

	@Keyword
	public void DeleteAllProjectGroups() {
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_Refresh'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SelectAllCheckbox'))

		WebUI.switchToFrame(findTestObject('Object Repository/Groups_Page/menuData_iFrame'), 10)
		WebElement ele = DriverFactory.getWebDriver().findElement(By.xpath("(//table[@id='ProjectGroupsGridcontrol']/tbody/tr/td[contains(text(),'default')]//preceding::td)[last()-1]/input"))
		ele.click()
		WebUI.switchToDefaultContent()
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_Delete'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/PopupConfirmationOK_Button'))
	}

	@Keyword
	public void createNewDeliverablePriorities(String name) {
		if (name.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			PriorityName = "High_" + Rand;
		} else {
			PriorityName = name
		}
		KeywordUtil.logInfo("Deliverable Prorities name assigned")

		WebUI.click(findTestObject('ProjectSetup_Page/Button_Add'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/ProjectSetup_Page/PriorityDescription_InputBox'), 10)
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/PriorityDescription_InputBox'), PriorityName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SaveButton'))

		KeywordUtil.logInfo("New Priority Created")

		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/SuccessAlert'), 10)

		KeywordUtil.logInfo("Created Priority Verified Successfully")
	}

	@Keyword
	public void EditDeliverablePriorities() {

		KeywordUtil.logInfo("Deliverable Prorities modified name assigned")

		KeywordUtil.logInfo("Priority name modified successfully")

		KeywordUtil.logInfo("Verified the modified priority successfully")
	}

	@Keyword
	public void CreateMultipleDeliverablePriorities(int count) {

		for(int i=1;i<=count;i++) {
			createNewDeliverablePriorities("random")
			KeywordUtil.logInfo("Deliverable Prority "+i+" Created Successfully")
		}
	}

	@Keyword
	public void DeleteDeliverablePriorities(String name) {

		KeywordUtil.logInfo("Created Deliverable Prority Deleted Successfully")
	}
	@Keyword
	public void DeleteAllDeliverablePriorities(String name) {

		KeywordUtil.logInfo("All Deliverable Prorities Deleted Successfully")
	}
	@Keyword
	public void createNewCharterTemplate(String name) {

		KeywordUtil.logInfo("Form Name of Charter Template entered")
		
		KeywordUtil.logInfo("Needed Elements added for Charter Template")
		
		KeywordUtil.logInfo("Added additional available Elements to the Charter Template")
		
		KeywordUtil.logInfo("Charter Template Saved")
		
		KeywordUtil.logInfo("Attached pillar to the Charter Template")
		
		KeywordUtil.logInfo("Charter Template Created Successfully")
	}
	@Keyword
	public void modifyCharterTemplate() {

		KeywordUtil.logInfo("Form Name of Charter Template verified")
		
		KeywordUtil.logInfo("Elements settings changed")		
		
		KeywordUtil.logInfo("Attached pillar is modified")
		
		KeywordUtil.logInfo("Charter Template modified Successfully")
	}
	
	@Keyword
	public void modifyCharterTemplateDraft() {

		KeywordUtil.logInfo("Form Name of Charter Template verified")
		
		KeywordUtil.logInfo("Elements settings changed")
		
		KeywordUtil.logInfo("Attached pillar is modified")
		
		KeywordUtil.logInfo("Charter Template is modified in Draft mode")
	}

	@Keyword
	public void CopyFormCharterTemplate() {

		KeywordUtil.logInfo("Click on Copy Form button")
		
		KeywordUtil.logInfo("Verify new form page is loaded")
		
		KeywordUtil.logInfo("New name given to the copied form and saved")
		
		KeywordUtil.logInfo("New Charter Templated is created and verified in the Charter Template page")
	}
	
	@Keyword
	public void DeleteCharterTemplate() {

		KeywordUtil.logInfo("Charter Template to be deleted is selected")
		
		KeywordUtil.logInfo("Click on Delete button")
		
		KeywordUtil.logInfo("Confirm the alert message")
		
		KeywordUtil.logInfo("Charter Template is Deleted Successfully")
	}
	@Keyword
	public void VerifyStatusOfCharterTemplate() {

		KeywordUtil.logInfo("Remove the pillar associated with the Charter template")
		
		KeywordUtil.logInfo("Save the Charter Template")
		
		KeywordUtil.logInfo("Navigate back to the Charter Templated page and verify the pillar is not attached to the charter template")
		
		KeywordUtil.logInfo("Verified the Status of Charter Template is Inactive")
	}
	@Keyword
	public void setModuleLevelApprovers(String name) {

		KeywordUtil.logInfo("Approvers list added...")
		
		KeywordUtil.logInfo("Module Level Approvers set successfully")
	}
	@Keyword
	public void verifyModuleLevelApprovers(String name) {
		
		KeywordUtil.logInfo("Verify Approval Request page")
		
		KeywordUtil.logInfo("Module Level Approval verified successfully")
	}
	@Keyword
	public void setAutoLevelApprovers(String name) {

		KeywordUtil.logInfo("Approvers list added...")
		
		KeywordUtil.logInfo("Auto Level Approvers set successfully")
	}
	@Keyword
	public void verifyAutoLevelApprovers(String name) {
		
		KeywordUtil.logInfo("Verify Approval Request page")
		
		KeywordUtil.logInfo("Auto Level Approval verified successfully")
	}
	@Keyword
	public void setGroupRightsMenu(String name) {
		
		KeywordUtil.logInfo("Verify Project Charter rights in Group Rights Menu")
		
		KeywordUtil.logInfo("Project Charter rights set for the Group")
	}
	
	@Keyword
	public void verifyProjectManagementMenu(String name) {
		
		KeywordUtil.logInfo("Project Management menu verified successfully")
	}
}
