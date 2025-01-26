package com.etiqa.framework.common

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import internal.GlobalVariable
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

class ExcelHandler {

	// Testing reading data by key
	static String getValueByKey(String key) throws IOException {
		Map<String, Object> testData = getTestData(GlobalVariable.G_TestDataPath)
		return (String) testData.getOrDefault(key, "Key not found")
	}

	static Map<String, Object> getTestData(String filePath) throws IOException {
		Map<String, Object> dataMap = [:]  // Groovy-style empty map
		FileInputStream fs = new FileInputStream(filePath)
		XSSFWorkbook workbook = new XSSFWorkbook(fs)
		//		print(GlobalVariable.G_TDSheetName)
		XSSFSheet sheet = workbook.getSheet(GlobalVariable.G_TDSheetName)
		Row headerRow = sheet.getRow(3)  // Header row (index 3)

		int easeFieldsIndex = -1
		int testCaseIndex = -1


		// Find the column indexes for "Ease Fields" and "TC001"
		headerRow.each { cell ->
			//			println(cell.getStringCellValue())

			if (cell.getStringCellValue().equalsIgnoreCase(GlobalVariable.G_CurrColName)) {
				easeFieldsIndex = cell.columnIndex
			}
			if (cell.getStringCellValue().equalsIgnoreCase(GlobalVariable.G_CurrentTCID )) {
				testCaseIndex = cell.columnIndex
			}
		}

		if (easeFieldsIndex == -1 || testCaseIndex == -1) {
			throw new IllegalArgumentException("Specified columns not found in the header row.")
		}

		// Iterate through the rows starting from row 5 (index 4)
		for (int k = 4; k <= sheet.getLastRowNum(); k++) {
			Row row = sheet.getRow(k);
			if (row != null) {
				Cell keyCell = row.getCell(easeFieldsIndex);
				Cell valueCell = row.getCell(testCaseIndex);

				if (keyCell != null && valueCell != null) {
					String key = getCellValue(keyCell); // Get value as string
					String value = getCellValue(valueCell); // Get value as string
					dataMap.put(key, value); // Add to the map
				}
			}
		}

		workbook.close()  // Close the workbook after use
		return dataMap
	}

	// Helper method to get the cell value in string format
	private static String getCellValue(Cell cell) {
		String value = ""

		if (cell == null) {
			return value // Return empty string for null cell
		}

		switch (cell.getCellTypeEnum()) {
			// Using getCellTypeEnum() for POI 5.x
			case CellType.STRING:
				value = cell.getStringCellValue()
				break

			case CellType.NUMERIC:
			// Check if the number is a date
				if (DateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue().toString() // Handle date values if needed
				} else {
					double numericValue = cell.getNumericCellValue()
					if (numericValue == (long) numericValue) {
						// If it's a whole number, cast it to long and remove the decimal
						value = String.valueOf((long) numericValue)
					} else {
						// Otherwise, keep the decimal value
						value = String.valueOf(numericValue)
					}
				}
				break

			case CellType.BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue())
				break

			case CellType.BLANK:
				value = "" // Empty cell, return empty string
				break

			default:
				value = "" // Default to empty string for unknown cell types
				break
		}

