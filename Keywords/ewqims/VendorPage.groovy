package ewqims

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

public class VendorPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String VendorName;
	static String VendorCode;
	static String Position;

	@Keyword
	public void createVendor(String Name, String Code) {
		Rand = DateTimeStr;

		if (Name.equalsIgnoreCase("random")) {
			VendorName = "Test Automation - " + Rand;
		} else {
			VendorName = "Rand"
		}

		if (Code.equalsIgnoreCase("random")) {
			VendorCode = "T-" + Rand;
		} else {
			VendorCode = "T12345"
		}

		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'), VendorName)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), VendorCode+Keys.TAB)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'), 30)
	}

	@Keyword
	public void addContact() {
		String Mail = DateTimeStr;
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/contactAdd_Icon'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addContact_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/firstName_TextBox'),"Test")
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/lastName_TextBox'), "Automation")
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/email_TextBox'), "Test"+Mail+"@gmail.com")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/saveContact_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Contact successfully added..! Success message verified..!")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/close_Icon'))
	}

	@Keyword
	public void DeleteVendor() {
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(VendorName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Manufacturer/Vendor data found and selected.. !")
		} else {
			throw new Error("Manufacturer/Vendor not found.. Please check the data ! Actual data is : " + val);
		}

		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}


	@Keyword
	public void ValidateUser(String SearchOption, String Code, String PageName) {
		searchUser(SearchOption, Code)
		Position = validateSearchUser(SearchOption,Code,PageName);
		if(Position!=0) {
			KeywordUtil.logInfo("User found in the search option.. !")
		} else {
			throw new Error("User not found in the search option.. !")
		}
	}

	public int validateSearchUser(String searchOption,String pageName, String Code) {
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
					dataValue=Code;
					break;
				default:
					KeywordUtil.logInfo("By default searching user with code")
					if(pageName.contains("users_page")) {
						data = 2;
					} else {
						data=3;
					}
					dataValue=Code;
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

	@Keyword
	public void RemoveUserFromDocPro(String Country,String Module, String Code) {
		Thread.sleep(3000)
		selectCountry(Country)
		selectModule(Module)
		int position = searchUser(Code);
		selectUser(position,"modules_page");
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))

		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
			KeywordUtil.logInfo("User deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	public void selectModule(String moduleName) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSelection_DropDown'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSearch_TextBox'), moduleName+Keys.ENTER);
	}


	public int searchUser(String code) {
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
		WebUI.switchToDefaultContent()
	}
}
