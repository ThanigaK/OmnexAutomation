package docPro

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import org.openqa.selenium.Keys
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class HomePage extends common {

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	static String countryposition;
	static String entity;

	@Keyword
	public void NavigateToVendorPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/vendor_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/vendor_Option'))
	}

	@Keyword
	public void NavigateToTagsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/tags_Option'), 15)
	}

	@Keyword
	public void goToManufacturerPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/manufacturer_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/manufacturer_Option'))
	}

	@Keyword
	public void NavigateToSupplierPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/supplier_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/supplier_Option'))
	}

	@Keyword
	public void NavigatetoStatepage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/state_Option'), 15)
		WebUI.click(findTestObject('Home_Page/state_Option'))
	}

	@Keyword
	public void NavigateToShiftPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/shift_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/shift_Option'))
	}

	@Keyword
	public void NavigateToOrganizationProfilePage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/organizationProfile_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/organizationProfile_Option'))
	}

	@Keyword
	public void organizationProfileDatilsUpdate() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachCompanyLogoLarge_Button'))
		addImage("D:\\Work\\Omnex\\EWQIMS (3)\\EWQIMS\\Files\\Big_ClientLogo_Omnex.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachCompanyLogoMedium_Button'))
		addImage("D:\\Work\\Omnex\\EWQIMS (3)\\EWQIMS\\Files\\small_ClientLogo_Omnex.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachProductLogo_Button'))
		addImage("D:\\Work\\Omnex\\EWQIMS (3)\\EWQIMS\\Files\\EWQIMS-Logo.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Organization profile updated successfully..! Success message verified..!")
	}

	public void addImage(String filePath) {
		WebUI.waitForPageLoad(10)
		WebUI.waitForAngularLoad(10)
		WebUI.waitForElementPresent(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/chooseFile_Button'), 0)
		WebUI.uploadFile(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/chooseFile_Button'), filePath)
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/done_Button'))
	}

	@Keyword
	public void NavigateToPositionsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/position_Option'), 15)
		WebUI.click(findTestObject('Home_Page/position_Option'))
	}

	@Keyword
	public void NavigateToTeamsPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/systemSubMenu_DropDown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/userSystem_Option'), 15)
			WebUI.click(findTestObject('Home_Page/userSystem_Option'))
		}
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/teams_Option'), 15)
		WebUI.click(findTestObject('Object Repository/Home_Page/teams_Option'))
	}

	@Keyword
	public void NavigateToModulesPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		WebUI.click(findTestObject('Home_Page/setup_OptionIcon'))
		WebUI.scrollToElement(findTestObject('Home_Page/Modules_Option'), 15)
		WebUI.click(findTestObject('Home_Page/Modules_Option'))
	}

	@Keyword
	public void ValidateUser(String SearchOption, String Code) {
		searchUser(SearchOption, Code)
	}

	@Keyword
	public void NavigateToUsersPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}
		WebUI.scrollToElement(findTestObject('Home_Page/usersChild_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/usersChild_OptionIcon'), 15)
	}

	@Keyword
	public void NavigateToPreferencesPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}

		WebUI.scrollToElement(findTestObject('Home_Page/usersPreferences_Option'), 15)
		waitForClickableAndClick(findTestObject('Home_Page/usersPreferences_Option'), 15)
	}

	@Keyword
	public void addUserForModule(String moduleName, String Code) {
		String Country="";
		Thread.sleep(3000)
		if(!Country.isEmpty()) {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), Country+Keys.ENTER);
		} else {
			KeywordUtil.logInfo("Continue with the default Country..!")
		}
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSelection_DropDown'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Module_Page/moduleSearch_TextBox'), moduleName+Keys.ENTER);

		Boolean flag = false;
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))

		WebUI.selectOptionByValue(findTestObject('Object Repository/Suite_Module/Module_Page/searchUserOption_DropDown'), "Empcode", false)
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/Module_Page/searchUser_TextBox'), Code)
		WebUI.switchToFrame(findTestObject('Object Repository/Suite_Module/Meetings_Page/userTable_Frame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		for(int i =1; i<=10;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='userListingGridControl']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(Code)) {
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
				countryposition=i;
				KeywordUtil.logInfo("Country data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(countryposition!=0) {
			KeywordUtil.logInfo("Country found in the search option.. !")
		} else {
			KeywordUtil.markErrorAndStop("Country not found in the search option.. !")
		}
	}

	@Keyword
	public void DeleteCountry() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='Grid_CountryListing_selectCheck'])["+countryposition+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		validateCountryDeletionSuccessMessage();
		KeywordUtil.logInfo("Country deleted successfully..!")
	}

	@Keyword
	public void NavigateToEntityPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/entity_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/entity_Option'), 15)
	}

	@Keyword
	public void createNewEntity(String EntityName, String Code) {
		if (EntityName.equalsIgnoreCase("random")) {
			entity = "Test-" + DateTimeStr;
		} else {
			entity = EntityName
		}
		Thread.sleep(3000);
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/entityName_TextBox'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/entityName_TextBox'), entity)
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/addContactPerson_Button'))
		//		pageRecordSizeChange('100')
		selectUser("doNotDelete_01")
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/entitySave_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Entity is created successfully.. Success message verified..!")
	}

	@Keyword
	public void NavigateToEntityOwnerPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/entityOwner_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/entityOwner_Option'), 15)
	}

	@Keyword
	public void assignEntityOwner(String Code) {
		switchFrameAndDoActions("adminIcon", "//span[contains(text(),'"+entity+"')]/img", "jsclick",findTestObject('Home_Page/detailView_iFrame'))
		//		pageRecordSizeChange("100")
		selectUser(Code)
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Entity owner assigned.. Success message verified..!")
	}

	@Keyword
	public void DeleteEntity() {
		Thread.sleep(5000);
		switchFrameAndDoActions("entity", "//*[text()='"+entity+"']", "rightclick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/deleteEntity_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
		KeywordUtil.logInfo("Entity deleted successfully.. ! Deletion success message verified..!")
	}

	@Keyword
	public void NavigateToUnlockUserPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		if(!WebUI.getAttribute(findTestObject('Object Repository/Home_Page/usersSubMenu_Dropdown'), "class").contains("active")) {
			WebUI.scrollToElement(findTestObject('Home_Page/usersParent_OptionIcon'), 15)
			WebUI.click(findTestObject('Home_Page/usersParent_OptionIcon'))
		}

		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/unlockUser_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/unlockUser_Option'), 15)
	}

	@Keyword
	public void NavigateToGroupsPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/groups_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/groups_Option'), 15)
	}

	@Keyword
	public void NavigateToDocProSetupPage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/docProSetup_Option'), 15)
	}


	@Keyword
	public void NavigateToNewDocRequestPage() {

		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/newDocRequest_Option'), 15)
	}

	@Keyword
	public void goToDocumentsPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documents_Option'), 15)
	}

	@Keyword
	public void goToDocumentRoutePage() {
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			WebUI.click(findTestObject('Home_Page/menu_Icon'))
		}
		waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		WebUI.scrollToElement(findTestObject('Object Repository/Home_Page/documentRoute_Option'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documentRoute_Option'), 15)
	}
	
	@Keyword
	public void goToFavouritesPage() {
		WebUI.waitForPageLoad(30)
		if(WebUI.getAttribute(findTestObject('Object Repository/Home_Page/sideMiniBar'), "class").contains("mini-sidebar")) {
			waitForClickableAndClick(findTestObject('Home_Page/menu_Icon'), 15)
		}
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/documnets_Icon'), 15)
		waitForClickableAndClick(findTestObject('Object Repository/Home_Page/favourite_Option'), 15)
	}
}
