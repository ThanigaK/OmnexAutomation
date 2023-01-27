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

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoAlertPresentException

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

import common.CommonClass
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit

/**
 * @author sibis
 *
 */
class UserDetailsHome_Page extends CommonClass{


	int position;

	public void searchUser(String searchOption) {
		WebUI.waitForPageLoad(30);
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		if(rowSize>=1) {
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUserFilter_Dropdown'),
					searchOption, true)
			switch (searchOption) {
				case "empcode":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), code)
					break;

				case "FullName":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), getData("First name") +" " + getData("Last name"))
					break;

				case "UserName":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), userName)
					break;
				case "Email":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), email)
					break;
				case "ItarCompliance":
					WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchITAR_DropDown'),
					'ITAR', true)
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), code)
			}
		}
	}

	public int validateSearchUser(String searchOption,String pageName) {
		DriverFactory.getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {

			int flag = 0;String dataValue=null;
			int data=0;

			WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
			int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
			WebUI.switchToDefaultContent()

			if(rowSize != 0) {
				pageRecordSizeChange("100");
			} else {
				KeywordUtil.logInfo("There is no data found.. !")
				throw new Error("There is no data found in the search.. ! Please check the data..!")
			}
			switch (searchOption) {
				case "empcode":
					if(pageName.contains("users_page")) {
						data = 2;
					} else {
						data=3;
					}
					dataValue=code;
					break;
				case "FullName":
					if(pageName.contains("users_page")) {
						data=3;
					} else {
						data=4;
					}
					dataValue= getData("First name") +" " + getData("Last name");
					break;
				case "UserName":
					if(pageName.contains("users_page")) {
						data=4;
					} else {
						data=2;
					}
					dataValue=userName;
					break;
				case "Email":
					data=6;
					dataValue=email;
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					if(pageName.contains("users_page")) {
						data = 2;
					} else {
						data=3;
					}
					dataValue=code;
			}
			WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
			Thread.sleep(2000)
			int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
			for(int i =1; i<=rowSize1;i++) {
				String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row']/td["+data+"])["+i+"]")).getText();
				if(val.equals(dataValue)) {
					flag=i;
					KeywordUtil.logInfo("User data found.. !")
					break;
				}
			}
			WebUI.switchToDefaultContent()
			return flag;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while validating search user option.. !");
		}
	}

	public void selectUser(int position,String pageName) {
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		TestObject to = new TestObject("user_Checkbox")
		if(pageName.equals("restore_page")) {
			to.addProperty("xpath", ConditionType.EQUALS, "(//*[@name ='Grid_RestoreUsersListing_selectCheck'])["+position+"]")
		} else if (pageName.equals("siteAdmin_page")) {
			to.addProperty("xpath", ConditionType.EQUALS, "(//*[@name ='userListingGridControl_selectCheck'])["+position+"]")
		}else if(pageName.equals("modules_page")) {
			to.addProperty("xpath", ConditionType.EQUALS, "(//*[@name ='SuAdminGridViewControl_selectCheck'])["+position+"]")
		} else {
			to.addProperty("xpath", ConditionType.EQUALS, "(//*[@name ='SuUsers_selectCheck'])["+position+"]")
		}
		WebUI.click(to)
		//WebUI.click(findTestObject('Checkbox'))
		WebUI.switchToDefaultContent()
	}

	public void validateUserDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
			KeywordUtil.logInfo("User deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	public void validateSuccessMessageInRestorePage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/User_RestorePage/restoreSuccessmessage'))
			KeywordUtil.logInfo("User restore is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User restore is failed.. ! Success message is not displayed.. !")
		}
	}

	@And("Search and validate user by (.*) in (.*)")
	public void searchAndValidateUser(String searchOption,String pageName) {
		searchUser(searchOption);
		position = validateSearchUser(searchOption,pageName);
		if(position!=0) {
			KeywordUtil.logInfo("User found in the search option.. !")
		} else {
			throw new Error("User not found in the search option.. !")
		}
	}

	@And("Restore user in (.*)")
	public void restoreUser(String pageName) {
		selectUser(position,pageName);
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		validateSuccessMessageInRestorePage();
	}

	@And("Delete user")
	public void deleteUser() {
		selectUser(position,"");
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)

		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		try {
			WebUI.acceptAlert();
		}
		catch(Exception e) {
			KeywordUtil.logInfo("No Alert Present.. So, trying again to delete user.. !")
			DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
			WebUI.acceptAlert();
		}
		WebUI.switchToDefaultContent();
		validateUserDeletionSuccessMessage();
	}

	@And("Validate Page size")
	public void validatePageSize() {
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//*[@class='dataTables_length']/label/select"));
		Select s= new Select(element);
		WebElement selectedOption=s.getFirstSelectedOption();
		if(selectedOption.getText().equals(getData("Page size"))) {
			KeywordUtil.logInfo("Page size Matched..!")
		} else {
			KeywordUtil.markFailed("Page size is not matched.. Actual --> " +selectedOption.getText())
		}
		WebUI.switchToDefaultContent();
	}
}