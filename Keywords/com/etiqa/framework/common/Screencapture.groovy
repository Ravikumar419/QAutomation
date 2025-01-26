package com.etiqa.framework.common

import java.text.SimpleDateFormat
import org.apache.poi.xwpf.usermodel.*
import org.apache.poi.util.Units
import java.io.File
import java.io.FileOutputStream
import java.io.ByteArrayInputStream
import java.util.Date
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable
import com.etiqa.framework.common.LogHelper

class ScreenshotHelper {

	static String basePath = "Reports"
	static Map<String, String> createdFolders = [:]  // Store last created folder path per TCID
	static XWPFDocument doc = null  // Document object for the whole TCID
	static FileOutputStream outStream = null  // Output stream to write the document
	static String metadataFilePath = null  // Path for metadata table
	public static boolean pageBreak = true;

	// Method to capture screenshot and generate the Word document for the TCID
	static String captureScreenshot(String TCID, String screenshotName, String description) {
		// Check if the TCID has already been processed
		String iterationFolder
		String screenshotFolder
		Mobile.hideKeyboard()
		if (GlobalVariable.G_IsTestPassed == true) {
			if (createdFolders.containsKey(TCID)) {
				iterationFolder = createdFolders[TCID]
				screenshotFolder = "${iterationFolder}/Screenshots"
			} else {
				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
				iterationFolder = "${basePath}/${TCID}_${timestamp}"
				screenshotFolder = "${iterationFolder}/Screenshots"

				try {
					// Create necessary folders
					createFolder(basePath)
					createFolder(iterationFolder)
					createFolder(screenshotFolder)

					// Initialize the document
					doc = new XWPFDocument()
					metadataFilePath = "${iterationFolder}/Test_Report_${TCID}.docx"
					outStream = new FileOutputStream(metadataFilePath)

					// Create metadata table in the document
					createMetadataTable()

					createdFolders[TCID] = iterationFolder
				} catch (Exception e) {
					println "Error occurred during folder creation: ${e.message}"
					throw new Exception("Failed to create necessary folders. ${e.message}")
				}
			}

			// Capture the screenshot using Katalon and save it
			String screenshotFilePath = "${screenshotFolder}/${screenshotName}.png"
			Mobile.takeScreenshot(screenshotFilePath)
			println "Screenshot saved at: ${screenshotFilePath}"

			// Add description and screenshot to the document
			addDescriptionAndScreenshotToDocument(screenshotFilePath, description)
			return screenshotFilePath
		}
		else {
			//capture screen when failure and display error message
			println("Search field not found. Skipping.");
			LogHelper logHelper = new LogHelper()
			logHelper.logKeywordStatus(GlobalVariable.G_TestCaseName, GlobalVariable.G_ErrorMessage, LogHelper.STATUS_FAILED)
		}
	}

