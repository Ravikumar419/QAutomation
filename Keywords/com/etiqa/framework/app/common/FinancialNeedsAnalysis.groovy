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


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class FinancialNeedsAnalysis {
	//	LogHelper logHelper = new LogHelper()
	//	AppCommon app = new AppCommon()
	public static void ExistingCoverage() {
		if (GlobalVariable.G_IsTestPassed == true) {
			AppCommon.mobile_btn(findTestObject(Locators.FinancialNeedsAnalysis + 'btn_Financial Needs Analysis'),'Financial Needs Analysis')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Financial Needs Analysis", "Details of Existing Coverage")
			AppCommon.Swipe()
			AppCommon.mobile_txt(findTestObject(Locators.FinancialNeedsAnalysis + 'txt_Is there a reason why'), 'Is there a reason why')
			AppCommon.driver.executeScript("mobile: hideKeyboard")
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "FinancialNeedsAnalysisOption3", "Entered details of Option3")
		}
	}
}
