//import com.kms.katalon.core.annotation.AfterTestCase
//import com.kms.katalon.core.annotation.BeforeTestCase
//import com.kms.katalon.core.context.TestCaseContext
//import com.kms.katalon.core.util.KeywordUtil
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//
//class TestListener {
//
//	@BeforeTestCase
//	def beforeTestCase(TestCaseContext testCaseContext) {
//		// Optional: log before test case starts
//		println "Starting test case: ${testCaseContext.getTestCaseId()}"
//	}
//
//	@AfterTestCase
//	def afterTestCase(TestCaseContext testCaseContext) {
//		// After each test case completes
//		if (testCaseContext.getTestCaseStatus() == TestCaseStatus.FAILED) {
//			// Capture a screenshot if test case fails
//			String screenshotPath = "TestCase_Failure_" + testCaseContext.getTestCaseId() + ".png"
//			WebUI.takeScreenshot(screenshotPath)
//			println "Test case failed. Screenshot saved at: ${screenshotPath}"
//
//			// Optionally log the failure
//			KeywordUtil.markFailed("Test case failed: ${testCaseContext.getTestCaseId()}")
//
//			// Exit the current iteration (if running in a loop or data-driven test)
//			// This will stop the execution of the current test case iteration
//			throw new Exception("Stopping current iteration due to test failure.")
//		} else {
//			// Optionally log success for passing test cases
//			println "Test case passed: ${testCaseContext.getTestCaseId()}"
//		}
//	}
//}
