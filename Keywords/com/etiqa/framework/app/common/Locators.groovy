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

public class Locators {

	static final String AutoCreditBankDetails = 'Object Repository/Ease/Customers/Participant_Person Covered/Auto Credit Bank Details/'
	static final String ContactDetails = 'Object Repository/Ease/Customers/Participant_Person Covered/Contact Details/'
	static final String EmployemnetStatus = 'Object Repository/Ease/Customers/Participant_Person Covered/Employment Status/'
	static final String ParticipantDetails = 'Object Repository/Ease/Customers/Participant_Person Covered/Participants Details/'
	static final String PayorContactDetails = 'Object Repository/Ease/Customers/Payor/Contact Details/'
	static final String PayorEmplymentStatus = 'Object Repository/Ease/Customers/Payor/Employment Status/'
	static final String PayorDetails = 'Object Repository/Ease/Customers/Payor/Payor Details/'
	static final String PotentialAreaOfDiscussion = 'Object Repository/Ease/Potential Area of Discussion/'
	static final String FinancialNeedsAnalysis = 'Object Repository/Ease/Financial Needs Analysis/'
	static final String IntermediaryStatus = 'Object Repository/Ease/Potential Area of Discussion/Intermediary Status/'
	static final String RecommededProducts = 'Object Repository/Ease/Recommended Products/'
	static final String ChooseProduct = 'Object Repository/Ease/Recommended Products/Choose Product/'
	static final String Executor = 'Object Repository/Ease/Executor And Beneficiary/'
	
}
