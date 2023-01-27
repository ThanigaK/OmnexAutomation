package common;

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author sibis
 *
 */
public class GlobalAnnotations extends CommonClass {

	@Before
	public void before(Scenario scenario) {
		try {
			int totRows = findTestData(getPropertyValue("Global", "ExcelFileName")).getRowNumbers();
			for (int i = 1; i <= totRows; i++) {
				String scenarioName = findTestData(getPropertyValue("Global", "ExcelFileName"))
						.getValue("Scenario name", i);
				if (scenarioName.contains(scenario.getName())) {
					rowNum = i;
					break;
				}
			}
			if (rowNum == 0) {
				throw new Exception(scenario.getName() + " -- This Scenario is not available.. !");
			}
			System.out.println("Taken data row number is : " + rowNum);

			SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyyHHmmss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			dateTimeStr = sdf1.format(timestamp);

			if (getData("Code").equalsIgnoreCase("random")) {
				code = "T-" + dateTimeStr;
			} else {
				code = getData("Code");
			}
			if (getData("User name").equalsIgnoreCase("random")) {
				userName = "Test-" + dateTimeStr;
			} else {
				userName = getData("User name");
			}
			if (getData("Email").equalsIgnoreCase("random")) {
				email = dateTimeStr + "@gmail.com";
			} else {
				email = getData("Email");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		rowNum = 0;
	}
}