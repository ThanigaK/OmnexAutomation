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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable

public class KeyWord extends common {

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String CustomerName;
	static String countryPosition;
	public static WebDriver driver1;
	public static WebDriver driver2;

	/**
	 * Login to the application
	 */
	@Keyword
	public static void Login() {
		try {
			WebUI.openBrowser('')
			WebUI.maximizeWindow()
			WebUI.deleteAllCookies();
			WebUI.navigateToUrl(GlobalVariable.url)
			WebUI.setText(findTestObject('Login_Page/userName_Input'), GlobalVariable.username)
			WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), GlobalVariable.Password)
			WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))
			WebUI.click(findTestObject('Login_Page/login_Button'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error Login");
		}
	}

	/**
	 * Navigate to the Administrator setting page from the setup menu
	 */
	@Keyword
	public static void NavigateToAdminSettingPage() {
		try {
			'Check wheather the Setup window is already opened or not\r\n'
			if (WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), 'class').contains('mini-sidebar')) {
				WebUI.click(findTestObject('Home_Page/menu_Icon'))
			}
			'If not click the setup Icon'
			WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))

			waitForClickableAndClick(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)

			'Scroll to the admin setting option \r\n'
			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/adminSettings_Option'), 15)
			'Click on Admin setting\r\n'
			WebUI.click(findTestObject('Object Repository/Home_Page/adminSettings_Option'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to navigate");
		}
	}

	/**
	 * Modify the fields in the administration page
	 * @param PasswordLength
	 * @param First
	 * @param Middle
	 * @param Last
	 * @param Symbol
	 */
	@Keyword
	public static void updateAdminSettingPage(String PasswordLength, String First, String Middle, String Last, String Symbol) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/passwordFormat_ComboBox'), FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(findTestObject('Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), 'Alphanumeric' + Keys.ENTER)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/passwordLength_TextBox'), PasswordLength + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/first_ComboBox'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), First + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/second_ComboBox'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), Symbol + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/third_ComboBox'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), Middle + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/fourth_ComboBox'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), Symbol + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/fifth_ComboBox'))
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/AdminsSettings_Page/searchInDropDown_TextBox'), Last + Keys.ENTER)
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo('Successfully Modified')
	}

	/**
	 * Logout from the application
	 */
	@Keyword
	public static void Logout() {
		WebUI.click(findTestObject('Home_Page/userProfile_Icon'))
		WebUI.click(findTestObject('Home_Page/logout_Button'))
		WebUI.click(findTestObject('Home_Page/popupOk_Button'))
	}

	@Keyword
	public static void NavigateToCityPage() {
		try {
			if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
				WebUI.click(findTestObject('Home_Page/menu_Icon'))
			}
			WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
			if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
				WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
				WebUI.click(findTestObject('Home_Page/userSystem_Option'))
			}
			WebUI.scrollToElement(findTestObject('Home_Page/city_Option'), 15)
			WebUI.click(findTestObject('Home_Page/city_Option'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to navigate");
		}
	}

	@Keyword
	public static void CreateCity(String Country, String state, String City) {
		Thread.sleep(3000)

		if(Country.equals("India")) {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), "India" +Keys.ENTER);
		} else {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), Country +Keys.ENTER);
			KeywordUtil.logInfo("Continue with the default Country..!")
		}

		if(state.equals("Tamilnadu")) {
			WebUI.click(findTestObject('Suite_Module/City_Page/state_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), state +Keys.ENTER);
		} else {
			KeywordUtil.logInfo("Continue with the default State..!")
		}

		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Suite_Module/City_Page/city_TextBox'), City)
		WebUI.click(findTestObject('Suite_Module/City_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
		KeywordUtil.logInfo("City creation is successful.. ! Success message verified..!")
	}

	@Keyword
	public static void NavigateToCustomerPage() {
		try {
			if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
				WebUI.click(findTestObject('Home_Page/menu_Icon'))
			}
			WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
			if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
				WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
				WebUI.click(findTestObject('Home_Page/userSystem_Option'))
			}
			WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/customers_Option'), 15)
			WebUI.click(findTestObject('Object Repository/Home_Page/customers_Option'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to navigate");
		}
	}

	@Keyword
	public void createCustomer(String Name, String Code) {
		Rand = DateTimeStr;

		if (Name.equalsIgnoreCase("random")) {
			CustomerName = "Test Automation - " + Rand;
		} else {
			Name = "Rand";
		}

		if (Code.equalsIgnoreCase("random")) {
			Code = "T-" + Rand;
		} else {
			Code = "Rand"
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),20)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerCode_TextBox'), Code)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/manufacturerName_TextBox'), CustomerName+Keys.TAB)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'), 15)
	}


	@Keyword
	public void AddAddress(String Country, String State, String City) {
		WebUI.scrollToElement(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'), 15)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/street_TextBox'), "Automation street")
		if(!Country.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/country_DropDown'), Country, false)
		}
		if(!State.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/state_DropDown'), State, false)
		}
		if(!City.isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/city_DropDown'), City, false)
		}
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/zipcode_TextBox'), "251098")
		WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Address successfully added..! Success message verified..!")
	}

	@Keyword
	public void pageRecordSizeChange() {
		if(WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'))) {
			WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'), 20)
			WebUI.selectOptionByValue(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'),
					'100', true)
		} else {
			KeywordUtil.logInfo("Page size dropdown not displaying.. Continue the flow")
		}
	}

	@Keyword
	public void DeleteCustomer() {
		Boolean flag;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[2]/a")).getText();
			if(val.equals(CustomerName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='SuSupplierGridViewControl']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}

		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Customer data found and selected.. !")
		} else {
			throw new Error("Customer not found.. Please check the data !");
		}

		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Deleted Success message verified..!")
	}

	@Keyword
	public void validateRememberMeOption(String Username) {
		WebUI.setText(findTestObject('Login_Page/userName_Input'), Username+Keys.TAB)
		WebUI.click(findTestObject('Login_Page/login_Button'))
		if(WebUI.getWindowTitle().equals("EwQIMS")){
			KeywordUtil.logInfo("Remember me option validated successfully..!")
		} else {
			throw new Exception("Remember me option not working properly..--> FAILED")
		}
	}

	@Keyword
	public void NavigateToMeetingsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/meetings_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/meetings_Option'))
	}

	@Keyword
	public void NavigateToCountryPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/country_Option'), 15)
		WebUI.click(findTestObject('Home_Page/country_Option'))
	}

	@Keyword
	public void validateCountryCreation(String Country) {
		WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'), 10)
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(rowSize>=1) {
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), Country)
			pageRecordSizeChange("100");
		}

		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row'])["+i+"]/td[2]/a")).getText()
			if(val.equals(Country)) {
				countryPosition=i;
				KeywordUtil.logInfo("Country data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(countryPosition!=0) {
			KeywordUtil.logInfo("Country found in the search option.. !")
		} else {
			KeywordUtil.markErrorAndStop("Country not found in the search option.. !")
		}
	}

	@Keyword
	public void deleteCountry() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='Grid_CountryListing_selectCheck'])["+countryPosition+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		validateCountryDeletionSuccessMessage();
		KeywordUtil.logInfo("Country deleted successfully..!")
	}

	public WebDriver chooseBrowser() {
		String drivervalue = DriverFactory.getExecutedBrowser();
		if(drivervalue.equals("Chrome"))
			return openChromeBrowser()
		else if(drivervalue.equals("Edge Chromium"))
			return openEdgeBrowser()
		else if(drivervalue.equals("Firefox"))
			return openFireFoxBrowser()
		return null;
	}

	@Keyword
	public void newBrowser(String count) {
		String drivervalue = DriverFactory.getExecutedBrowser();
		System.println(drivervalue);
		if(count.equals("1")) {
			driver1 = chooseBrowser()
			DriverFactory.changeWebDriver(driver1)
		} else if(count.equals("2")){
			driver2 = chooseBrowser()
			DriverFactory.changeWebDriver(driver2)
		}
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.url)
	}

	public ChromeDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
		return new ChromeDriver();
	}
	public EdgeDriver openEdgeBrowser() {
		System.setProperty("webdriver.edge.driver", DriverFactory.getEdgeChromiumDriverPath()()())
		return new EdgeDriver();
	}
	public FirefoxDriver openFireFoxBrowser() {
		System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
		return new FirefoxDriver();
	}

	@Keyword
	public void validateUserUnlocked() {
		validateUnlockedUser(driver1)
		WebUI.closeBrowser()
	}

	public void validateUnlockedUser(WebDriver drive){
		DriverFactory.changeWebDriver(drive)
		//waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		//WebUI.enhancedClick(findTestObject('Object Repository/Home_Page/usersParent_OptionIcon'))
		new HomePage().NavigateToPreferencesPage()
		String alertText = WebUI.getAlertText()
		WebUI.verifyAlertPresent(15)
		KeywordUtil.logInfo("Session Expired alert displayed.. !")
	}

	@Keyword
	public static void LoginwithCredential(String Url, String UserName, String Password) {
		try {
			WebUI.openBrowser('')
			WebUI.maximizeWindow()
			WebUI.deleteAllCookies();
			WebUI.navigateToUrl(Url)
			WebUI.setText(findTestObject('Login_Page/userName_Input'), UserName)
			WebUI.setEncryptedText(findTestObject('Login_Page/password_Input'), Password)
			//WebUI.click(findTestObject('Object Repository/Login_Page/rememberMe_CheckBox'))
			WebUI.click(findTestObject('Login_Page/login_Button'))
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error Login");
		}
	}

	@Keyword
	public void NavigateToLevelsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/SuiteSetup'), 15)
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/levels_Page'), 15)
	}
}
