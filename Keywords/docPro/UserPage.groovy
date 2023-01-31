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

public class UserPage extends common {
	//	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	//	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	//	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String Pass;
	static String Cod;
	static String Position;
	static String User;
	static String email;

	public String RandomNumber()
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String DateTimeStr = sdf1.format(timestamp);
		return DateTimeStr;
	}


	@Keyword
	public String createUser(String Code, String FirstName, String LastName, String Email, String UserName, String Password, String ITAR, String ChangePassword) {
		User = UserName;
		Pass = Password
		if(Code.equalsIgnoreCase("random")) {
			String Random = RandomNumber();
			Cod = 	Random;
			User = "User"+ Random;
			email = "DarkAuto" + Random + "@Gmail.com";
		}
		WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 30)
		Thread.sleep(3000);
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/code_Inuput'), Cod)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/firstName_Input'), FirstName)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/lastName_Input'), LastName)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/email_Input'), email)
		WebUI.setText(findTestObject('Suite_Module/UserCreation_Page/username_Input'), User)
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserCreation_Page/password_Input'), Password)
		WebUI.setEncryptedText(findTestObject('Suite_Module/UserCreation_Page/confirmPassword_Input'), Password)

		if(ITAR.equalsIgnoreCase("yes")) {
			WebUI.scrollToElement(findTestObject('Suite_Module/UserCreation_Page/ITAR_CheckBox'), 10)
			switchFrameAndDoActions("ITARCheckBox", "//div/div/input[@id='ChangeItar']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		} else {
			KeywordUtil.logInfo("ITAR is not required..!")
		}

		if(ChangePassword.equalsIgnoreCase("no")) {
			WebUI.scrollToElement(findTestObject('Suite_Module/UserCreation_Page/changePassword_Checkbox'), 10)
			switchFrameAndDoActions("ITARCheckBox", "//div/div/input[@id='Changelogin']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		} else {
			KeywordUtil.logInfo("Change password on next logon checkbox is already checked..!")
		}

		WebUI.click(findTestObject('Suite_Module/UserCreation_Page/save_Button'))

		try {
			WebUI.scrollToPosition(0, 0)
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
			KeywordUtil.logInfo("User creation is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User creation is failed.. ! Success message is not displayed.. !")
		}

		return Cod;
	}

	@Keyword
	public void searchAndValidateUser() {
		searchUser("empcode");
		Position = validateSearchUser("empcode","users_page", Cod);
		if(Position!=0) {
			KeywordUtil.logInfo("User found in the search option.. !")
		} else {
			throw new Error("User not found in the search option.. !")
		}
	}

	@Keyword
	public void deleteUser() {

		SelectUser(Position,"User_Page")
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

	public void SelectUser(String position,String pageName) {
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
			KeywordUtil.logInfo("*******************"+ position)
		}
		WebUI.waitForJQueryLoad(20)
		WebUI.click(to)
		WebUI.delay(3);
		//WebUI.click(findTestObject('Checkbox'))
		WebUI.switchToDefaultContent()
	}

	public void searchUser(String searchOption) {
		WebUI.waitForPageLoad(30);
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		if(rowSize>=1) {
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUserFilter_Dropdown'),
					searchOption, true)
			switch (searchOption) {
				case "empcode":
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Cod)
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Cod)
			}
		}
	}

	@Keyword
	public String ChangeForgotPassword(String NewPassword) {

		WebUI.click(findTestObject('Object Repository/Login_Page/forgotPassword_Link'))

		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotUserName_TextBox'), User)

		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotEmpCode_TextBox'), Cod)

		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotEmail_TextBox'), email)

		WebUI.click(findTestObject('Object Repository/Login_Page/validateUser_Button'))

		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotNewPassword_TextBox'), NewPassword)

		WebUI.setText(findTestObject('Object Repository/Login_Page/forgotConfirmNewPassword_TextBox'), NewPassword)

		WebUI.click(findTestObject('Object Repository/Login_Page/forgotPassSubmit_Button'))

		WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/passwordChangedSuccessMessage'), 20)

		KeywordUtil.logInfo("Successfully Updated")

		return User;
	}

	@Keyword
	public void unlockUser() {
		//pageRecordSizeChange("100")
		userUnlock("doNotDelete_01")
		WebUI.closeBrowser()
	}

	public void userUnlock(String user) {
		selecttheUser(user)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UnlockUser_Page/unlockUserSuccessMessage'))
		KeywordUtil.logInfo("User unlocked successfully..! Success message verified..!")
	}

	public void selecttheUser(String user) {
		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_UnlockUsersListing']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(user)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_UnlockUsersListing']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("User data found and selected.. !")
		} else {
			throw new Error("User not found.. Please check the data !");
		}
	}



}
