package com.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

	private static final String FILE_NAME = Paths.get(PathUtils.RESOURCES, "file/vocabulary.xlsx").toString();

	public static String getCell(XSSFRow row, int i) {
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
			for (char c = 'A'; c <= 'Z'; c++) {
				System.out.println(c);
				List<String> currentList = repository.findAllVocab();
				List<Vocabulary> listWords = new ArrayList<>();
				XSSFSheet worksheet = workbook.getSheet(String.valueOf(c));
				// XSSFSheet worksheet = workbook.getSheetAt(16);
				for (int i = 0; i <= worksheet.getLastRowNum(); i++) {
					XSSFRow row = worksheet.getRow(i);
					if (row != null) {
						String word = getCell(row, 0).toLowerCase();
						if (!currentList.contains(word)) {
							String pro = getCell(row, 1).toLowerCase();
							String mean = getCell(row, 2).toLowerCase();
							listWords.add(new Vocabulary(word, pro, mean));
						}
					}
				}
				if (listWords.size() > 0) {
					repository.saveAll(listWords);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
