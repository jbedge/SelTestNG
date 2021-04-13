package com.qa.generic;

import java.io.FileInputStream;
import java.util.Hashtable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ScriptName : DataPool
 * @Description :
 * @Author : 
 */

public class DataPool {

	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	private FileInputStream excelFileIS;
	private Row headerRow;
	private Row testDataRow;


	
	public Hashtable<String, Hashtable<String, String>> loadTestData(String testDataFilePath) {
		Hashtable<String, Hashtable<String, String>> objDataProvider = null;

		// Logic to read the Excel workBook
		try {
			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
			testDataRow = sheet.getRow(1);
			int lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0;
			String bufferCell = "";
			String strRMID = "";
			objDataProvider = new Hashtable<String, Hashtable<String, String>>();

			while (rowIndex <= lastRowNumber) {
				bufferCell = this.getCellValue(sheet.getRow(rowIndex), 0);
				if (bufferCell.trim().equalsIgnoreCase("RM ID")) {
					headerRow = sheet.getRow(rowIndex);
					testDataRow = sheet.getRow(rowIndex + 1);
					strRMID = this.getCellValue(testDataRow, 0);

					Hashtable<String, String> dataValueSet = new Hashtable<String, String>();
					int clmNo = 0;
					// iterating over cells
					do {
						String header = "", testData = "";
						// Key Data
						header = this.getCellValue(headerRow, clmNo);
						// Value
						testData = this.getCellValue(testDataRow, clmNo);

						if (!header.equals(""))
							dataValueSet.put(header, testData);
						clmNo++;

					} while (clmNo < headerRow.getLastCellNum());

					clmNo = 0;
					objDataProvider.put(strRMID, dataValueSet);
				}
				rowIndex++;
				rowIndex++;
			}
			excelFileIS.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {

		}
		return objDataProvider;
	}

	/**
	 * @Method : getCellValue(Cell testDataCell, int columnNumber)
	 * @Description : Get Cell value for given cell (Used in loadDataProvider)
	 * @author : Automation Tester
	 */
	private String getCellValue(Row testDataRow, int columnNumber) {

		if (testDataRow == null)
			return "";
		else {
			Cell testDataCell = testDataRow.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
			if (testDataCell == null)
				return "";
			else
				return testDataCell.toString().trim();
		}
	}
}
