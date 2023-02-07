package suiteModule

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
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.util.KeywordUtil

public class TeamsPage extends common {
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static String DateTimeStr = sdf1.format(timestamp);
	public static String Rand;
	static String teamName;
	static String Mail;
	
	public void selectLeader(String userCode){
		WebUI.click(findTestObject('Object Repository/Suite_Module/Teams_Page/leaderName_Icon'))
		selectUser(userCode)
	}

	public void addExternalMember(String name,String email) {
		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/Teams_Page/addExternalTeamMem_Button'),20)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/externalMemberName_TextBox'), name)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/externalMemberEmail_TextBox'), email)
	}

	public void addMember(String userCode) {
		WebUI.click(findTestObject('Object Repository/Suite_Module/Teams_Page/addMember_Button'))
		selectUser(userCode)
	}


	@Keyword
	public void CreateTeam(String Team, String Code)
	{
		Rand = DateTimeStr
		Mail = "Test"+Rand+"@gmail.com";
		if (Team.equalsIgnoreCase("random")) {
			teamName = "Test Automation - " + Rand;
		} else {
			teamName = Rand
		}

		waitForClickableAndClick(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/add_Button'),30)
		selectLeader(Code)
		WebUI.setText(findTestObject('Object Repository/Suite_Module/Teams_Page/teams_TextBox'), teamName+Keys.TAB)
		addExternalMember("Test Automation",Mail)
		addMember("E001")
	}
	
	@Keyword
	public void DeleteTeam()
	{
		
		Boolean flag;
		String val;
		WebUI.switchToFrame(findTestObject('Object Repository/Home_Page/detailView_iFrame'), 15)
		Thread.sleep(2000)
		WebUI.waitForJQueryLoad(10)
		int size = DriverFactory.getWebDriver().findElements(By.xpath("//tbody/tr[@role='row']")).size()
		for(int i =1; i<=size;i++) {
			val = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_TeamListing']/tbody/tr["+i+"]/td[2]")).getText();
			if(val.equals(teamName)) {
				DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='Grid_TeamListing']/tbody/tr["+i+"]/td[1]/input")).click()
				flag= true;
				break;
			}
		}
		WebUI.switchToDefaultContent()
		if(flag) {
			KeywordUtil.logInfo("Team data found and selected.. !")
		} else {
			throw new Error("Team not found.. Please check the data ! Actual data is : " + val);
		}
		
		WebUI.click(findTestObject('Object Repository/Suite_Module/UsersDetailsHome_Page/deleteUser_Button'))
		WebUI.click(findTestObject('Object Repository/Suite_Module/Module_Page/popUpOk_Button'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Suite_Module/City_Page/cityDeletionSuccessMessage'))
		KeywordUtil.logInfo("Team deleted successfully.. ! Deleted Success message verified..!")
	}
	
}
