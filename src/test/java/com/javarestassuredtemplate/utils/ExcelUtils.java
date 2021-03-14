package com.javarestassuredtemplate.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName) throws IOException{
        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);
    }
    /*public static void getRowCount() throws IOException {
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println(rowCount);
    }*/

    public static Integer getRowCount() throws IOException {
        int rowCount = sheet.getPhysicalNumberOfRows();
        return rowCount;
    }

    /*public static void getCellData(int rowNum, int colNum) throws IOException {
        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println(value);
    }*/

    public static Object getCellData(int rowNum, int colNum) throws IOException {
        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        return value;
    }


}
