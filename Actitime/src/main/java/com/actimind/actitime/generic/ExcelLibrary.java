package com.actimind.actitime.generic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	
	public static String getData(String filePath, String sheetName, int row, int cell) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook=WorkbookFactory.create(file);
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).toString();	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getAllData(String filePath, String sheetName) {
		FileInputStream file;
		try {
			file = new FileInputStream(filePath);
			Workbook workbook=WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet(sheetName);
			int totalRows=sheet.getPhysicalNumberOfRows();
			int totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
			String[][] sarr = new String[totalRows-1][totalCells];
			
			for(int i=1,k=0;i<=totalRows-1;i++,k++) {			
				for(int j=0;j<=totalCells-1;j++) {
					sarr[k][j]=sheet.getRow(i).getCell(j).toString();
				}
			}			
			return sarr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;				
	}
}
