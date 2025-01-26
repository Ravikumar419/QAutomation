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

public class RecommendedProducts {
	//	AppCommon app = new AppCommon()
	public static void PurposeTransaction() {
		if (GlobalVariable.G_IsTestPassed == true) {
		AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_Recommended Products'),'Recommended Products')
		AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Investment'),'Investment')
		ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products", "Selected details of Purpose of Transaction")
		AppCommon.Swipe()

		ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "RecommendedProducts", "Recommended Products screen")
		AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Recommend product'),'Recommend product')
		}
	}

	public static void ChooseProduct() {
		
		if (GlobalVariable.G_IsTestPassed == true) {
			try {
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Investment Link'),'Investment Link')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_ElitePlus Takafulink'), 'Please select a basic plan')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan", "selected a basic plan screen")
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan Notification", "Notification Customer not Eligible")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Close'), 'close')
			//		app.Swipe()
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Basic Plan Expiry Age Option'), 'Basic Plan Expiry Age')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan Expiry Age Option'", "Basic Plan Expiry Age'")
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Sum Covered Amount'), 'Sum Covered Amount')
			AppCommon.driver.executeScript("mobile: hideKeyboard")
			//		Thread.sleep(2000)
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Contribution Amount'), 'Contribution Amount')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Salary Deduction'), 'Salary Deduction')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Amount Details", "Amount details")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Supplementary Riders'), 'Supplementary Riders')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan Notification", "Add Rider")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Add rider'), 'Add Rider')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Takafulink Accelerated Critical illness'), 'Takafulink Accelerated Critical illness')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Takafulink Accidental Benefit'), 'Takafulink Accidental Benefit')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Takafulink Accidental Indemnity Benefit'), 'Takafulink Accidental Ibdemnity Benefit')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Takafulink Hospital Cash Benefit'), 'Takafulink Hospital Cash Benefit')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan Notification", "Add Selected Rider")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Add selected rider'), 'Add Selected Rider')
			Thread.sleep(3000)
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Takafulink Accelerated Critical Illness'), 'Takafulink Accelerated Critical illness')
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Takafulink Accidental Benefit'), 'Takafulink Accidental Benefit')
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Takafulink Accidental Indemnity Benefit'), 'Takafulink Accidental Ibdemnity Benefit')
			Thread.sleep(1000)
			AppCommon.mobile_lst(findTestObject(Locators.ChooseProduct + 'lst_Takafulink Hospital Cash Benefit'), 'Takafulink Hospital Cash Benefit')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Basic Plan Notification", "Details")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Regular Top-Up'), 'Regular Top Up')
			//		Thread.sleep(1000)
			//		app.mobile_btn(findTestObject(Locators.ChooseProduct + 'txt_Contribution Monthly'), 'Regular Top-Up Contribution Monthly')
			AppCommon.mobile_txt_amount(findTestObject(Locators.ChooseProduct + 'txt_Contribution Monthly'), 'Regular Top-Up Contribution Monthly')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Regular top up", "Regular topup contribution Monthly")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Funds'), 'Funds')
			Thread.sleep(1000)
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Dana Pendapatan'), 'Dana Pendapatan Prima Takaful')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Dana Syariah'), 'Dana Syariah Sukuk Global')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Dana Syariah Seimbang'), 'Dana Syariah Seimbang')
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Dana Syariah Indeks'), 'Dana Syariah Indeks Ekuiti Global')
			AppCommon.mobile_txt(findTestObject(Locators.ChooseProduct + 'txt_Fund1 Contribution'), 'Dana Pendapatan Prima Takaful')
			AppCommon.mobile_txt(findTestObject(Locators.ChooseProduct + 'txt_Fund2 Contribution'), 'Dana Syariah Sukuk Global')
			AppCommon.mobile_txt(findTestObject(Locators.ChooseProduct + 'txt_Fund3 Contribution'), 'Dana Syariah Seimbang')
			AppCommon.mobile_txt(findTestObject(Locators.ChooseProduct + 'txt_Fund4 Contribution'), 'Dana Syariah Indeks Ekuiti Global')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Funds", "Investment Allocation")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Calculate Contribution'), 'Calculate Contribution')
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Continue", "Continue Screen")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Continue'), 'Continue')
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Summary", "Summary & Confirmation screen")
			AppCommon.Swipe()
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Summary2", "Summary & Confirmation screen")
			AppCommon.Swipe()
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Summary3", "Summary & Confirmation screen")
			AppCommon.mobile_btn(findTestObject(Locators.ChooseProduct + 'btn_Recommend'), 'Recommend')
			Thread.sleep(15000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "SI", "SI screen")
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_View Full SI'),'SIview')
			Thread.sleep(4000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "SI", "SI screen")
			for (int screen = 0; screen < 13; screen++) {
				AppCommon.Swipe()
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "SI"+screen, "SI screen")
			}
			
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_PDS'), "PDS Screen")
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "PDS", "PDS screen")
			for (int screen = 0; screen < 17; screen++) {
				AppCommon.Swipe()
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "PDS"+screen, "PDS screen")
			}
			
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_FFS'), "FFS Screen")
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "FFS", "FFS screen")
			for (int screen = 0; screen < 14; screen++) {
				AppCommon.Swipe()
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "FFS"+screen, "FFS screen")
			}
			
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_IL GUIDE'), "IL GUIDE Screen")
			Thread.sleep(2000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "IL GUIDE", "IL screen")
			for (int screen = 0; screen < 3; screen++) {
				AppCommon.Swipe()
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "IL"+screen, "IL screen")
			}
			
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_Share'), 'Share')
			Thread.sleep(2000)
			AppCommon.mobile_txt(findTestObject(Locators.RecommededProducts + 'txt_Email'), 'ShareEmail')
			AppCommon.driver.executeScript("mobile: hideKeyboard");
			Thread.sleep(1000)
			AppCommon.driver.findElement(AppiumBy.xpath("//XCUIElementTypeCell/XCUIElementTypeOther[2]")).click();
			AppCommon.driver.executeScript("mobile: hideKeyboard");
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Share", "Share screen")
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_SendEmail'), 'Send Email')
			AppCommon.mobile_btn(findTestObject(Locators.RecommededProducts + 'btn_BackFromDoc'), 'Back')
			Thread.sleep(1000)
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products1", "Recommended Product details Screen")
			AppCommon.Swipe()
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products2", "Recommended Product details Screen")
			AppCommon.mobile_lst(findTestObject(Locators.RecommededProducts + 'lst_FNA Recommendation'), "FNA Recommendation")
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products3", "Recommended Product details Screen")
			AppCommon.Swipe()
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products4", "Recommended Product details Screen")
			AppCommon.mobile_lst(findTestObject(Locators.RecommededProducts + 'lst_Risk Recommendation'), "Risk Recommendation")
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products5", "Recommended Product details Screen")
			AppCommon.Swipe()
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Recommended Products6", "Recommended Product details Screen")
			
			} catch(Exception e) {
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}
}
