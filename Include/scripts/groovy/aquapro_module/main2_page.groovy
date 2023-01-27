package aquapro_module
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
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
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
class main2_page extends CommonClass{

	JavascriptExecutor jsExecutor = (JavascriptExecutor)DriverFactory.getWebDriver();

	public void goToOption(String option){
		switchFrameAndDoActions(option, "//*[@title='"+option+"']","click",findTestObject('Home_Page/detailView_iFrame'));
	}

	public void openExportEount(String docName){
		switchFrameAndDoActions("docName", "//li/span[text()='"+docName+"']","rightClick",findTestObject('Home_Page/detailView_iFrame'))
		WebUI.click(findTestObject('AquaPro_Module/Main2_Page/openExportCount_Option'))
		WebUI.click(findTestObject('AquaPro_Module/Main2_Page/ok_Button'))
	}

	public void enterDataInDoc(String cellType,String data,String rowAndColumnNum) {
		String[] cellVal = cellType.split("\\|");
		String[] dataVal = data.split("\\|");
		String[] rowColVal = rowAndColumnNum.split("\\|");

		for(int i=0; i<cellVal.size();i++) {
			String[] rowCol = rowColVal[i].split("\\,");

			String xpath = "//*[@id='doctable']/tbody/tr["+rowCol[0]+"]/td["+rowCol[1]+"]";
			switchFrameAndDoActions("docDataCell_"+i.toString(), xpath,"doubleClick",findTestObject('Home_Page/detailView_iFrame'))
			switch (cellVal[i]) {
				case "textbox":
					WebUI.sendKeys(findTestObject("AquaPro_Module/Main2_Page/textArea_Textbox"), dataVal[i])
					break;
				default:
					System.out.println("Cell type not matched.. ! Please chech the data")
			}
		}
	}

	public void createHyperLink(String hyperLinkType,String hyperLinkRowCol,String hyperLinkPath) {
		String[] rowCol = hyperLinkRowCol.split("\\,");
		String xpath = "//*[@id='doctable']/tbody/tr["+rowCol[0]+"]/td["+rowCol[1]+"]";
		switchFrameAndDoActions("hyperLinkType",xpath,"rightClick")

		switch(hyperLinkType) {
			case "DocPro HyperLink" :
			case "DocPro Image HyperLink":
				String[] path = hyperLinkPath.split("/");
				if(hyperLinkType.equalsIgnoreCase("DocPro HyperLink")) {
					WebUI.click(findTestObject('AquaPro_Module/Main2_Page/createDocProHyperLink_Option'))
				} else {
					WebUI.click(findTestObject('AquaPro_Module/Main2_Page/createDocProImageHyperLink_Option'))
				}
				for(int i=0;i<path.size();i++) {
					if(i!=path.size()-1) {
						switchFrameAndDoActions("hyperLinkPath_"+i.toString(), "//span[text()='"+path[i]+"']/preceding-sibling::span","click",findTestObject('Home_Page/detailView_iFrame'))
					} else {
						switchFrameAndDoActions("hyperLinkPath_"+i.toString(), "//span[text()='"+path[i]+"']","click",findTestObject('Home_Page/detailView_iFrame'))
					}
				}
				break;

			case "AquaPro HyperLink":
				WebUI.click(findTestObject('AquaPro_Module/Main2_Page/createAquaProHyperLink_Option'))
				WebUI.selectOptionByLabel(findTestObject('AquaPro_Module/Main2_Page/selectAquaProDoc_PopupDropDown'), hyperLinkPath, false)
				break;

			case "Non-controlled HyperLink":
				WebUI.click(findTestObject('AquaPro_Module/Main2_Page/createNonControlledHyperLink_Option'))
				WebUI.setText(findTestObject('AquaPro_Module/Main2_Page/nonControlledHyperLink_Textbox'), hyperLinkPath)
				break;
			default:
				System.out.println("Hyper link type not matched.. ! Please check the data")
		}
		WebUI.click(findTestObject('AquaPro_Module/Main2_Page/ok_Button'));
	}