		return value
	}


	static void writeToTestData(String fieldName, String data)
	throws IOException {
		String filePath = GlobalVariable.G_TestDataPath
		String sheetName = GlobalVariable.G_TDSheetName
		String easeFieldsHeader = "Ease Fields"
		String testCaseName = GlobalVariable.G_CurrentTCID
		FileInputStream fis = new FileInputStream(filePath)
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheet(sheetName)

		if (sheet == null) {
			throw new IllegalArgumentException("Sheet \"" + sheetName + "\" does not exist.")
		}

		// Locate the Ease Fields and Test Case columns
		Row headerRow = sheet.getRow(3)  // Assuming headers are in row 4 (index 3)
		if (headerRow == null) {
			throw new IllegalArgumentException("Header row is null. Please check the Excel file.")
		}

		int easeFieldsColIndex = -1
		int testCaseColIndex = -1

		// Loop through the header row to find the column indices for Ease Fields and Test Case columns
		for (Cell cell : headerRow) {
			String cellValue = cell.getStringCellValue().trim()
			if (cellValue.equalsIgnoreCase(easeFieldsHeader)) {
				easeFieldsColIndex = cell.getColumnIndex()
			}
			if (cellValue.equalsIgnoreCase(testCaseName)) {
				testCaseColIndex = cell.getColumnIndex()
			}
		}

		if (easeFieldsColIndex == -1) {
			throw new IllegalArgumentException("Ease Fields \"" + easeFieldsHeader + "\" column not found in header row.")
		}
		if (testCaseColIndex == -1) {
			throw new IllegalArgumentException("Test Case \"" + testCaseName + "\" column not found in header row.")
		}

		// Debugging: Print out the column indices for "Ease Fields" and "Test Case"
		//		println "Ease Fields column index: $easeFieldsColIndex, Test Case column index: $testCaseColIndex"


		// Enhanced Debugging and handling extra spaces
		int targetRowIndex = -1
		for (int y = 1; y <= sheet.getLastRowNum(); y++) {
			Row row = sheet.getRow(y)
			if (row != null) {
				Cell easeFieldCell = row.getCell(easeFieldsColIndex)
				println "easefield" + easeFieldCell
				if (easeFieldCell != null ) {
					// Get the raw value of the Ease Fields cell
					String rawCellValue = easeFieldCell.getStringCellValue().trim()
					String sanitizedFieldName = fieldName.trim().replaceAll("[^\\x20-\\x7E]", "")  // Remove non-printable characters
					//					println "rawcellvalue" + rawCellValue
					//					println "sanitizesfieldname" + sanitizedFieldName
					// Print the raw and sanitized values for comparison
					//					println "Comparing sanitized field: '${sanitizedFieldName}' with raw cell value: '${rawCellValue}'"
					//					println "Row index: $y, Ease Fields Column Index: $easeFieldsColIndex"
					//					println "Raw Ease Fields Value: '${rawCellValue}'"

					// Compare sanitized value of fieldName with the cell value
					if (rawCellValue.equalsIgnoreCase(sanitizedFieldName)) {
						println "Found matching row at index $y"
						targetRowIndex = y
						break
					}
				}
			}
		}

		if (targetRowIndex == -1) {
			println "Field \"$fieldName\" not found under Ease Fields."
			throw new IllegalArgumentException("Field \"" + fieldName + "\" not found under Ease Fields.")
		}



		// Write the data in the identified row and test case column
		Row targetRow = sheet.getRow(targetRowIndex)
		Cell targetCell = targetRow.getCell(testCaseColIndex)
		if (targetCell == null) {
			targetCell = targetRow.createCell(testCaseColIndex)
		}
		targetCell.setCellValue(data)

		// Save the changes
		FileOutputStream fos = new FileOutputStream(filePath)
		workbook.write(fos)
		fos.close()
		workbook.close()

		println "Data written successfully to Excel."
	}

	static void TestDataValue(String key) {
		println("Value for '$key': ${ExcelHandler.getValueByKey(GlobalVariable.G_TestDataPath, key)}")
	}



	static void GetControllerData(String filePath) {

		FileInputStream fis = null;
		Workbook workbook = null;

		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);

			// Step 1: Connect to "Controller v2.0" sheet
			Sheet controllerSheet = workbook.getSheet("Controller v2.0");
			if (controllerSheet == null) {
				throw new IllegalArgumentException("Sheet 'Controller v2.0' not found.");
			}

			// Step 2: Retrieve column indices from header row (row 4)
			Row headerRow = controllerSheet.getRow(3); // Row 4 (index 3)
			if (headerRow == null) {
				throw new IllegalArgumentException("Header row not found at row 4.");
			}
			Map<String, Integer> columnIndices = getColumnIndices(headerRow);

			// Step 3: Loop through rows starting from row 5 (index 4)
			for (int i = 4; i <= controllerSheet.getLastRowNum(); i++) {
				Row row = controllerSheet.getRow(i);
				if (row == null) continue;

				// Retrieve the 'Execute' and 'App Name' cells
				Cell executeCell = row.getCell(columnIndices.get("Execute (Y/N)"));
				Cell appNameCell = row.getCell(columnIndices.get("App Name"));

				if (executeCell != null && "Y".equalsIgnoreCase(executeCell.getStringCellValue())
						&& appNameCell != null && "Ease".equalsIgnoreCase(appNameCell.getStringCellValue())) {

					// Retrieve other cells
					Cell functionalityCell = row.getCell(columnIndices.get("Functionality"));
					Cell TCIDCell = row.getCell(columnIndices.get("TCID"));
					Cell dependentTestCaseCell = row.getCell(columnIndices.get("Dependent Test Case Reference"));

					String functionality = (functionalityCell != null) ? functionalityCell.getStringCellValue() : null;
					String dependentTestCaseReference = (dependentTestCaseCell != null) ? dependentTestCaseCell.getStringCellValue() : null;

					// Extract value from TCID cell (may be string or numeric)
					String TCID = (TCIDCell != null) ? getCellValue(TCIDCell) : null;

					System.out.println("TCID: " + TCID);
					System.out.println("Processing Functionality: " + functionality);
					System.out.println("Dependent Test Case Reference: " + dependentTestCaseReference);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Map<String, Integer> getColumnIndices(Row headerRow) {
		Map<String, Integer> columnIndices = new HashMap<>();
		for (Cell cell : headerRow) {
			columnIndices.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
		}
		return columnIndices;
	}

	/**
	 * This method retrieves the number of rows with data in the Excel sheet.
	 *
	 * @param excelFilePath the full path of the Excel file (absolute path)
	 * @param sheetName the name of the sheet to read from
	 * @return the number of rows with data in the sheet
	 */
	static int getUsedRangeRowCount() {
		try {
			// Global variables for file path and sheet name
			String excelFilePath = GlobalVariable.G_TestDataPath
			String sheetName = GlobalVariable.G_CntrSheetName
			// Create a FileInputStream to read the Excel file
			FileInputStream fis = new FileInputStream(new File(excelFilePath))

			// Create a Workbook instance (for .xlsx files)
			Workbook workbook = new XSSFWorkbook(fis)

			// Get the sheet by its name
			Sheet sheet = workbook.getSheet(sheetName)
			if (sheet == null) {
				KeywordUtil.markFailed("Sheet not found: " + sheetName)
				return 0
			}

			// Get the last row number (this gives the index of the last row with data)
			int rowCount = sheet.getPhysicalNumberOfRows()

			// Close the workbook and FileInputStream
			workbook.close()
			fis.close()

			return rowCount
		} catch (Exception e) {
			KeywordUtil.markFailed("Error while reading Excel file: " + e.message)
			return 0
		}
	}

	/**
	 * This method retrieves data based on the field name (header) from an Excel sheet for a specific row.
	 *
	 * @param fieldName the field (header) name in the sheet
	 * @param excelFilePath the full path of the Excel file (absolute path)
	 * @param sheetName the name of the sheet to read from
	 * @param rowIndex the row index to get data for
	 * @return the value in the corresponding row under the given header, or null if not found
	 */
	static String getDataByHeaderAndRow(String fieldName, int rowIndex) {

		try {
			// Global variables for file path and sheet name
			String excelFilePath = GlobalVariable.G_TestDataPath
			String sheetName = GlobalVariable.G_CntrSheetName
			// Create a FileInputStream to read the Excel file
			FileInputStream fis = new FileInputStream(new File(excelFilePath))

			// Create a Workbook instance (for .xlsx files)
			Workbook workbook = new XSSFWorkbook(fis)

			// Get the sheet by its name
			Sheet sheet = workbook.getSheet(sheetName)
			if (sheet == null) {
				KeywordUtil.markFailed("Sheet not found: " + sheetName)
				return null
			}

			// Get the header row (assuming the first row is the header)
			Row headerRow = sheet.getRow(3)
			if (headerRow == null || headerRow.getPhysicalNumberOfCells() == 0) {
				KeywordUtil.markFailed("Header row is empty.")
				return null
			}

			// Find the column index based on the field name
			int columnIndex = -1
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().trim().equalsIgnoreCase(fieldName.trim())) {
					columnIndex = cell.getColumnIndex()
					break
				}
			}

			if (columnIndex == -1) {
				KeywordUtil.logInfo("Field not found in header: " + fieldName)
				return null  // Return null instead of failing the test
			}

			// Get the row for the requested index (adjusted for 0-based indexing in Java)
			Row dataRow = sheet.getRow(rowIndex)
			if (dataRow == null) {
				KeywordUtil.logInfo("Row not found at index: " + rowIndex)
				return null  // Return null if the row is not found
			}

			// Get the value from the cell in the specified row and column
			Cell cell = dataRow.getCell(columnIndex)
			if (cell == null) {
				KeywordUtil.logInfo("Cell is empty at row " + rowIndex + ", column " + columnIndex)
				return null  // Return null if the cell is empty
			}

			// Return the cell value as a string
			String value = cell.toString()

			// Close the workbook and FileInputStream
			workbook.close()
			fis.close()

			return value
		} catch (Exception e) {
			KeywordUtil.markFailed("Error while reading Excel file: " + e.message)
			return null
		}
	}
	
	static boolean writeToController(String fieldName, int rowIndex, String newValue) {
		try {
			// Global variables for file path and sheet name
			String excelFilePath = GlobalVariable.G_TestDataPath;
			String sheetName = GlobalVariable.G_CntrSheetName;
			
			// Create a FileInputStream to read the Excel file
			FileInputStream fis = new FileInputStream(new File(excelFilePath));
	
			// Create a Workbook instance (for .xlsx files)
			Workbook workbook = new XSSFWorkbook(fis);
	
			// Get the sheet by its name
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				KeywordUtil.markFailed("Sheet not found: " + sheetName);
				return false;
			}
	
			// Get the header row (assuming the first row is the header)
			Row headerRow = sheet.getRow(3);
			if (headerRow == null || headerRow.getPhysicalNumberOfCells() == 0) {
				KeywordUtil.markFailed("Header row is empty.");
				return false;
			}
	
			// Find the column index based on the field name
			int columnIndex = -1;
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().trim().equalsIgnoreCase(fieldName.trim())) {
					columnIndex = cell.getColumnIndex();
					break;
				}
			}
	
			if (columnIndex == -1) {
				KeywordUtil.logInfo("Field not found in header: " + fieldName);
				return false; // Return false if field not found
			}
	
			// Get the row for the requested index (adjusted for 0-based indexing in Java)
			Row dataRow = sheet.getRow(rowIndex);
			if (dataRow == null) {
				KeywordUtil.logInfo("Row not found at index: " + rowIndex);
				return false; // Return false if row is not found
			}
	
			// Get the cell in the specified row and column and set its value
			Cell cell = dataRow.createCell(columnIndex, CellType.STRING);
			cell.setCellValue(newValue);
	
			// Write the updated workbook to a new file (or overwrite the existing file)
			FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
			workbook.write(fos);
	
			// Close resources
			fos.close();
			workbook.close();
			fis.close();
	
			// Return true if the write operation is successful
			return true;
		} catch (Exception e) {
			KeywordUtil.markFailed("Error while writing to Excel file: " + e.getMessage());
			return false;
		}
	}
	

	//	public static void main(String[] args) {
	//		GetControllerData();
	//	}
}
