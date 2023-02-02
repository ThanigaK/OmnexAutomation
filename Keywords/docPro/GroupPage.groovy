package docPro

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit
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

public class GroupPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String VendorName;
	static String groupPosition;
	static String Position;
	static String groupName;
	static String modifiedGroupName;


	@Keyword
	public void createNewGroup(String GroupName, String option) {
		if (GroupName.equalsIgnoreCase("random")) {
			Rand = RandomNumber()
			groupName = "Test Automation - " + Rand;
		} else {
			groupName = GroupName
		}

		modifiedGroupName = groupName// + "_Modified"

		if(option.equalsIgnoreCase("Documents")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/documents_Option'))
		}
		if(option.equalsIgnoreCase("Audits")) {
			WebUI.click(findTestObject('Object Repository/Groups_Page/audits_text'))
		}

		WebUI.waitForElementPresent(findTestObject('Object Repository/Suite_Module/Groups_Page/add_Button'), 20)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/add_Button'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Groups_Page/groupName_TextBox'), groupName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Groups_Page/successMessageGroupCreation'))
		KeywordUtil.logInfo("Group creation is successful.. ! Success message verified..!")
		WebUI.waitForPageLoad(10)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/refresh_Button'))
	}

	public void searchAndSelectUser(String search,String data) {
		Boolean flag = false;
		Thread.sleep(3000)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/Groups_Page/searchUserOption_DropDown'), search, false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Groups_Page/searchUser_TextBox'), data)
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Groups_Page/userTableList_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equalsIgnoreCase(data)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[1]/input")).click()
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
	}

	@Keyword
	public void addUserInTheGroup(String option) {
		if(option.equalsIgnoreCase("Documents")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/documents_Option'))
		}

		groupPosition = searchGroup(groupName);
		if(groupPosition!=0) {
			KeywordUtil.logInfo("Group found in the search option.. !")
		} else {
			throw new Error("Group not found in the search option.. !")
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/addUsers_Icon'))
		searchAndSelectUser("Empcode","doNotDelete_01")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/saveInAddUsers_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Groups_Page/successMessageInAddUser_Message'))
		KeywordUtil.logInfo("User Added Succesfully.. ! Success message verified..!")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/close_Icon'))
	}

	@Keyword
	public int searchGroup(String groupName) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/refresh_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Groups_Page/searchGroup_TextBox'), groupName)
		try {

			int flag = 0;
			Thread.sleep(2000)

			WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Groups_Page/groupPage_Frame'), 15)

			int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
			for(int i =1; i<=rowSize1;i++) {
				String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row']/td[3])["+i+"]")).getText();
				if(val.equals(groupName)) {
					flag=i;
					KeywordUtil.logInfo("Group data found.. !")
					break;
				}
			}
			WebUI.switchToDefaultContent()
			return flag;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while validating search group option.. !");
		}
	}

	@Keyword
	public void removeUserInTheGroup(String option) {
		if(option.equalsIgnoreCase("Documents")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/documents_Option'))
		}
		searchGroup(groupName);
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/viewUser_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/firstUserInViewUser_CheckBox'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/removeUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Groups_Page/removedSuccess_Message'))
		KeywordUtil.logInfo("User Removed is successfully.. ! Success message verified..!")
	}

	@Keyword
	public void modifyTheGroupName(String option) {
		if(option.equalsIgnoreCase("Documents")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/documents_Option'))
		}

		searchGroup(groupName);
		WebUI.mouseOver(findTestObject('Object Repository/Suite_Module/Groups_Page/addUsers_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/editGroups_Icon'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Groups_Page/groupName_TextBox'), modifiedGroupName)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Groups_Page/updatedSuccess_Message'))
		KeywordUtil.logInfo("Group name modified successfully.. ! Success message verified..!")
	}

	@Keyword
	public void deleteTheGroup(String option) {
		if(option.equalsIgnoreCase("Documents")) {
			WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/documents_Option'))
		}
		if(option.equalsIgnoreCase("Audits")) {
			WebUI.click(findTestObject('Object Repository/Groups_Page/audits_text'))
		}
		searchGroup(modifiedGroupName);
		WebUI.mouseOver(findTestObject('Object Repository/Suite_Module/Groups_Page/addUsers_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/deleteGroups_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Groups_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/Groups_Page/deletedSuccess_Message'))
		KeywordUtil.logInfo("Groupd Deleted successfully.. ! Success message verified..!")
	}
}
