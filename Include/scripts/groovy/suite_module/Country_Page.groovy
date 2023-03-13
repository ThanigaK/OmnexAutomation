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
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad

import common.CommonClass
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



/**
 * @author sibis
 *
 */
class Country_Page extends CommonClass{
	int countryPosition;

	public void validateCountryDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/City_Page/cityDeletionSuccessMessage'))
			KeywordUtil.logInfo("Country deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Country deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	@And('Create new Country')
	public void createNewCountry() {
		WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 10)
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Suite_Module/Country_Page/country_TextBox'), getData("Country/Region"))
		WebUI.click(findTestObject('Suite_Module/Country_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
		KeywordUtil.logInfo("Country creation is successful.. ! Success message verified..!")
	}

	@And('Validate Country creation')
	public void validateCountryCreation() {
		WebUI.waitForElementClickable(findTestObject('Suite_Module/UsersDetailsHome_Page/tableRecordsLength'), 10)
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		WebUI.switchToDefaultContent()
		if(rowSize>=1) {
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), getData("Country/Region"))
			pageRecordSizeChange("100");
		}

		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row'])["+i+"]/td[2]/a")).getText()
			if(val.equals(getData("Country/Region"))) {
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

	@And('Delete Country')
	public void deleteState() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='Grid_CountryListing_selectCheck'])["+countryPosition+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		validateCountryDeletionSuccessMessage();
		KeywordUtil.logInfo("Country deleted successfully..!")
	}
}