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
class Positions_Page extends CommonClass{

	int position;

	public void validatePositionDeletionSuccessMessage() {
		try {
			WebUI.verifyElementVisible(findTestObject('Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'))
			KeywordUtil.logInfo("Position deletion is successful.. ! Success message verified..!")
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Position deletion is failed.. ! Success message is not displayed.. !")
		}
	}

	@And('Create new Position')
	public void createNewPosition() {
		if(!getData("Site").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject("Suite_Module/Positions_Page/site_DropDown"), getData("Site"), false)
		}
		Thread.sleep(3000)
		WebUI.click(findTestObject('Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.sendKeys(findTestObject("Suite_Module/Positions_Page/postion_TextBox"), getData("Position"));
		WebUI.sendKeys(findTestObject("Suite_Module/Positions_Page/positionDescription_TextBox"), "Test Automation");
		switchFrameAndDoActions("saveButton", "//div/*[@id='btnSave']", "jsClick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.verifyElementVisible(findTestObject('Suite_Module/UserCreation_Page/successMessage'))
		KeywordUtil.logInfo("City creation is successful.. ! Success message verified..!")
	}

	@And('Validate Position creation')
	public void validatePositionCreation() {
		if(!getData("Site").isEmpty()) {
			WebUI.selectOptionByLabel(findTestObject("Suite_Module/Positions_Page/site_DropDown"), getData("Site"), false)
		}
		Thread.sleep(2000)
		int rowSize= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		System.out.println("Size is - " +rowSize)
		if(rowSize>1) {
			WebUI.setText(findTestObject('Suite_Module/UsersDetailsHome_Page/searchUser_Input'), getData("Position"))
		}
		pageRecordSizeChange("100");
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		Thread.sleep(2000)
		int rowSize1= DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=rowSize1;i++) {
			String val = DriverFactory.getWebDriver().findElement(By.xpath("(//tbody/tr[@role='row'])["+i+"]/td[2]/a")).getText()
			if(val.equals(getData("Position"))) {
				position=i;
				KeywordUtil.logInfo("Position data found.. !")
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(position!=0) {
			KeywordUtil.logInfo("Position found in the search option.. !")
		} else {
			throw new Error("Position not found in the search option.. !")
		}
	}

	@And('Delete Position')
	public void deletePosition() {
		switchFrameAndDoActions("Citychkbox", "(//*[@name='PositionGrid_selectCheck'])["+position+"]", "click",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		DriverFactory.getWebDriver().findElement(By.id("dbtndelete")).click();
		WebUI.switchToDefaultContent();
		WebUI.click(findTestObject('Suite_Module/City_Page/poupUpOk_Button'));
		validatePositionDeletionSuccessMessage();
		KeywordUtil.logInfo("Position deleted successfully..!")
	}
}