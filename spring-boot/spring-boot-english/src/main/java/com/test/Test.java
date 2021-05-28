package com.test;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entity.Vocabulary;

public class Test {

	private final static Path FILE = Paths.get("D:/GDrive/ToCompany/english/vocabulary_new.xlsx");
	
	private static String getCellValue(XSSFRow row, int i) {
		if (row == null) {
			return "";
		}
		try {
			XSSFCell cell = row.getCell(i);
			return cell != null ? cell.getStringCellValue().trim().toLowerCase() : "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static Vocabulary rowToVocab(XSSFRow row) {
		String word = getCellValue(row, 0);
		String pronounce = getCellValue(row, 1);
		String translate = getCellValue(row, 2);
		if (StringUtils.isNotEmpty(word) && StringUtils.isNotEmpty(translate)) {
			return new Vocabulary(word, pronounce, translate);
		}
		return null;
	}

	public static Set<Vocabulary> getAllWordInSheet(String sheetName) {
		Set<Vocabulary> data = new LinkedHashSet<>();
		try ( //
			FileInputStream file = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(file); //
		) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				Vocabulary entity = rowToVocab(row);
				data.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void main(String[] args) {
		
	}

}
