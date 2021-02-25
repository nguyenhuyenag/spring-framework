package com.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.XSSFService;
import com.util.PathUtils;

@Service
public class XSSFServiceImpl implements XSSFService {

	@Autowired
	private VocabRepository repository;

	private static final String FILE_NAME = Paths.get(PathUtils.RESOURCES, "data/vocabulary.xlsx").toString();

	public static String getCell(XSSFRow row, int i) {
		if (row == null) {
			return "";
		}
		try {
			XSSFCell cell = row.getCell(i);
			return cell != null ? cell.getStringCellValue() : "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void importExcel() {
		try ( //
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME)); //
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); //
		) {
			Iterator<Sheet> itr = workbook.sheetIterator();
			while (itr.hasNext()) {
				String sheetName = itr.next().getSheetName();
				char c = sheetName.charAt(0);
				if (sheetName.length() == 1 && ('A' <= c || c <= 'Z')) {
					List<String> currentList = repository.findAllVocab();
					List<Vocabulary> listWords = new ArrayList<>();
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					// XSSFSheet worksheet = workbook.getSheetAt(16);
					for (int i = 0; i <= worksheet.getLastRowNum(); i++) {
						XSSFRow row = worksheet.getRow(i);
						if (row != null) {
							String word = getCell(row, 0).trim().toLowerCase();
							if (!currentList.contains(word)) {
								String pro = getCell(row, 1).trim().toLowerCase();
								String mean = getCell(row, 2).trim().toLowerCase();
								listWords.add(new Vocabulary(word, pro, mean));
							}
						}
					}
					if (listWords.size() > 0) {
						repository.saveAll(listWords);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
