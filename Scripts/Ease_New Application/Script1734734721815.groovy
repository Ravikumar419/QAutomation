import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable

// G_AppPath stores the bundle ID
String appBundleId = GlobalVariable.G_AppPath


// Start the existing application by its bundle ID [Maintained in profile]
Mobile.startExistingApplication(appBundleId)

// Deafult login passcode will be entered using below method
CustomKeywords.'com.etiqa.framework.app.common.AppCommon.loginWithDefaultPassCode'()
Mobile.waitForElementPresent(findTestObject, 0)