	static String captureErrorScreenshot(String TCID, String screenshotName, String errorDescription) {
		// Folder paths are assumed to already be created
		String screenshotFolder = "${basePath}/${TCID}/Screenshots"
	
		// Capture the screenshot using Katalon and save it
		String screenshotFilePath = "${screenshotFolder}/${screenshotName}.png"
		Mobile.takeScreenshot(screenshotFilePath)
		println "Screenshot saved at: ${screenshotFilePath}"
	
		// Add a page break before the error
		XWPFParagraph pageBreakPara = doc.createParagraph()
		pageBreakPara.createRun().addBreak(BreakType.PAGE)
	
		// Add decorative line at the top
		XWPFParagraph lineParaTop = doc.createParagraph()
		XWPFRun lineRunTop = lineParaTop.createRun()
		lineRunTop.setText("**************************************************")
		lineRunTop.setColor("FF0000")  // Red color in hex
		lineRunTop.setBold(true)
	
		// Add the error description
		XWPFParagraph errorPara = doc.createParagraph()
		XWPFRun errorRun = errorPara.createRun()
		errorRun.setText(errorDescription)
		errorRun.setColor("FF0000")  // Red color in hex
		errorRun.setBold(true)
	
		// Add decorative line at the bottom
		XWPFParagraph lineParaBottom = doc.createParagraph()
		XWPFRun lineRunBottom = lineParaBottom.createRun()
		lineRunBottom.setText("**************************************************")
		lineRunBottom.setColor("FF0000")  // Red color in hex
		lineRunBottom.setBold(true)
	
		// Insert the screenshot into the document
		File screenshotFile = new File(screenshotFilePath)
		if (screenshotFile.exists()) {
			byte[] screenshotBytes = screenshotFile.bytes
			println "Screenshot byte length: ${screenshotBytes.length} bytes"
	
			if (screenshotBytes.length == 0) {
				println "Error: Screenshot byte array is empty."
				return screenshotFilePath
			}
	
			// Add picture data to the document (PNG type)
			String pictureId = doc.addPictureData(screenshotBytes, XWPFDocument.PICTURE_TYPE_PNG)
			println "Picture data added to document with ID: ${pictureId}"
	
			// Insert the picture into the document using the picture ID
			XWPFParagraph pictureParagraph = doc.createParagraph()
			XWPFRun run = pictureParagraph.createRun()
	
			// Resize the picture
			int pictureWidth = Units.toEMU(400)  // Width in EMUs
			int pictureHeight = Units.toEMU(300) // Height in EMUs
	
			// Add the image using the byte array
			run.addPicture(new ByteArrayInputStream(screenshotBytes), XWPFDocument.PICTURE_TYPE_PNG, "Screenshot.png", pictureWidth, pictureHeight)
			println "Screenshot inserted into document."
		} else {
			println "Screenshot file does not exist at: ${screenshotFilePath}"
		}
	
		// Return the screenshot file path
		return screenshotFilePath
	}
	

	// Create folder if it doesn't exist
	private static void createFolder(String folderPath) {
		try {
			File folder = new File(folderPath)
			if (!folder.exists()) {
				boolean folderCreated = folder.mkdirs()
				if (folderCreated) {
					println "Folder created at: ${folderPath}"
				} else {
					println "Failed to create folder at: ${folderPath}"
					throw new IOException("Unable to create directory: ${folderPath}")
				}
			} else {
				println "Folder already exists at: ${folderPath}"
			}
		} catch (IOException ioException) {
			println "Error creating folder at ${folderPath}: ${ioException.message}"
			throw ioException
		}
	}

	// Create metadata table for TCID
	//	private static void createMetadataTable() {
	//		// Create table with two columns: "Field Name" and "Metadata Value"
	//		XWPFTable table = doc.createTable()
	//		table.setWidth(50000)
	//		// Add headers
	//		XWPFTableRow headerRow = table.getRow(0)
	//		headerRow.getCell(0).setText("Field Name")
	//		headerRow.addNewTableCell().setText("Metadata Value")
	//
	//		// Add the metadata rows
	//		addMetadataRow("TCID", GlobalVariable.G_CurrentTCID.toString())  // Example value, you can change it dynamically
	//		addMetadataRow("Test Case Name", GlobalVariable.G_TestCaseName.toString())
	//		addMetadataRow("Platform", GlobalVariable.G_Platform.toString())
	//		addMetadataRow("Requester", "QA Team")
	//		addMetadataRow("Environment", GlobalVariable.G_Environment.toString())
	//		addMetadataRow("Functionality", "Proposal Creation")
	//		addMetadataRow("Product", "Ease App")
	//		addMetadataRow("Policy No", "" )
	////		addMetadataRow("Status", GlobalVariable.G_IsTestPassed.toString() )
	//		addMetadataRow("Status", "")
	//		addMetadataRow("Error if any", "" )
	//
	//		// Add a single line break after the table
	//		XWPFParagraph breakParagraph = doc.createParagraph()
	//		breakParagraph.alignment.CENTER
	//		breakParagraph.createRun().addBreak()
	//	}

