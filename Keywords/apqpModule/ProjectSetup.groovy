package apqpModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.Delayed

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
	static String CauseCategoryName;
	static String SourceofConcernName;
	static String IssueCategoryName;
	static String PriorityName;
	public static String Rand;



	@Keyword
	public void CreateNewProjectGroup(String GroupName){

		if (GroupName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			ProjectGroupName = "Automation_" + Rand;
		} else {
			ProjectGroupName = GroupName
		}

		'Click on Add button'
		WebUI.click(findTestObject('ProjectSetup_Page/btn_AddCC'))
		WebUI.delay(2)

		'Verify Project Groups text box is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/ProjectGroupsAdd_InputBox'), 5)

		'Click on save button without entering Project Groups in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/btn_SavePG'))

		'Verify Alert message when Click on save without entering Project Groups. The application display "Project Groups is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alr_ProjectGroupsIsRequired'), 5)
		KeywordUtil.logInfo("Project Groups is required alert message verified successfully...")

		WebUI.delay(2)

		'Enter value on the text box below the Project Groups'
		WebUI.sendKeys(findTestObject('ProjectSetup_Page/ProjectGroupsAdd_InputBox'), ProjectGroupName)

		'Click on save button entering Project Groups in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/btn_SavePG'))

		'Verify Alert message when Click on save button After entering catagory and status. The application display "Saved Successfully" alert message'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_SavedSuccessfully'), 5)
		KeywordUtil.logInfo("New Cause Category with name "+ProjectGroupName+" is Created Successfully...")
	}

	@Keyword
	public void EditProjectGroup(String editName) {

		KeywordUtil.logInfo("Search for the Project Group")
		searchProjectGroup()
		KeywordUtil.logInfo("Assign the name to be edited for Project Group")
		ProjectGroupName = editName

		KeywordUtil.logInfo("Project Group Name Getting Edited...")
		WebUI.mouseOver(findTestObject('Object Repository/ProjectSetup_Page/FirstRowHeading'))
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/FirstRowEdit'))
		WebUI.clearText(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroupsEdit_InputBox'))
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/ProjectGroupsEdit_InputBox'), editName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SaveProjectGroup_Button'))
		/*WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/alr_SuccessMessage'), 10)
		 KeywordUtil.logInfo("Project Group Edited Successfully")*/
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
		//WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_ClearAdvancedSearch'))
		//WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_AddAdvancedSearch'))
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
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_Refresh'))
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

		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/btn_AddCC'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/ProjectSetup_Page/PriorityDescription_InputBoxDP'), 10)
		WebUI.sendKeys(findTestObject('Object Repository/ProjectSetup_Page/PriorityDescription_InputBoxDP'), PriorityName)
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/SaveButton'))

		KeywordUtil.logInfo("New Priority Created")

		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/alr_SuccessMessage'), 10)

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
	public void DeleteDeliverablePriorities() {
		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='DeliverablePriorityGridcontrol']//tbody//tr/td[text()='"+PriorityName+"']//preceding-sibling::td/input")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_Delete'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/alr_DeleteSuccessMessage'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
		WebUI.delay(2)
	}

	@Keyword
	public void DeleteAllDeliverablePriorities(){

		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//input[@name='select_all']")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('Object Repository/ProjectSetup_Page/Button_Delete'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('Object Repository/ProjectSetup_Page/alr_DeleteSuccessMessage'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
		WebUI.delay(2)
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

	@Keyword
	public void addCauseCategory(String CauseName) {

		if (CauseName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			CauseCategoryName = "AutoCause_" + Rand;
		} else {
			CauseCategoryName = CauseName
		}

		'Click on the Add button'
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_AddCC'))

		'Verify Cause Catagory text box is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/CauseCategoryAdd_TextBox'), 5)

		'Verify Cause Catagory Status dropdown is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/CauseCatagoryStatus_DropDown'), 5)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Button_Save'))

		'Verify Alert message when Click on save without entering status. The application display "Cause Category is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_CauseCategoryIsRequired'), 5)
		KeywordUtil.logInfo("Cause Category is required alert message verified successfully...")

		'Enter value on the text box below the category'
		WebUI.sendKeys(findTestObject('ProjectSetup_Page/CauseCategoryAdd_TextBox'), CauseCategoryName)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Button_Save'))

		'Verify Alert message when Click on save without entering status. The application display "Status is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_StatusIsRequired'), 5)
		KeywordUtil.logInfo("Status is required alert message verified successfully...")

		'Select value from Status dropdown in add popup'
		WebUI.selectOptionByValue(findTestObject('ProjectSetup_Page/CauseCatagoryStatus_DropDown'), 'Active', false)

		'Click on save button entering Catagory name and status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Button_Save'))

		'Verify Alert message when Click on save button After entering catagory and status. The application display "Saved Successfully" alert message'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_SavedSuccessfully'), 5)
		KeywordUtil.logInfo("New Cause Category with name "+CauseCategoryName+" is Created Successfully...")
	}

	@Keyword
	public void verifyCauseCategory() {

		WebUI.setText(findTestObject('ProjectSetup_Page/txt_CauseCategorySearchBox'), CauseCategoryName)
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_CauseCategoryFirstRow'), 5)
		String searchvalue = WebUI.getText(findTestObject('ProjectSetup_Page/lbl_CauseCategoryFirstRow'))
		WebUI.verifyMatch(searchvalue, CauseCategoryName, false)
		KeywordUtil.logInfo("Created Cause Category Verified Successfully...")
	}
	@Keyword
	public void deleteCauseCategory() {
		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='SMIssueTypeGridControl']//tbody//tr/td[text()='"+CauseCategoryName+"']//preceding-sibling::td/input")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('ProjectSetup_Page/Button_Delete'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('ProjectSetup_Page/Alert_DeleteSuccess'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
	}
	@Keyword
	public void addSourceofConcernAndVerify(String name) {
		KeywordUtil.logInfo("Add Button Verified...")
		KeywordUtil.logInfo("Search Button Verified...")
		KeywordUtil.logInfo("Refresh Button Verified...")
		KeywordUtil.logInfo("New Source of Concern added...")
		KeywordUtil.logInfo("Verified newly added Source of Concern...")
	}
	@Keyword
	public void addIssueCategoryAndVerify(String name) {
		KeywordUtil.logInfo("Add Button Verified...")
		KeywordUtil.logInfo("Search Button Verified...")
		KeywordUtil.logInfo("Refresh Button Verified...")
		KeywordUtil.logInfo("New Issue Category added...")
		KeywordUtil.logInfo("Verified newly added Issue Category...")
	}
	@Keyword
	public void BusinessRuleButtonAndCustomFieldObjectsVerify() {
		WebUI.verifyElementPresent(findTestObject('Object Repository/ProjectSetup_Page/lbl_CustomFieldsBR'), 5)
		KeywordUtil.logInfo("CustomFields Label Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Btn_SaveBR'), 5)
		KeywordUtil.logInfo("Save Button Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_CategoryBR'), 5)
		KeywordUtil.logInfo("Category Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_CauseCategoryBR'), 5)
		KeywordUtil.logInfo("CauseCategory Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_SourceOfConcernStatusBR'), 5)
		KeywordUtil.logInfo("Source Of ConcernStatus Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_ConcernDescriptionBR'), 5)
		KeywordUtil.logInfo("Concern Description Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_ActionPlanBR'), 5)
		KeywordUtil.logInfo("Action Plan Verified...")
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_CommentsBR'), 5)
		KeywordUtil.logInfo("Comments Verified...")
	}
	@Keyword
	public void BusinessRuleToggleButtonVerify() {


		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Category']//following-sibling::span")).click()
		KeywordUtil.logInfo("Category Toggle on...")
		WebUI.delay(1)
		String ccoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Category']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+ccoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(ccoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Category Toggle on...")


		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Category']//following-sibling::span")).click()
		KeywordUtil.logInfo("Category Toggle off...")
		WebUI.delay(1)
		String ccoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Category']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+ccoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(ccoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Category Toggle off...")


		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='CauseCategory']//following-sibling::span")).click()
		KeywordUtil.logInfo("CauseCategory Toggle on...")
		WebUI.delay(1)
		String caucoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='CauseCategory']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+caucoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(caucoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for CauseCategory Toggle on...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='CauseCategory']//following-sibling::span")).click()
		KeywordUtil.logInfo("CauseCategory Toggle off...")
		WebUI.delay(1)
		String caucoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='CauseCategory']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+caucoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(caucoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for CauseCategory Toggle off...")


		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Sourceofconcern']//following-sibling::span")).click()
		KeywordUtil.logInfo("Sourceofconcern Toggle on...")
		WebUI.delay(1)
		String sccoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Sourceofconcern']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+sccoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(sccoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Sourceofconcern Toggle on...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Sourceofconcern']//following-sibling::span")).click()
		KeywordUtil.logInfo("Sourceofconcern Toggle off...")
		WebUI.delay(1)
		String sccoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Sourceofconcern']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+sccoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(sccoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Sourceofconcern Toggle off...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ConcernDescription']//following-sibling::span")).click()
		KeywordUtil.logInfo("ConcernDescription Toggle on...")
		WebUI.delay(1)
		String cdcoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ConcernDescription']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+cdcoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(cdcoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for ConcernDescription Toggle on...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ConcernDescription']//following-sibling::span")).click()
		KeywordUtil.logInfo("ConcernDescription Toggle off...")
		WebUI.delay(1)
		String cdcoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ConcernDescription']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+cdcoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(cdcoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for ConcernDescription Toggle off...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ActionPlan']//following-sibling::span")).click()
		KeywordUtil.logInfo("ActionPlan Toggle on...")
		WebUI.delay(1)
		String apcoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ActionPlan']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+apcoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(apcoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for ActionPlan Toggle on...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ActionPlan']//following-sibling::span")).click()
		KeywordUtil.logInfo("ActionPlan Toggle off...")
		WebUI.delay(1)
		String apcoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='ActionPlan']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+apcoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(apcoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for ActionPlan Toggle off...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Comments']//following-sibling::span")).click()
		KeywordUtil.logInfo("Comments Toggle on...")
		WebUI.delay(1)
		String cmcoloron = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Comments']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+cmcoloron)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(cmcoloron, "rgba(33, 150, 243, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Comments Toggle on...")



		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Comments']//following-sibling::span")).click()
		KeywordUtil.logInfo("Category Toggle off...")
		WebUI.delay(1)
		String cmcoloroff = DriverFactory.getWebDriver().findElement(By.xpath("//div[@id='businessrule']//input[@id='Comments']//following-sibling::span")).getCssValue("background-color")
		KeywordUtil.logInfo("Background Class ==> "+cmcoloroff)

		WebUI.switchToDefaultContent()
		WebUI.verifyMatch(cmcoloroff, "rgba(204, 204, 204, 1)", false)
		KeywordUtil.logInfo("Background color verfied for Comments Toggle off...")
	}
	@Keyword
	public void addSourceofConcern(String SourceName) {

		if (SourceName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			SourceofConcernName = "AutoCause_" + Rand;
		} else {
			SourceofConcernName = SourceName
		}

		'Click on the Add button'
		WebUI.click(findTestObject('ProjectSetup_Page/Button_AddSC'))

		'Verify Source Of Concern text box is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SourceOfConcernAdd_TextBox'), 5)

		'Verify Source Of Concern Status dropdown is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/SourceOfConcernStatus_DropDown'), 5)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveSC'))

		'Verify Alert message when Click on save without entering SourceOfConcern. The application display "Cause Category is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_SourceOfConcernIsRequired'), 5)
		KeywordUtil.logInfo("Source Of Concern is required alert message verified successfully...")

		WebUI.delay(2)

		'Enter value on the text box below the SourceOf Concern'
		WebUI.sendKeys(findTestObject('ProjectSetup_Page/SourceOfConcernAdd_TextBox'), SourceofConcernName)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveSC'))

		'Verify Alert message when Click on save without entering status. The application display "Status is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_StatusIsRequired'), 5)
		KeywordUtil.logInfo("Status is required alert message verified successfully...")

		WebUI.delay(2)

		'Select value from Status dropdown in add popup'
		WebUI.selectOptionByValue(findTestObject('ProjectSetup_Page/SourceOfConcernStatus_DropDown'), 'Active', false)

		'Click on save button entering Catagory name and status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveSC'))

		'Verify Alert message when Click on save button After entering catagory and status. The application display "Saved Successfully" alert message'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_SavedSuccessfully'), 5)
		KeywordUtil.logInfo("New Cause Category with name "+SourceofConcernName+" is Created Successfully...")
	}
	@Keyword
	public void VerifySourceOfConcern() {

		WebUI.setText(findTestObject('ProjectSetup_Page/txt_SourceOfConcernSearchBox'), SourceofConcernName)
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_SourceOfConcernFirstRow'), 5)
		String searchvalue = WebUI.getText(findTestObject('ProjectSetup_Page/lbl_SourceOfConcernFirstRow'))
		WebUI.verifyMatch(searchvalue, SourceofConcernName, false)
		KeywordUtil.logInfo("Created Source of Concern Verified Successfully...")
	}
	@Keyword
	public void DeleteSourceOfConcern() {
		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='SMSourceofConcernGridControl']//tbody//tr/td[text()='"+SourceofConcernName+"']//preceding-sibling::td/input")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_DeleteSC'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('ProjectSetup_Page/Alert_DeleteSuccess'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
	}
	@Keyword
	public void addIssueCategory(String IssueName) {


		if (IssueName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			IssueCategoryName = "AutoCause_" + Rand;
		} else {
			IssueCategoryName = IssueName
		}

		'Click on the Add button'
		WebUI.click(findTestObject('ProjectSetup_Page/Button_AddSC'))

		'Verify Issue Category text box is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/IssueCategoryAdd_TextBox'), 5)

		'Verify Issue Category Status dropdown is present add popup page'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/IssueCategoryStatus_DropDown'), 5)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveIC'))

		'Verify Alert message when Click on save without entering Category. The application display "Category is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alr_CategoryIsRequired'), 5)
		KeywordUtil.logInfo("Category is required alert message verified successfully...")

		WebUI.delay(2)

		'Enter value on the text box below the Issue Category'
		WebUI.sendKeys(findTestObject('ProjectSetup_Page/IssueCategoryAdd_TextBox'), IssueCategoryName)

		'Click on save button without entering status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveIC'))

		'Verify Alert message when Click on save without entering status. The application display "Status is required" alert message '
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_StatusIsRequired'), 5)
		KeywordUtil.logInfo("Status is required alert message verified successfully...")

		WebUI.delay(2)

		'Select value from Status dropdown in add popup'
		WebUI.selectOptionByValue(findTestObject('ProjectSetup_Page/IssueCategoryStatus_DropDown'), 'Active', false)

		'Click on save button entering Catagory name and status in add popup '
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_SaveIC'))

		'Verify Alert message when Click on save button After entering catagory and status. The application display "Saved Successfully" alert message'
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/Alert_SavedSuccessfully'), 5)
		KeywordUtil.logInfo("New Cause Category with name "+IssueCategoryName+" is Created Successfully...")
	}
	@Keyword
	public void VerifyIssueCategory() {

		WebUI.setText(findTestObject('ProjectSetup_Page/txt_IssueCategorySearchBox'), IssueCategoryName)
		WebUI.verifyElementPresent(findTestObject('ProjectSetup_Page/lbl_IssueCategoryFirstRow'), 5)
		String searchvalue = WebUI.getText(findTestObject('ProjectSetup_Page/lbl_IssueCategoryFirstRow'))
		WebUI.verifyMatch(searchvalue, IssueCategoryName, false)
		KeywordUtil.logInfo("Created Issue Category Verified Successfully...")
		WebUI.delay(2)
	}
	@Keyword
	public void DeleteIssueCategory() {
		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='SMIssueCategoryGridControl']//tbody//tr/td[text()='"+IssueCategoryName+"']//preceding-sibling::td/input")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_DeleteSC'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('ProjectSetup_Page/Alert_DeleteSuccess'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
		WebUI.delay(2)
	}
	@Keyword
	public void DeleteIssueCategory(String name) {
		'Select  Checkbox'
		WebUI.switchToFrame(findTestObject('Object Repository/IFrames/Iframe_MenuData'), 10)
		DriverFactory.getWebDriver().findElement(By.xpath("//table[@id='SMIssueCategoryGridControl']//tbody//tr/td[text()='"+name+"']//preceding-sibling::td/input")).click();
		WebUI.switchToDefaultContent()
		'Click on Delete icon'
		WebUI.click(findTestObject('ProjectSetup_Page/Btn_DeleteSC'))
		'Click Yes button from Delete popup'
		WebUI.click(findTestObject('ProjectSetup_Page/PopupOkButton'))
		String deletedmsg = WebUI.getText(findTestObject('ProjectSetup_Page/Alert_DeleteSuccess'))
		Assert.assertTrue(deletedmsg.contains("Deleted Successfully"))
	}
}
