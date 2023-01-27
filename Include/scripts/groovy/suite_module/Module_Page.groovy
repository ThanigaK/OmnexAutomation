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
class Module_Page extends CommonClass {

	public void selectModule(String moduleName) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSelection_DropDown'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSearch_TextBox'), moduleName+Keys.ENTER);
	}

	public void addUser() {
		Boolean flag = false;
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),10)

		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/Module_Page/searchUserOption_DropDown'), "Empcode", false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Module_Page/searchUser_TextBox'), code)
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Meetings_Page/userTable_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(code)) {
				if(!DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).isSelected()) {
					DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).click()
				}
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()

		if(flag) {
			KeywordUtil.logInfo("User data found and selected.. !")
		} else {
			throw new Error("User data not found.. Please check the data !");
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/Done_Button'))
	}

	public int searchUser() {
		int position = 0;

		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()

		if(rowSize==1) {
			KeywordUtil.logInfo("One data only there..!")
		} else if(rowSize>=1){
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUserFilter_Dropdown'),
					"Empcode", true)
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), code)
		} else {
			KeywordUtil.logInfo("There is no data found.. !")
			throw new Error("There is no data found in the search.. ! Please check the data..!")
		}
		pageRecordSizeChange("100");
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row']/td[2])["+i+"]")).getText();
			if(val.equals(code)) {
				position=i;
				KeywordUtil.logInfo("User data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		return position;
	}

	public void removeUser() {
		try {
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while removing user.. !");
		}
	}


	@And('Add user for (.*) Module')
	public void addUserForModule(String moduleName) {
		Thread.sleep(3000)
		new City_Page().selectCountry();
		selectModule(moduleName);
		addUser();
	}

	@And('Remove user from (.*) Module')
	public void removeUserFromModule(String moduleName) {
		Thread.sleep(3000)
		new City_Page().selectCountry();
		selectModule(moduleName);
		int position = searchUser();
		new UserDetailsHome_Page().selectUser(position, "modules_page")
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		new UserDetailsHome_Page().validateUserDeletionSuccessMessage()
	}
}