	// Create metadata table for TCID
	private static void createMetadataTable() {
		// Create table with two columns: "Column Name" and "Value"
		XWPFTable table = doc.createTable();
		table.setWidth(50000);

		// Add headers (Column names: Field Name, Value)
		XWPFTableRow headerRow = table.getRow(0);

		// Ensure cells are created
		if (headerRow.getCell(0) == null) {
			headerRow.createCell(); // Create first cell
		}
		if (headerRow.getCell(1) == null) {
			headerRow.createCell(); // Create second cell
		}

		//		// Set text for the headers
		//		headerRow.getCell(0).setText("Column Name");
		//		headerRow.getCell(1).setText("Value");

		// Style the "Column Name" cell (first column)
		XWPFParagraph paragraph1 = headerRow.getCell(0).getParagraphs().get(0);
		paragraph1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run1 = paragraph1.createRun();
		run1.setText("Column Name");  // Reset text in case it's not initialized correctly
		run1.setBold(true);
		run1.setColor("FFA500");  // Orange color

		// Style the "Value" cell (second column)
		XWPFParagraph paragraph2 = headerRow.getCell(1).getParagraphs().get(0);
		paragraph2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run2 = paragraph2.createRun();
		run2.setText("Value");  // Reset text
		run2.setBold(true);
		run2.setColor("FFA500");  // Orange color

		// Add the metadata rows, initializing values
		addMetadataRow("TCID", GlobalVariable.G_CurrentTCID.toString())  // Example value, you can change it dynamically
		addMetadataRow("Test Case Name", GlobalVariable.G_TestCaseName.toString() + "    ")
		addMetadataRow("Platform", GlobalVariable.G_Platform.toString())
		//		addMetadataRow("Requester", "QA Team")
		addMetadataRow("Environment", GlobalVariable.G_Environment.toString())
		addMetadataRow("Functionality", "Proposal Creation")
		//		addMetadataRow("Product", "Ease App")
		//		addMetadataRow("Policy No", "Not Set")  // Default empty value
		//		addMetadataRow("Status", "Not Set")    // Default empty value
		//		addMetadataRow("Error if any", "NA")   // Default empty value

		// Add a single line break after the table
		XWPFParagraph breakParagraph = doc.createParagraph()
		breakParagraph.alignment = ParagraphAlignment.CENTER
		breakParagraph.createRun().addBreak()
	}

	private static void addMetadataRow(String fieldName, String value) {
		XWPFTable table = doc.getTables().get(0)
		XWPFTableRow row = table.createRow()
		row.getCell(0).setText(fieldName)
		row.getCell(1).setText(value)

		println "Added row: ${fieldName} -> ${value}"  // Debugging line
	}

	// Method to update metadata row value
	private static void updateMetadataRow(String fieldName, String newValue) {
		if (doc == null || doc.tables.isEmpty()) {
			println "No table found in the document."
			return
		}

		XWPFTable table = doc.getTables().get(0)  // Get the first table in the document

		// Print the table rows for debugging
		println "Table Rows Before Update:"
		for (int i = 0; i < table.rows.size(); i++) {
			XWPFTableRow row = table.getRow(i)
			println "Row ${i}: ${row.getCell(0).getText()} -> ${row.getCell(1).getText()}"
		}

		// Loop through rows, skipping the header row
		for (int i = 1; i < table.rows.size(); i++) {
			XWPFTableRow row = table.getRow(i)

			// Use .equals() to compare the field names
			if (row.getCell(0).getText().equals(fieldName)) {
				// Update the second cell with the new value
				row.getCell(1).setText(newValue)
				println "Updated ${fieldName} with value: ${newValue}"
				return  // Exit once the row is updated
			}
		}

		// If the row doesn't exist, create a new row with the given values
		addMetadataRow(fieldName, newValue)
		println "Added new row for ${fieldName} with value: ${newValue}"
	}





	// Method to update metadata row value
	//	private static void updateMetadataRow(String fieldName, String newValue) {
	//		// Ensure the document and table exist
	//		if (doc == null || doc.tables.isEmpty()) {
	//			println "No table found in the document."
	//			return
	//		}
	//
	//		XWPFTable table = doc.getTables().get(0)  // Get the first table in the document
	//
	//		// Print the table rows for debugging
	//		println "Table Rows Before Update:"
	//		for (int i = 0; i < table.rows.size(); i++) {
	//			XWPFTableRow row = table.getRow(i)
	//			println "Row ${i}: ${row.getCell(0).getText()} -> ${row.getCell(1).getText()}"
	//		}
	//
	//		// Loop through rows, skipping the header row
	//		for (int i = 1; i < table.rows.size(); i++) {
	//			XWPFTableRow row = table.getRow(i)
	//
	//			// Use .equals() to compare the field names
	//			if (row.getCell(0).getText().equals(fieldName)) {
	//				// Update the second cell with the new value
	//				row.getCell(1).setText(newValue)
	//				println "Updated ${fieldName} with value: ${newValue}"
	//				return  // Exit once the row is updated
	//			}
	//		}

