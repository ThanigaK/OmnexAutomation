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
import common.HomeAndLogin_Page
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
class UnlockUser_Page extends CommonClass{
	public void selectUser(String user) {
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

	public void userUnlock(String user) {
		selectUser(user)
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UnlockUser_Page/unlockUserSuccessMessage'))
		KeywordUtil.logInfo("User unlocked successfully..! Success message verified..!")
	}

	public void validateUnlockedUser(WebDriver drive){
		DriverFactory.changeWebDriver(drive)
		//waitForClickableAndClick(findTestObject('Home_Page/setup_OptionIcon'), 15)
		//WebUI.enhancedClick(findTestObject('Object Repository/Home_Page/usersParent_OptionIcon'))
		new HomeAndLogin_Page().goToPreferencesPage();
		String alertText = WebUI.getAlertText()
		WebUI.verifyAlertPresent(15)
		KeywordUtil.logInfo("Session Expired alert displayed.. !")
	}

	@When("Unlock user")
	public void unlockUser() {
		pageRecordSizeChange("100")
		userUnlock(code)
		WebUI.closeBrowser()
	}

	@When("Validate user unlocked")
	public void validateUserUnlocked() {
		validateUnlockedUser(driver1)
		WebUI.closeBrowser()
	}
}