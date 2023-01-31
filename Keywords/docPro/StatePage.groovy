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

public class StatePage extends common {
	static int statePosition;

	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;

	@Keyword
	public void createNewState(String Country, String State) {
		if(!Country.isEmpty()) {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), Country+Keys.ENTER);
		} else {
			KeywordUtil.logInfo("Continue with the default Country..!")
		}
		WebUI.waitForElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'), 20);
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.setText(findTestObject('Suite_Module/State_Page/state_TextBox'), State)
		WebUI.click(findTestObject('Suite_Module/City_Page/save_Button'))
		WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
		KeywordUtil.logInfo("State creation is successful.. ! Success message verified..!")
	}

	@Keyword
	public void validateCityCreation(String Country, String State) {
		if(!Country.isEmpty()) {
			WebUI.click(findTestObject('Suite_Module/City_Page/country_DropDown'))
			WebUI.sendKeys(findTestObject('Suite_Module/City_Page/searchCountryState_TextBox'), Country+Keys.ENTER);
		} else {
			KeywordUtil.logInfo("Continue with the default Country..!")
		}
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		if(rowSize>=1) {
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), State)
		}
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row'])["+i+"]/td[2]/a")).getText()
			if(val.equals(State)) {
				statePosition=i;
				KeywordUtil.logInfo("State data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(statePosition!=0) {
			KeywordUtil.logInfo("State found in the search option.. !")
		} else {
			throw new Error("State not found in the search option.. !")
		}
	}

	@Keyword
	public void deleteState() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='Grid_StateListing_selectCheck'])["+statePosition+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		validateStateDeletionSuccessMessage();
		KeywordUtil.logInfo("State deleted successfully..!")
	}
}
