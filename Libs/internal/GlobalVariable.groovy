package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object G_Timeout
     
    /**
     * <p></p>
     */
    public static Object G_AppPath
     
    /**
     * <p></p>
     */
    public static Object G_TestDataPath
     
    /**
     * <p></p>
     */
    public static Object G_CurrentTCID
     
    /**
     * <p></p>
     */
    public static Object G_CurrColName
     
    /**
     * <p></p>
     */
    public static Object G_TDSheetName
     
    /**
     * <p></p>
     */
    public static Object G_CntrSheetName
     
    /**
     * <p></p>
     */
    public static Object G_Functionality
     
    /**
     * <p></p>
     */
    public static Object G_Platform
     
    /**
     * <p></p>
     */
    public static Object G_Execute_Y_N
     
    /**
     * <p></p>
     */
    public static Object G_TestCaseName
     
    /**
     * <p></p>
     */
    public static Object G_IsTestPassed
     
    /**
     * <p></p>
     */
    public static Object G_Environment
     
    /**
     * <p></p>
     */
    public static Object G_ErrorMessage
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters(), selectedVariables)
    
            G_Timeout = selectedVariables['G_Timeout']
            G_AppPath = selectedVariables['G_AppPath']
            G_TestDataPath = selectedVariables['G_TestDataPath']
            G_CurrentTCID = selectedVariables['G_CurrentTCID']
            G_CurrColName = selectedVariables['G_CurrColName']
            G_TDSheetName = selectedVariables['G_TDSheetName']
            G_CntrSheetName = selectedVariables['G_CntrSheetName']
            G_Functionality = selectedVariables['G_Functionality']
            G_Platform = selectedVariables['G_Platform']
            G_Execute_Y_N = selectedVariables['G_Execute_Y_N']
            G_TestCaseName = selectedVariables['G_TestCaseName']
            G_IsTestPassed = selectedVariables['G_IsTestPassed']
            G_Environment = selectedVariables['G_Environment']
            G_ErrorMessage = selectedVariables['G_ErrorMessage']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
