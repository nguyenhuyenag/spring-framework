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

	private String getCell(XSSFRow row, int i) {
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

	private Vocabulary rowToVocab(XSSFRow row) {
		return new Vocabulary(getCell(row, 0), getCell(row, 1), getCell(row, 2));
	}

	@Override
	public List<String> importExcel() {
		int size, count = 0;
		List<String> msg = new ArrayList<>();
		try ( //
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME)); //
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); //
		) {
			Iterator<Sheet> itr = workbook.sheetIterator();
			while (itr.hasNext()) {
				String sheetName = itr.next().getSheetName();
				char c = sheetName.charAt(0);
				if (sheetName.length() == 1 && ('A' <= c || c <= 'Z')) {
					List<Vocabulary> listVocab = new ArrayList<>();
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					// XSSFSheet worksheet = workbook.getSheetAt(16);
					for (int i = 0; i <= worksheet.getLastRowNum(); i++) {
						XSSFRow row = worksheet.getRow(i);
						if (row != null) {
							Vocabulary entity = rowToVocab(row);						// new word
							Vocabulary vcb = repository.findByWord(entity.getWord());	// word from db
							if (vcb == null) { 				// chưa có
								listVocab.add(entity); 		// thêm mới
							} else { 						// đã có
								if (!vcb.equals(entity)) { 	// so sánh để update
									vcb.setPronounce(entity.getPronounce());
									vcb.setMean(entity.getMean());
									repository.save(vcb);
									msg.add("Update: " + vcb.getWord());
								}
							}
						}
					}
					size = listVocab.size();
					if (size > 0) {
						repository.saveAll(listVocab);
						count += size;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			msg.add("Add new " + count + " word");
		}
		return msg;
	}

}
