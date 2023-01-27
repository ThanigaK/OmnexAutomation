/*
 * import com.kazurayam.ks.plainreport.ReportGenerator import
 * com.kms.katalon.core.annotation.AfterTestCase import
 * com.kms.katalon.core.annotation.AfterTestSuite import
 * com.kms.katalon.core.annotation.BeforeTestCase import
 * com.kms.katalon.core.annotation.BeforeTestSuite import
 * com.kms.katalon.core.context.TestCaseContext import
 * com.kms.katalon.core.context.TestSuiteContext import
 * com.kms.katalon.core.configuration.RunConfiguration import java.nio.file.Path
 * import java.nio.file.Paths
 * 
 * class TLPlainReport { private ReportGenerator reportgen
 * 
 * // TLPlainReport() { // reportgen = new ReportGenerator() //
 * reportgen.setOutputDir("./PlainReport") // } // // @BeforeTestSuite // def
 * beforeTestSuite(TestSuiteContext testSuiteContext) { //
 * CustomKeywords.'cloudQa.KeyWord.PreRequisite'() // } // // @BeforeTestCase //
 * def beforeTestCase(TestCaseContext testCaseContext) { //
 * CustomKeywords.'cloudQa.KeyWord.login'() // }
 * 
 * @AfterTestCase def afterTestCase(TestCaseContext testCaseContext) {
 * CustomKeywords.'cloudQa.KeyWord.CloseBrowser'() } // // @AfterTestSuite //
 * def afterTestSuite(TestSuiteContext testSuiteContext) { //
 * CustomKeywords.'cloudQa.KeyWord.PostProcessing'() // } }
 */