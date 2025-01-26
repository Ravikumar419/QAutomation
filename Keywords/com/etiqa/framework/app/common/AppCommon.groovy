package com.etiqa.framework.app.common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Dimension
import org.openqa.selenium.By

import com.etiqa.framework.common.LogHelper
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import internal.GlobalVariable
import io.appium.java_client.AppiumBy
import io.appium.java_client.AppiumDriver
import com.etiqa.framework.common.ExcelHandler
import com.etiqa.framework.app.common.Locators
import org.openqa.selenium.WebElement;





import io.appium.java_client.ios.IOSDriver
import java.text.DecimalFormat


public class AppCommon {
	public static AppiumDriver driver = MobileDriverFactory.getDriver()
	public static boolean failureLogged = false
	public static String testCaseName = GlobalVariable.G_TestCaseName

	public static void getData(String fieldName, String obj, String action) {
		// Get the data from Excel
		String tData = ExcelHandler.getValueByKey(fieldName, obj);

		// Check if the data is not empty or blank and action is TAP
		if (!(tData.trim().isEmpty()) && action.toUpperCase().equals("TAP")) {
			Mobile.tap(findTestObject(obj), 0);
		}

		// Check if the data is not empty or blank and action is INPUT
		if (!(tData.trim().isEmpty()) && action.toUpperCase().equals("INPUT")) {
			Mobile.setText(findTestObject(obj), tData, 0);
		}
	}

