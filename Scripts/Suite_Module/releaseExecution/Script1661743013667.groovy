import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CucumberKW.runFeatureFile('Include/features/Suite_Module/UserCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Preferences.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/ChangePassword.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/CityCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/StateCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/CountryCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/PositionCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/GroupsCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/ModulesFunctionalities.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Manufacturer.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Shift.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Meetings.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Entity and Entity owners.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Supplier.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/AdminSettings.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/LoginPageFunctionalities.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Teams.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/UnlockUser.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/Customers.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/PositionCreation.feature')

CucumberKW.runFeatureFile('Include/features/Suite_Module/OrganizationProfile.feature')

