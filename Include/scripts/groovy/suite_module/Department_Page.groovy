package suite_module
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.CommonClass

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

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

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
import org.testng.Assert


/**
 * @author Thaniga
 *
 */
class Department_Page extends CommonClass {
	static String department = getData("Code");


	/**
	 * @Ation: Navigating to the Create Department page from the Home Page Setup
	 */
	public void openCreateDepartment() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		WebUI.click(findTestObject('Dashboard_Page/createDepartment_Button'))
	}


	/**
	 * @Action: Create Department in the Department Module
	 * @param departmentName 
	 * @param departmentCode
	 */
	public void createNewDepartment(String departmentName, String departmentCode) {
		WebUI.waitForElementPresent(findTestObject('Object Repository/Department_Module/add_Button'), 10)
		WebUI.click(findTestObject('Object Repository/Department_Module/add_Button'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Department_Module/department_Name_input'), departmentName)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Department_Module/department_code_input'), departmentCode)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/save_button'))
		KeywordUtil.logInfo(departmentName+" was sucessfully added")
	}


	/**
	 * @Action: Validating whether the department was created sucessfully or not
	 * @param DeptName
	 */
	public void validateDepartment(String DeptName) {
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Department_Module/iframe'), 10)
		List<WebElement> TotalDepartment = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='Grid_Dept']//tbody/tr/td[contains(@class,'sorting')]"));
		for(int i=0; i<TotalDepartment.size(); i++) {
			String Value = TotalDepartment[i].getText();
			if(Value.equals(DeptName)) {
				KeywordUtil.logInfo(DeptName+" was sucessfully Validated")
				break;
			}
			else if(i==TotalDepartment.size()-1) {
				KeywordUtil.logInfo(DeptName+"Not Found")
				Assert.fail();
			}
		}
		WebUI.switchToDefaultContent()
	}


	/**
	 * @Action: Editing the existing departments by changing the department name
	 * @param Dept
	 */
	public void editDepartment(String Dept) {
		WebUI.mouseOver(findTestObject('Object Repository/Suite_Module/Department_Module/department_text'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/edit_button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Department_Module/input_Department'), Dept)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/save_button'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/Suite_Module/Department_Module/department_text'), 10)
		KeywordUtil.logInfo(Dept+" was sucessfully Edited")
	}



	/**
	 * @Action: Search the existing department with the help of department search drop down
	 * @param name
	 * @param department
	 */
	public void searchDepartment(String name, String department) {
		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/Department_Module/search_Dropdown'), name, false)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/Department_Module/lblSearch'), 10)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Department_Module/search_input'), department)
	}


	/**
	 * Delete the existing Department
	 */
	public void deleteDepartment() {
		WebUI.mouseOver(findTestObject('Object Repository/Suite_Module/Department_Module/department_text'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/delete_button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/yes_button'))

		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Department_Module/Iframe'), 10)
		String Message = WebUI.getText(findTestObject('Department_Module/Delete_Message'))
		if(Message.equalsIgnoreCase(" : Deleted successfully")) {
			KeywordUtil.logInfo(" Department was sucessfully Deleted")
		}
		else {
			KeywordUtil.markErrorAndStop("Department Deletion failed")
		}
		WebUI.switchToDefaultContent()
	}

	/**
	 * Refresh the Department grid
	 */
	public void refreshDepartment() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Department_Module/refresh_button'))
	}

	@And("Go to Create Department page")
	public void goToDepartmentPage() {
		openCreateDepartment();
	}

	@When("Add Department and Validate")
	public void addNewDepartment() {
		createNewDepartment(getData("departmentName"),getData("departmentCode"));
		validateDepartment(getData("departmentName"))
		createNewDepartment(getData("DepartmentName2"),getData("DepartmentCode2"));
		validateDepartment(getData("DepartmentName2"))
	}

	@Then("Edit Department and Validate")
	public void editDepartment() {
		searchDepartment("Department",getData("DepartmentName2"))
		validateDepartment(getData("DepartmentName2"))
		if (getData("Code").equalsIgnoreCase("random")) {
			department = "Test Automation - " + dateTimeStr;
		}
		editDepartment(department)
		refreshDepartment();
		searchDepartment("Department",department)
		validateDepartment(department)
	}

	@And("Delete Department")
	public void deletetheDepartment() {
		searchDepartment("Department",department)
		deleteDepartment();
		refreshDepartment();
		deleteDepartment();
	}
}