	public void validateHyperLink(String hyperLinkRowCol,String createOrDeleted) {
		String[] rowCol = hyperLinkRowCol.split("\\,");

		String xpath = "//*[@id='doctable']/tbody/tr["+rowCol[0]+"]/td["+rowCol[1]+"]/div/a";

		WebUI.switchToFrame(findTestObject('Home_Page/detailView_iFrame'), 5)
		TestObject to = new TestObject("hyperLink")
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
		WebUI.waitForElementPresent(to, 10);
		if(createOrDeleted.equalsIgnoreCase("created")) {
			if(WebUI.getAttribute(to, "class").equals("link")) {
				KeywordUtil.markPassed("Hyper link is generated.. !")
			} else {
				KeywordUtil.markErrorAndStop("Hyper link is not generated.. !")
			}
		} else {
			if(!WebUI.verifyElementNotPresent(to, 5)) {
				KeywordUtil.markErrorAndStop("Hyper link is not deleted.. !")
			} else {
				KeywordUtil.markPassed("Hyper link is deleted.. !")
			}
		}
		WebUI.switchToDefaultContent();
	}

	public void deleteHyperLink(String hyperLinkRowCol) {
		String[] rowCol = hyperLinkRowCol.split("\\,");
		String xpath = "//*[@id='doctable']/tbody/tr["+rowCol[0]+"]/td["+rowCol[1]+"]/div/a";
		switchFrameAndDoActions("hyperLinkType",xpath,"rightClick",findTestObject('Home_Page/detailView_iFrame'));
		WebUI.click(findTestObject('AquaPro_Module/Main2_Page/deleteHyperLink_Option'));
	}


	@When("Go to Aqua Pro Main2 application")
	public void GotoAquaProMain2application() {
		WebUI.click(findTestObject('Home_Page/products_Icon'))
		WebUI.click(findTestObject('Home_Page/aquaPro_Icon'))
		WebUI.click(findTestObject('Home_Page/main2_Icon'))
		WebUI.mouseOver(findTestObject('Home_Page/menu_Icon'))
	}

	@And("Click on the product in (.*)")
	public void goToProduct(String productionItemType) {
		goToOption(productionItemType);
		WebUI.waitForElementClickable(findTestObject('AquaPro_Module/Main2_Page/globalExpand_Icon'), 30)
		WebUI.enhancedClick(findTestObject('AquaPro_Module/Main2_Page/globalExpand_Icon'))
		switchFrameAndDoActions("productionItem", "//li/span[text()='"+getData("Product item name")+"']","click",findTestObject('Home_Page/detailView_iFrame'))
	}

	@And("Open (.*) document in (.*)")
	public void openDocument(String docName, docType) {
		switchFrameAndDoActions("docType", "//*[@title='"+docType+"']","click",findTestObject('Home_Page/detailView_iFrame'))
		openExportEount(docName);
	}

	@And("Create (.*) in main2")
	public void createHyperLink(String hyperLinkType) {
		String hyperLink;
		if(hyperLinkType.equalsIgnoreCase("DocPro HyperLink")) {
			hyperLink = getData("Docpro HyperLink path");
		} else if(hyperLinkType.equalsIgnoreCase("DocPro Image HyperLink")) {
			hyperLink = getData("Docpro image HyperLink path");
		} else if(hyperLinkType.equalsIgnoreCase("AquaPro HyperLink")) {
			hyperLink = getData("Aquapro HyperLink docname");
		} else if(hyperLinkType.equalsIgnoreCase("Non-controlled HyperLink")) {
			hyperLink = getData("Non-controlled HyperLink");
		}
		createHyperLink(hyperLinkType,getData("HyperLink row and column"), hyperLink)
	}

	@Then("Validate (.*) HyperLink in main2")
	public void validateHyperLinkInMain2(String created) {
		validateHyperLink(getData("HyperLink row and column"), created);
	}

	@And("Delete HyperLink in main2")
	public void deleteHyperLinkInMain2() {
		deleteHyperLink(getData("HyperLink row and column"));
	}
}