package com.etiqa.framework.app.common

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

public class Executor {

	// A method that encapsulates the logic
	public static void executePolicyDetails() {
		if (GlobalVariable.G_IsTestPassed == true) {
			try {
				AppCommon.mobile_btn(findTestObject(Locators.Executor + 'btn_Executor'),'Executor')
				AppCommon.mobile_btn(findTestObject(Locators.Executor + 'btn_SelectExecutor'),'Select Executor')
				AppCommon.mobile_btn(findTestObject(Locators.Executor + 'btn_AddExecutor'),'Add Executor')
//				AppCommon.mobile_txt(findTestObject(Locators.Executor + 'btn_Executor'),'Executor')
				
				AppCommon.mobile_txt(findTestObject(Locators.Executor + 'txt_Full Name'), 'E_Full Name')
				AppCommon.mobile_txt(findTestObject(Locators.Executor + 'txt_New IC'), 'E_New IC')
				AppCommon.driver.executeScript("mobile: hideKeyboard")
				AppCommon.mobile_search_lst(findTestObject(Locators.Executor + 'lst_Salutation'),'E_Salutation')
				AppCommon.mobile_lst(findTestObject(Locators.Executor + 'lst_Relationship with Participant'),'E_Relationship')
				
				
			} catch(Exception e) {
				// Handling the exception if there's an error
				println "Error in entering Policy Details"
				GlobalVariable.G_IsTestPassed = false
			}
		}
	}
}

