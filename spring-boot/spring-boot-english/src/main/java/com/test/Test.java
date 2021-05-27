package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	private final static Path FILE = Paths.get("D:/GDrive/ToCompany/english/vocabulary_new.xlsx");

//	private static String getCell(XSSFRow row, int i) {
//		if (row == null) {
//			return "";
//		}
//		try {
//			XSSFCell cell = row.getCell(i);
//			return cell != null ? cell.getStringCellValue().trim().toLowerCase() : "";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	public static void main(String[] args) {
		try ( //
			FileInputStream excelFile = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); //
		) {
			XSSFSheet sheet = workbook.getSheet("new");
			// XSSFRow row = sheet.getRow(sheet.getLastRowNum());
			// System.out.println(getCell(row, 0));
			// System.out.println(getCell(row, 1));
			// System.out.println(getCell(row, 2));
			int i = sheet.getLastRowNum();
			XSSFRow row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Type of Cell");
			row.createCell(1).setCellValue("cell value");
			row.createCell(2).setCellValue("cell 12457");
			FileOutputStream out = new FileOutputStream(FILE.toFile());
		    workbook.write(out);
		    out.close();
		    System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}