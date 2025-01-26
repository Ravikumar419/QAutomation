import com.etiqa.framework.common.ExcelHandler
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.etiqa.framework.app.common.Customers
import internal.GlobalVariable as GlobalVariable
import com.etiqa.framework.common.LogHelper
import com.etiqa.framework.common.ScreenshotHelper
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil
import com.etiqa.framework.app.common.PotentialAreaOfDiscussion
import com.etiqa.framework.app.common.FinancialNeedsAnalysis
import com.etiqa.framework.app.common.RecommendedProducts
import com.etiqa.framework.app.common.Executor
import java.text.SimpleDateFormat;
import java.util.Date;
import com.etiqa.framework.app.common.AppCommon


//import com.etiqa.framework.app.common.AppCommon

// Get the number of rows with data in the sheet
int usedRangeRowCount = ExcelHandler.getUsedRangeRowCount()
println("Number of rows with data: " + usedRangeRowCount)
AppState = false
// Loop through the rows starting from row 3 (index 2)
for (int iterator = 4; iterator < (usedRangeRowCount - 1); iterator++) {
	// Fetch necessary data for the row once
	GlobalVariable.G_Functionality = ExcelHandler.getDataByHeaderAndRow('Functionality', iterator)?.trim() ?: ''
	GlobalVariable.G_Execute_Y_N = ExcelHandler.getDataByHeaderAndRow('Execute (Y/N)', iterator)?.trim() ?: ''
	GlobalVariable.G_Platform = ExcelHandler.getDataByHeaderAndRow('Platform', iterator)?.trim() ?: ''
	GlobalVariable.G_CurrentTCID = ExcelHandler.getDataByHeaderAndRow('TCID', iterator)?.trim() ?: ''
	GlobalVariable.G_TDSheetName = ExcelHandler.getDataByHeaderAndRow('Test Data Sheet Reference', iterator)?.trim() ?: ''
	GlobalVariable.G_TestCaseName = GlobalVariable.G_CurrentTCID  + " - "+ ExcelHandler.getDataByHeaderAndRow('Test Case Name', iterator)?.trim() ?: ''
	GlobalVariable.G_Environment = ExcelHandler.getDataByHeaderAndRow('Environment', iterator)?.trim() ?: ''
	GlobalVariable.G_IsTestPassed = true
	long startTime = System.currentTimeMillis();

	// Consolidate checks into a single if statement with better readability
	if (GlobalVariable.G_Execute_Y_N?.trim() &&
		'Y' == GlobalVariable.G_Execute_Y_N.toUpperCase() &&
		'MOBILE' == GlobalVariable.G_Platform.toUpperCase() &&
		'NEW APPLICATION' == GlobalVariable.G_Functionality.toUpperCase()
		) {
		println("Executing test case: " + GlobalVariable.G_CurrentTCID)
		
		ExcelHandler.writeToTestData("Policy Number", "E12345")
		ExcelHandler.writeToController('Execution Status', iterator, "PASSED")
		timestamp = System.currentTimeMillis()
		ExcelHandler.writeToController('Last Updated', iterator, timestamp.toString())
		ExcelHandler.writeToController('Error Message if any', iterator, GlobalVariable.G_ErrorMessage.toString())
		Thread.sleep(2000)
		long endTime = System.currentTimeMillis();
		// Convert start and end times to formatted date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy-HHmmss");

		String startFormatted = dateFormat.format(new Date(startTime));

		
		// G_AppPath stores the bundle ID
		String appBundleId = GlobalVariable.G_AppPath
		
		// Start the existing application by its bundle ID [Maintained in profile]
		Mobile.startExistingApplication(appBundleId)
//		Mobile.startApplication('/Users/etiqaqa/Downloads/EaSE-2.ipa', false)
		
		if (AppState == false) {
		AppCommon.loginWithDefaultPassCode()
		AppState = true
		}

		Customers.participantDetails()
//		Customers.payorDetails()
//		PotentialAreaOfDiscussion.NeedsAndPriority()
//		PotentialAreaOfDiscussion.InvestmentPreference()
//		PotentialAreaOfDiscussion.IntermediaryStatus()
//		FinancialNeedsAnalysis.ExistingCoverage()
//		RecommendedProducts.PurposeTransaction()
//		RecommendedProducts.ChooseProduct()
//		Executor.executePolicyDetails()
		


		
		String testStatus = ""
		if (GlobalVariable.G_IsTestPassed.toString() == "true"){
			testStatus = "PASSED"
		}
		if (GlobalVariable.G_IsTestPassed.toString() == "false"){
			testStatus = "FAILED"
			KeywordUtil.markFailed(GlobalVariable.G_ErrorMessage)
			ScreenshotHelper.captureErrorScreenshot(GlobalVariable.G_CurrentTCID, "Error Screen", GlobalVariable.G_ErrorMessage.toString())
		}
		String policyNumber = "12345"  // You should set this dynamically based on your test logic
		String errorMessage = "NA"
		String endFormatted = dateFormat.format(new Date(endTime));
		
		// Calculate the duration in milliseconds
		long durationMillis = endTime - startTime;

		// Convert duration to seconds and minutes
		long seconds = durationMillis / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;

		// Format the duration as MM.SS
		String duration = String.format("%02d.%02d", minutes, seconds);
		
		ExcelHandler.writeToController('Duration (in sec)', iterator, duration)
		// Update the metadata table with actual values
		ScreenshotHelper.updateMetadataRow("Policy No", policyNumber)
		ScreenshotHelper.updateMetadataRow("Status", testStatus)
		ScreenshotHelper.updateMetadataRow("Error if any", GlobalVariable.G_ErrorMessage.toString())
		ScreenshotHelper.saveWordDocument()
//		Mobile.closeApplication()
		}
}


