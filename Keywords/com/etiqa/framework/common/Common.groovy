package com.etiqa.framework.common

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable as GlobalVariable

public class LogHelper {

	private static KeywordLogger logger = KeywordLogger.getInstance(LogHelper.class)

	// Define the log file path (relative to the project folder)
	private static final String LOG_FILE_PATH = "Reports/Logs/log.txt"

	// Log status constants
	static final String STATUS_PASSED = "PASSED"
	static final String STATUS_FAILED = "FAILED"
	static final String STATUS_SKIPPED = "SKIPPED"

	// Method to log the status of a keyword
//	@Keyword
	public static void logKeywordStatus(String testCaseName, String currentKeyword, String status) {
		// Get the current date and time in DD-MM-YY HH:mm:ss format
		def dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss")
		def dateStr = dateFormat.format(new Date())

		// Build the log entry
		def logEntry = "$dateStr - $testCaseName - $currentKeyword - $status"

		// Log to the console (Katalon Studio)
		logToConsole(logEntry, status)

		// Write to log file
		writeLogToFile(logEntry)
	}

	//	@Keyword
	//	def logKeywordStatus(String testCaseName, String currentKeyword, String status, String errorMessage = "", String screenshotPath = "") {
	//		// Get the current date and time in DD-MM-YY HH:mm:ss format
	//		def dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss")
	//		def dateStr = dateFormat.format(new Date())
	//
	//		// Build the log entry
	//		def logEntry = "$dateStr - $testCaseName - $currentKeyword - $status"
	//
	//		// Append the error message if there is one
	//		if (errorMessage) {
	//			logEntry += " - Error: $errorMessage"
	//		}
	//
	//		// Append the screenshot path if provided
	//		if (screenshotPath) {
	//			logEntry += " - Screenshot: $screenshotPath"
	//		}
	//
	//		// Log to the console (Katalon Studio)
	//		logToConsole(logEntry, status)
	//
	//		// Write to log file
	//		writeLogToFile(logEntry)
	//
	//		// If the test failed, capture a screenshot (if not already captured)
	//		if (status == STATUS_FAILED && !screenshotPath) {
	//			String defaultScreenshotPath = 	ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Error", "Error in Below screen")
	//			logEntry += " - Screenshot: $defaultScreenshotPath"
	//			writeLogToFile(logEntry) // Re-log with screenshot path
	//		}
	//	}


	// Log to console based on status
	private static void logToConsole(String logEntry, String status) {
		switch (status) {
			case STATUS_FAILED:
				logger.logError(logEntry)  // Log as an error if failed
				logger.logInfo(logEntry)
				break
			case STATUS_PASSED:
				logger.logInfo(logEntry)   // Log as info if passed
				break
			case STATUS_SKIPPED:
				logger.logWarning(logEntry)  // Log as warning if skipped
				break
			default:
				logger.logInfo(logEntry)  // Default log level
		}
	}

	// Method to write logs to a file
	private static void writeLogToFile(String logEntry) {
		try {
			// Create a file object for the log file
			File logFile = new File(LOG_FILE_PATH)

			// Check if the directory exists, and if not, create it
			if (!logFile.getParentFile().exists()) {
				logFile.getParentFile().mkdirs()  // Create the parent directories (Reports/Logs)
			}

			// If the file doesn't exist, create it
			if (!logFile.exists()) {
				logFile.createNewFile()
			}

			// Open the file in append mode and write the log entry
			FileWriter writer = new FileWriter(logFile, true)  // Open in append mode
			writer.write(logEntry + "\n")  // Write the log entry with a newline
			writer.close()
		} catch (IOException e) {
			e.printStackTrace()  // Print stack trace in case of an exception
		}
	}
}
