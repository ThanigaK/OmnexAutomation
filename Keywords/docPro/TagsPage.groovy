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

public class TagsPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String Position;
	static String tag;

	@Keyword
	public void CreateTag(String TagName) {
		if (TagName.equalsIgnoreCase("random")) {
			TagName = "Automation - " + DateTimeStr;
			tag = TagName;

			WebUI.click(findTestObject('Object Repository/Suite_Module/Manufacturer_Page/addAddress_Button'))
			WebUI.setText(findTestObject('Object Repository/Suite_Module/Tags_Page/tags_TextBox'), tag)
			WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/save_Button'))
			WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/UserPreferences_Page/successMessageInChangePassword'), 15)
			KeywordUtil.logInfo("Tag is created Successfully..! Tag name is : "+tag)
		}
	}

	@Keyword
	public void modifyTag() {
		searchTag(tag)
		tag = "Updated-"+tag;
		tagNameModification(tag)
	}

	@Keyword
	public void deleteTag() {
		searchTag(tag)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/delete_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUserSuccessMessage'), 15)
		KeywordUtil.logInfo("Tag is deleted Successfully..! Tag name is : "+tag)
	}

	public void searchTag(String name) {
		Boolean flag=false;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)


		WebUI.switchToDefaultContent()
		pageRecordSizeChange('100')

		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[3]")).getText();
			if(val.equals(name)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='FldrTagsGrid']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Tag data found and selected.. !")
		} else {
			throw new Error("Tag not found.. Please check the data ! Actual data is : " + val);
		}
	}

	public void tagNameModification(String name) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/editTag_Button'))
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Tags_Page/tags_TextBox'), name)
		WebUI.click(findTestObject('Object Repository/Suite_Module/Tags_Page/save_Button'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Suite_Module/Tags_Page/updateMessage_Text'), 15)
		KeywordUtil.logInfo("Tag name is updated Successfully..! Tag name is : "+name)
	}
}