	// If the row doesn't exist, create a new row with the given values
	//		addMetadataRow(fieldName, newValue)
	//		println "Added new row for ${fieldName} with value: ${newValue}"
	//	}


	// If the row doesn't exist, create a new row with the given values
	//		addMetadataRow(fieldName, newValue)
	//		println "Added new row for ${fieldName} with value: ${newValue}"
	//	}


	// Add metadata row to the table
	//	private static void addMetadataRow(String fieldName, String value) {
	//		XWPFTable table = doc.getTables().get(0)
	//		XWPFTableRow row = table.createRow()
	//		row.getCell(0).setText(fieldName)
	//		row.getCell(1).setText(value)
	//	}

	// Add description and screenshot to the document
	private static void addDescriptionAndScreenshotToDocument(String screenshotPath, String description) {
		try {


			if (pageBreak == false) {

				// Add a page break after the screenshot (to separate from next content)
				doc.createParagraph().createRun().addBreak(BreakType.PAGE)
			}
			// Add description
			XWPFParagraph descriptionPara = doc.createParagraph()
			descriptionPara.createRun().setText("Description: ${description}")

			// Add a page break after the description
			//			doc.createParagraph().createRun().addBreak(BreakType.PAGE)
			pageBreak = false
			// Insert screenshot
			File screenshotFile = new File(screenshotPath)
			if (screenshotFile.exists()) {
				byte[] screenshotBytes = screenshotFile.bytes
				println "Screenshot byte length: ${screenshotBytes.length} bytes"

				if (screenshotBytes.length == 0) {
					println "Error: Screenshot byte array is empty."
					return
				}

				// Add picture data to the document (PNG type)
				String pictureId = doc.addPictureData(screenshotBytes, XWPFDocument.PICTURE_TYPE_PNG)
				println "Picture data added to document with ID: ${pictureId}"

				// Insert the picture into the document using the picture ID
				XWPFParagraph pictureParagraph = doc.createParagraph()
				XWPFRun run = pictureParagraph.createRun()

				// Resize the picture
				int pictureWidth = Units.toEMU(400)  // Width in EMUs
				int pictureHeight = Units.toEMU(300) // Height in EMUs

				// Add the image using the byte array
				run.addPicture(new ByteArrayInputStream(screenshotBytes), XWPFDocument.PICTURE_TYPE_PNG, "Screenshot.png", pictureWidth, pictureHeight)
				println "Screenshot inserted into document."
			} else {
				println "Screenshot file not found or cannot be read."
			}
		} catch (Exception e) {
			println "Error while adding screenshot to the document: ${e.message}"
			throw new Exception("Failed to add screenshot to document. ${e.message}")
		}
	}

	static void saveWordDocument() {
		try {
			// Ensure document is not null
			if (doc != null && outStream != null) {
				// Add "End of Document" section
				XWPFParagraph endPara = doc.createParagraph()
				endPara.createRun().setText("======= End of Document =====")
				// Adjust vertical alignment (to avoid excessive space at the bottom of the page)
				//				endPara.setVerticalAlignment(TextAlignment.CENTER);
				endPara.setAlignment(ParagraphAlignment.CENTER);

				// Write the document to the output stream
				doc.write(outStream)
				outStream.close()
				println "Word document generated successfully at: ${metadataFilePath}"
			} else {
				println "Error: Document or output stream is not initialized."
			}
		} catch (Exception e) {
			println "Error while saving the Word document: ${e.message}"
			throw new Exception("Failed to save Word document. ${e.message}")
		}
	}
}
