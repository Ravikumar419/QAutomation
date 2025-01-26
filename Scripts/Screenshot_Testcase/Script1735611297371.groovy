import com.etiqa.framework.common.ExcelHandler
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
import groovy.io.PlatformLineWriter as PlatformLineWriter
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil
import com.etiqa.framework.common.ScreenshotHelper

// Get the number of rows with data in the sheet
int usedRangeRowCount = ExcelHandler.getUsedRangeRowCount()
println("Number of rows with data: " + usedRangeRowCount)

// Loop through the rows starting from row 4 (index 3)
for (int iterator = 4; iterator < (usedRangeRowCount - 1); iterator++) {
	// Fetch necessary data for the row once
	GlobalVariable.G_Functionality = ExcelHandler.getDataByHeaderAndRow('Functionality', iterator)?.trim() ?: ''
	GlobalVariable.G_Execute_Y_N = ExcelHandler.getDataByHeaderAndRow('Execute (Y/N)', iterator)?.trim() ?: ''
	GlobalVariable.G_Platform = ExcelHandler.getDataByHeaderAndRow('Platform', iterator)?.trim() ?: ''
	GlobalVariable.G_CurrentTCID = ExcelHandler.getDataByHeaderAndRow('TCID', iterator)?.trim() ?: ''
	GlobalVariable.G_TDSheetName = ExcelHandler.getDataByHeaderAndRow('Test Data Sheet Reference', iterator)?.trim() ?: ''
	GlobalVariable.G_TestCaseName = ExcelHandler.getDataByHeaderAndRow('Test Case Name', iterator)?.trim() ?: ''
	
	// Variables for screenshot name and description
	String screenshotName = "LoginScreen"
	String description = ""
	String screenshotPath = ""

	// Consolidate checks into a single if statement with better readability
	if (GlobalVariable.G_Execute_Y_N?.trim() &&
		'Y' == GlobalVariable.G_Execute_Y_N.toUpperCase() &&
		'MOBILE' == GlobalVariable.G_Platform.toUpperCase() &&
		'NEW APPLICATION' == GlobalVariable.G_Functionality.toUpperCase()) {
		
		println("Executing test case: " + GlobalVariable.G_CurrentTCID)
		println(ExcelHandler.getValueByKey("My client is buying this policy for"))
		
		// G_AppPath stores the bundle ID
		String appBundleId = GlobalVariable.G_AppPath
		
		// Start the existing application by its bundle ID [Maintained in profile]
		Mobile.startExistingApplication(appBundleId)
		
		// First screenshot before login
		description = "This screenshot shows the login screen before login."
		try {
			screenshotPath = ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, screenshotName, description)
			println "Screenshot saved at: ${screenshotPath}"
		} catch (Exception e) {
			println "Test execution failed: ${e.message}"
		}
		
		// Default login passcode will be entered using the below method
		CustomKeywords.'com.etiqa.framework.app.common.AppCommon.loginWithDefaultPassCode'()
		
		// Second screenshot after login
		screenshotName = "LoginScreen_AfterLogin"  // Update screenshot name to avoid overwriting
		description = "This screenshot shows the login screen after successful login."
		try {
			screenshotPath = ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, screenshotName, description)
			println "Screenshot saved at: ${screenshotPath}"
		} catch (Exception e) {
			println "Test execution failed: ${e.message}"
		}
		ScreenshotHelper.saveWordDocument()
	}
}