	public static void loginWithDefaultPassCode() {

		try {
			driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name = 'Please enter your passcode']//following::XCUIElementTypeTextField)[1]")).click()
			for (int i = 1; i <= 6; i++) {
				println(i)
				driver.findElement(AppiumBy.className("XCUIElementTypeSecureTextField")).sendKeys(i.toString())
			}
			println("Completed Executing method")
			// Log the step of clicking the passcode field
			LogHelper.logKeywordStatus(testCaseName, "loginWithDefaultPassCode - Entered passcode field", LogHelper.STATUS_PASSED)
			GlobalVariable.G_IsTestPassed = true
		} catch (Exception e) {
			println "Error in entering passcode"
			// Log any error that occurs during the process
			LogHelper.logKeywordStatus(testCaseName, "loginWithDefaultPassCode - Error in entering passcode", LogHelper.STATUS_FAILED)
			GlobalVariable.G_IsTestPassed = false
		}
	}


	public static void Swipe() {
		if (GlobalVariable.G_IsTestPassed == true) {
			IOSDriver driver = MobileDriverFactory.getDriver()

			def size = driver.manage().window().getSize(); //Get the size of screen.
			System.out.println(size);
			int starty = (int) (size.height * 0.75)
			int endy = (int) (size.height * 0.18)
			int startx = size.width / 2
			Mobile.swipe(startx, starty, startx, endy)
			Mobile.delay(1)
		}
	}

	public static void Swipelittle() {
		if (GlobalVariable.G_IsTestPassed == true) {
			IOSDriver driver = MobileDriverFactory.getDriver();

			// Get the size of the screen
			Dimension size = driver.manage().window().getSize();
			System.out.println(size);

			int starty = (int) (size.height * 0.75); // Start at 75% of the screen height
			int endy = (int) (size.height * 0.20); // End at 20% of the screen height
			int startx = size.width / 2; // Swipe horizontally from the middle of the screen

			// Calculate the vertical swipe distance and divide by 3 (for 3 steps)
			int swipeDistance = starty - endy;
			int step = swipeDistance / 3;

			// Perform 3 swipe steps
			for (int i = 0; i < 3; i++) {
				int currentEndy = starty - (i + 1) * step;
				Mobile.swipe(startx, starty, startx, currentEndy);
				Mobile.delay(1); // Delay to make it look like a smooth swipe
			}
		}
	}


	public static void mobile_txt(object, value) {
		// Flag to ensure failure is logged only once
		//		boolean failureLogged = false

		// Proceed only if the test has passed so far
		if (GlobalVariable.G_IsTestPassed == true) {
			String testval = ""  // Declare testval here to ensure it's accessible throughout the method

			try {
				// Fetch the value from the Excel sheet
				testval = ExcelHandler.getValueByKey(value)
				Thread.sleep(1000)
				// Check if the value is not empty
				if (testval != "") {
					// Set the text on the mobile object
					Mobile.setText(object, testval, 10)

					// Log the success and mark the test as passed
					LogHelper.logKeywordStatus(testCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
					GlobalVariable.G_IsTestPassed = true
				}
			} catch (Exception e) {
				// Log failure only once if not already logged
				if (!failureLogged) {
					println("Error in entering text for field " + value)

					// Set the test as failed
					GlobalVariable.G_IsTestPassed = false

					// Set the error message for logging
					GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to enter is " + testval

					// Log the failure and update the error message
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
					failureLogged = true  // Set failureLogged flag to true to avoid redundant logs
				}
			}
		}
	}



	public static void mobile_txt_amount(object, value) {
		// Flag to ensure failure is logged only once
		boolean failureLogged = false

		// Proceed only if the test has passed so far
		if (GlobalVariable.G_IsTestPassed == true) {
			String testval = ""  // Declare testval here to ensure it's accessible throughout the method

			try {
				// Fetch the value from the Excel sheet
				testval = ExcelHandler.getValueByKey(value)

				// Format the amount (ensure it is in the correct format like "0.00")
				if (testval != "") {
					//					testval = formatAmount(testval)

					// Set the text on the mobile object
					//					Mobile.setText(object, testval+"00", 10)
					Mobile.tap(object, 10)
					//					Thread.sleep(2000)
					//					Mobile.sendKeys(object, testval+"00")
					Mobile.setText(object, testval+"00", 10)

					// Log the success and mark the test as passed
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
					GlobalVariable.G_IsTestPassed = true
				}
			} catch (Exception e) {
				// Log failure only once if not already logged
				if (!failureLogged) {
					println("Error in entering text for field " + value)

					// Set the test as failed
					GlobalVariable.G_IsTestPassed = false

					// Set the error message for logging
					GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to enter is " + testval

					// Log the failure and update the error message
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
					failureLogged = true  // Set failureLogged flag to true to avoid redundant logs
				}
			}
		}
	}

	def String formatAmount(String amount) {
		// Parse the amount as a Double and format it to 2 decimal places
		double num = Double.parseDouble(amount)
		DecimalFormat df = new DecimalFormat("0.00")  // Format to 2 decimal places
		return df.format(num)
	}


	public static void mobile_lst(Object object, String value) {
		// Flag to ensure failure is logged only once
		//		boolean failureLogged = false

		// Proceed only if the test has passed so far
		if (GlobalVariable.G_IsTestPassed == true) {
			// Simulate tap action
			Mobile.tap(object, 10)

			// Get the test value from Excel
			String testval = ExcelHandler.getValueByKey(value)

			try {
				// Attempt to find the element using the test value
				WebElement testValElement = driver.findElement(AppiumBy.xpath("//*[@name='" + testval + "']"))

				// Attempt the normal click
				testValElement.click()

				// Log success and mark the test as passed
				LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
				GlobalVariable.G_IsTestPassed = true
			} catch (Exception e) {
				// Set the error message
				GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to select is " + testval

				// Log failure only once
				if (!failureLogged) {
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
					failureLogged = true // Prevent further failure logs for the same failure
				}

				// Mark the test as failed
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}

	public static void mobile_search_lst(Object object, String value) {
		// Flag to ensure failure is logged only once
		//		boolean failureLogged = false

		// Proceed only if the test has passed so far
		if (GlobalVariable.G_IsTestPassed == true) {
			// Simulate tap action
			Mobile.tap(object, 10)

			// Get the test value from Excel
			String testval = ExcelHandler.getValueByKey(value)

			// Check if the value is related to occupation
			if (value.contains("Occupation") || value.contains("P_Occupation")) {
				WebElement lsearchField = driver.findElement(AppiumBy.xpath("//*[@name='Search for occupation, industry or occupation code']"))
				lsearchField.sendKeys(testval)

				try {
					// Sleep to allow search field to update
					Thread.sleep(1000)

					// Click the occupation result
					driver.findElement(AppiumBy.xpath("//*[@name='Occupation']/following::*[6]")).click()

					// Log success and mark the test as passed
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
					GlobalVariable.G_IsTestPassed = true
				} catch (InterruptedException e) {
					// Log failure only once
					if (!failureLogged) {
						e.printStackTrace()
						println("Search field not found. Skipping.")
						GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to select is " + testval
						GlobalVariable.G_IsTestPassed = false
						LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
						failureLogged = true  // Prevent further failure logs for the same failure
					}
				}
			} else {
				// Directly find the "Search here" element
				WebElement searchField = null

				try {
					searchField = driver.findElement(AppiumBy.xpath("//*[@name='Search here']"))
					Thread.sleep(1000)  // Allow the search field to update
					searchField.sendKeys(testval)

					// Find and click the test value element
					WebElement testValElement = driver.findElement(AppiumBy.xpath("//*[@name='" + testval + "']"))
					testValElement.click()

					// Log success and mark the test as passed
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
					GlobalVariable.G_IsTestPassed = true
				} catch (Exception e) {
					// Log failure only once
					if (!failureLogged) {
						println("Search field not found. Skipping.")
						GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to select is " + testval
						GlobalVariable.G_IsTestPassed = false
						LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
						failureLogged = true  // Prevent further failure logs for the same failure
					}
				}
			}
		}
	}

	public static void mobile_btn(object, value) {
		// Flag to ensure failure is logged only once
		//		boolean failureLogged = false

		// Proceed only if the test has passed so far
		if (GlobalVariable.G_IsTestPassed == true) {
			String testval = ""  // Declare testval to hold value from Excel
			try {
				// Get value from Excel based on the provided key
				testval = ExcelHandler.getValueByKey(value)

				// Proceed only if testval is not empty
				if (testval != "") {
					// Tap on the button (or object)
					Mobile.tap(object, 10)
					// Log success and mark the test as passed
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, "Current Field Name: " + value, LogHelper.STATUS_PASSED)
					GlobalVariable.G_IsTestPassed = true
				}
			} catch (Exception e) {
				// Log failure only once if not already logged
				if (!failureLogged) {
					println("Error in tapping the button: " + value)
					GlobalVariable.G_IsTestPassed = false
					GlobalVariable.G_ErrorMessage = "Error in field: " + value + " Value trying to use is " + testval

					// Log the failure and update the error message
					LogHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
					failureLogged = true  // Set failureLogged flag to true to avoid redundant logs
				}
			}
		}
	}
}
