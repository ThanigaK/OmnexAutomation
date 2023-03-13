package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.CommonClass
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



/**
 * @author sibis
 *
 */
class Teams_Page extends CommonClass{
	String teamName = "";


	public void teamCreation() {
		if (getData("Team name").equalsIgnoreCase("random")) {
			teamName = "Test Automation - " + dateTimeStr;
		} else {
			teamName = getData("Team name")
		}

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),30)

		selectLeader(code)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/teams_TextBox'), teamName+Keys.TAB)
		addExternalMember("Test Automation",email)
		addMember("E001")
	}
	public void selectEntity(String entity) {
		Boolean flag;
		WebUI.click(findTestObject('Object Repository/Suite_Module/Teams_Page/entity_Icon'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/entitySearch_TextBox'), entity)
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		TestObject to = new TestObject("entity")
		to.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@title,'"+ entity+"')]/preceding::span[@class='button chk radio_false_full']")
		try {
			flag=WebUI.verifyElementPresent(to, 15)
		}
		catch(Exception e) {
			KeywordUtil.logInfo("Entity already selected.. Actual --> "+entity)
		}
		if(!flag) {
			WebUI.click(to)
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/Done_Button'))
	}

	public void selectLeader(String userCode){
		WebUI.click(findTestObject('Object Repository/Suite_Module/Teams_Page/leaderName_Icon'))
		new EntityAndEntityOwner_Page().selectUser(userCode)
	}

	public void addExternalMember(String name,String email) {
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Teams_Page/addExternalTeamMem_Button'),20)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/externalMemberName_TextBox'), name)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/externalMemberEmail_TextBox'), email)
	}

	public void addMember(String userCode) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Teams_Page/addMember_Button'))
		new EntityAndEntityOwner_Page().selectUser(userCode)
	}

	public void selectTeam(String name) {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_TeamListing']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(name)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_TeamListing']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Team data found and selected.. !")
		} else {
			throw new Error("Team not found.. Please check the data ! Actual data is : " + val);
		}
	}

	public void confirmDeletion(){
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Team deleted successfully.. ! Deleted Success message verified..!")
	}

	@And("Create Team")
	public void createTeam() {
		teamCreation()
	}

	@And("Delete Team")
	public void deleteTeam() {
		selectTeam(teamName)
		confirmDeletion()
	}
}