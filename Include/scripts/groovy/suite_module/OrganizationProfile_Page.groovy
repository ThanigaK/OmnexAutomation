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
class OrganizationProfile_Page extends CommonClass{

	public void organizationProfileDatilsUpdate() {
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachCompanyLogoLarge_Button'))
		addImage("D:/Omnex/Latest/EWQIMS/Files/Big_ClientLogo_Omnex.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachCompanyLogoMedium_Button'))
		addImage("D:/Omnex/Latest/EWQIMS/Files/small_ClientLogo_Omnex.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/attachProductLogo_Button'))
		addImage("D:/Omnex/Latest/EWQIMS/Files/Big_ClientLogo_Omnex.png")
		WebUI.click(findTestObject('Object Repository/Suite_Module/EntityAndEntityOwner_Page/saveEntityOwner_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'))
		KeywordUtil.logInfo("Organization profile updated successfully..! Success message verified..!")
	}

	public void addImage(String filePath) {
		WebUI.sendKeys(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/chooseFile_Button'), filePath)
		WebUI.click(findTestObject('Object Repository/Suite_Module/OrganizationProfile_Page/done_Button'))
	}

	@And('Update organization details')
	public void updateOrganizationDetails() {
		organizationProfileDatilsUpdate()
	}
}