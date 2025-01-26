package com.etiqa.framework.app.common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.By
import com.etiqa.framework.common.LogHelper
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import internal.GlobalVariable
import io.appium.java_client.AppiumBy
import io.appium.java_client.AppiumDriver
import com.etiqa.framework.common.ExcelHandler
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.etiqa.framework.app.common.Locators
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.interactions.Actions
import com.etiqa.framework.common.ScreenshotHelper
import com.etiqa.framework.app.common.AppCommon
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.MobileTestObject.MobileLocatorStrategy
import org.openqa.selenium.Keys


public class PotentialAreaOfDiscussion {

	public static void NeedsAndPriority() {
		if (GlobalVariable.G_IsTestPassed == true) {
			try {
				AppCommon.mobile_btn(findTestObject(Locators.PotentialAreaOfDiscussion + 'btn_Potential Area of Discussion'),'Potential Area of Discussion')

				// Create a new mobile object programmatically

				MobileTestObject mobileTestObject4 = new MobileTestObject("TestObjectID")
				mobileTestObject4.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
				mobileTestObject4.setMobileLocator("//*[@type = 'XCUIElementTypeStaticText' and contains(@name , 'Lump Sum Investment')]")

				MobileTestObject mobileTestObject6 = new MobileTestObject("TestObjectID")
				mobileTestObject6.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
				mobileTestObject6.setMobileLocator("//*[@type = 'XCUIElementTypeStaticText' and contains(@name , 'Protecting your family against Death')]")

				MobileTestObject mobileTestObject1 = new MobileTestObject("TestObjectID")
				mobileTestObject1.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
				mobileTestObject1.setMobileLocator("//*[@type = 'XCUIElementTypeStaticText' and contains(@name , 'Retirement Plan')]")

				Mobile.dragAndDrop(mobileTestObject4, mobileTestObject6,20)
				Mobile.dragAndDrop(mobileTestObject1, mobileTestObject6,20)

				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "PotentialArea Of Discussion", "Entered details of PotentialArea Of Discussion")
			} catch(Exception e) {
				// Handling the exception if there's an error
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}

	public static void InvestmentPreference() {
		if (GlobalVariable.G_IsTestPassed == true) {

			try {
				//Intermediary Status
				AppCommon.mobile_btn(findTestObject(Locators.PotentialAreaOfDiscussion + 'btn_Investment Preference'),'Investment Preference')
				Thread.sleep(1000)
				//			Mobile.tapAtPosition(447, 191)
				Mobile.tapAtPosition(587, 191)
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Investment Preference", "Selected details of Investment Preference")
			} catch(Exception e) {
				// Handling the exception if there's an error
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}

	public static void IntermediaryStatus() {
		if (GlobalVariable.G_IsTestPassed == true) {

			try {
				AppCommon.mobile_btn(findTestObject(Locators.PotentialAreaOfDiscussion + 'btn_Intermediary Status'),'Intermediary Status')
				AppCommon.mobile_btn(findTestObject(Locators.IntermediaryStatus + 'btn_Investment-Linked Takaful'),'Investment-Linked Takaful')
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Intermediary Status", "Selected details of Intermediary Status")
			} catch(Exception e) {
				// Handling the exception if there's an error
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}
}
