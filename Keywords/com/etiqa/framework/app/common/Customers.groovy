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

public class Customers {

	public static void participantDetails() {

		if (GlobalVariable.G_IsTestPassed == true) {

			try {
				GlobalVariable.G_IsTestPassed = true
				Mobile.tap(findTestObject('Object Repository/Ease/New Application/btn_NewApplication'), 100)
				String clientBuyingFor = ExcelHandler.getValueByKey("My client is buying this policy for")

				if (clientBuyingFor == "Spouse") {
					Mobile.tap(findTestObject('Object Repository/Ease/Customers/My Client is buying for/lst_Himself_herself'), 0)
					Mobile.tap(findTestObject('Object Repository/Ease/Customers/My Client is buying for/lst_Spouse'), 0)
				}
				else if (clientBuyingFor == "Children") {
					Mobile.tap(findTestObject('Object Repository/Ease/Customers/My Client is buying for/lst_Himself_herself'), 0)
					Mobile.tap(findTestObject('Object Repository/Ease/Customers/My Client is buying for/lst_Children'), 0)
				}

				AppCommon.mobile_search_lst(findTestObject(Locators.ParticipantDetails + 'lst_Salutation'),'Salutation')
				AppCommon.mobile_txt(findTestObject(Locators.ParticipantDetails + 'txt_Full Name'), 'Full Name')
				Mobile.hideKeyboard()
				//			Mobile.hideKeyboard()
				AppCommon.mobile_txt(findTestObject(Locators.ParticipantDetails + 'txt_New IC'), 'New IC')
				AppCommon.driver.executeScript("mobile: hideKeyboard")
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Participant Details1", "Entered details of Participant Details")
				AppCommon.Swipe()
				AppCommon.mobile_lst(findTestObject(Locators.ParticipantDetails + 'lst_Race'),'Race')
				AppCommon.mobile_btn(findTestObject(Locators.ParticipantDetails + 'btn_Muslim'),'Muslim')
				AppCommon.mobile_lst(findTestObject(Locators.ParticipantDetails + 'lst_Marital Status'),'Marital Status')
				AppCommon.mobile_btn(findTestObject(Locators.ParticipantDetails + 'btn_Smoking_No'),'Smoking')
				AppCommon.mobile_txt(findTestObject(Locators.ParticipantDetails + 'txt_Number of Children'), 'Number of Children')
				//			Thread.sleep(500)
				AppCommon.driver.executeScript("mobile: hideKeyboard")
				AppCommon.mobile_lst(findTestObject(Locators.ParticipantDetails + 'txt_Preferred Language'), 'Preferred Language')

				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Address line 1'), 'Address line 1')
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Address line 2'), 'Address line 2')
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Participant Details2", "Entered details of Participant Details continuation...")
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Postcode'), 'Postcode')
				AppCommon.mobile_search_lst(findTestObject(Locators.ContactDetails + 'lst_City'),'City')
				AppCommon.mobile_search_lst(findTestObject(Locators.ContactDetails + 'lst_State'),'State')
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Home Telephone No'), 'Home Telephone No')
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Office Telephone No'), 'Office Telephone No')
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Mobile No 1'), 'Mobile No')
				AppCommon.mobile_txt(findTestObject(Locators.ContactDetails + 'txt_Email'), 'Email')

				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Participant Details3", "Entered details of Participant Details continuation...")
				AppCommon.mobile_search_lst(findTestObject(Locators.EmployemnetStatus + 'lst_Occupation'), 'Occupation')
				AppCommon.mobile_txt(findTestObject(Locators.EmployemnetStatus + 'txt_Company Name'), 'Company Name')
				AppCommon.driver.executeScript("mobile: hideKeyboard")
				AppCommon.Swipe()
				AppCommon.mobile_txt_amount(findTestObject(Locators.EmployemnetStatus + 'txt_Monthly Income'), 'Monthly Income')

				AppCommon.mobile_search_lst(findTestObject(Locators.AutoCreditBankDetails + 'lst_Bank Name'),'Bank Name')
				AppCommon.mobile_lst(findTestObject(Locators.AutoCreditBankDetails + 'lst_Account Type'),'Account Type')
				AppCommon.Swipe()
				AppCommon.mobile_txt(findTestObject(Locators.AutoCreditBankDetails + 'txt_Account No'), 'Account No')
				ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Participant Details4", "Entered details of Participant Details continuation...")
			} catch(Exception e) {
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}

	public static void payorDetails() {
		if (GlobalVariable.G_IsTestPassed == true) {
			AppCommon.mobile_btn(findTestObject(Locators.PayorDetails + 'btn_Payor'),'Payor')
			AppCommon.mobile_lst(findTestObject(Locators.PayorDetails + 'lst_Who is Paying'),'Who is paying')
			AppCommon.mobile_lst(findTestObject(Locators.PayorDetails + 'lst_Relationship with Participant'),'Relationship with Participant')
			AppCommon.mobile_search_lst(findTestObject(Locators.PayorDetails + 'lst_Salutation'),'P_Salutation')
			AppCommon.mobile_txt(findTestObject(Locators.PayorDetails + 'txt_Full Name'), 'P_Full Name')
			AppCommon.mobile_txt(findTestObject(Locators.PayorDetails + 'txt_New IC'), 'P_New IC')
			AppCommon.driver.executeScript("mobile: hideKeyboard")
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Payor Details", "Entered details of Payor Details")
			AppCommon.mobile_lst(findTestObject(Locators.PayorDetails + 'lst_Marital Status'),'P_Marital Status')
			Thread.sleep(1000)
			AppCommon.mobile_txt(findTestObject(Locators.PayorDetails + 'txt_Number of Children'), 'P_Number of Children')
			AppCommon.mobile_txt(findTestObject(Locators.PayorContactDetails + 'txt_Mobile No 1'), 'P_Mobile No. 1')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Payor Details", "Entered details of Payor Details continuation...")
			AppCommon.mobile_txt(findTestObject(Locators.PayorContactDetails + 'txt_Email'), 'P_Email')
			AppCommon.mobile_search_lst(findTestObject(Locators.PayorEmplymentStatus + 'lst_Occupation'), 'P_Occupation')
			AppCommon.mobile_txt(findTestObject(Locators.PayorEmplymentStatus + 'txt_Company Name'), 'P_Company Name')
			AppCommon.mobile_txt_amount(findTestObject(Locators.PayorEmplymentStatus + 'txt_Monthly Income'), 'P_Monthly Income')
			AppCommon.mobile_btn(findTestObject(Locators.PayorEmplymentStatus + 'btn_Self Employed'),'P_Self Employed')
			ScreenshotHelper.captureScreenshot(GlobalVariable.G_CurrentTCID, "Payor Details", "Entered details of Payor Details continuation...")
		}
	}
}