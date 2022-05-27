package com.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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

	private final Path FILE = Paths.get("D:/GDrive/ToCompany/english/vocabulary.xlsx");

	private String getCellValue(XSSFRow row, int i) {
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
		String word 		= getCellValue(row, 0);
		String pronounce 	= getCellValue(row, 1);
		String translate	= getCellValue(row, 2);
		if (StringUtils.isNotEmpty(word) && StringUtils.isNotEmpty(translate)) {
			return new Vocabulary(word, pronounce, translate);
		}
		return null;
	}

	@Override
	public List<String> importExcel() {
		int size, count = 0;
		List<String> message = new ArrayList<>();
		if (PathUtils.isNotExist(FILE)) {
			message.add("File not found!");
			return message;
		}
		try ( //
			FileInputStream file = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(file); //
		) {
			Iterator<Sheet> itr = workbook.sheetIterator();
			while (itr.hasNext()) {
				String sheetName = itr.next().getSheetName();
				char c = sheetName.charAt(0);
				if (sheetName.length() == 1 && ('A' <= c || c <= 'Z')) {
					List<Vocabulary> listVocab = new ArrayList<>();
					XSSFSheet sheet = workbook.getSheet(sheetName);
					for (int i = 0; i <= sheet.getLastRowNum(); i++) {
						XSSFRow row = sheet.getRow(i);
						if (row != null) {
							Vocabulary entity = rowToVocab(row); // get new word
							if (entity != null) {
								Vocabulary vcb = repository.findByWord(entity.getWord()); // get word from db
								if (vcb == null) { // chưa có
									listVocab.add(entity); // thêm mới
								} else { // đã có
									if (!vcb.equals(entity)) { // so sánh để update
										vcb.setPronounce(entity.getPronounce());
										vcb.setTranslate(entity.getTranslate());
										// BeanUtils.copyProperties(entity, vcb, "id, word, count");
										repository.save(vcb);
										message.add("Update: " + vcb.getWord());
									}
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
			message.add("Add new " + count + " word");
		}
		if (message.size() == 0) {
			message.add("No change!");
		}
		return message;
	}

	private Set<Vocabulary> getAllWordInSheet(String sheetName) {
		Set<Vocabulary> data = new HashSet<>();
		try ( //
			FileInputStream file = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(file); //
		) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				Vocabulary entity = rowToVocab(row);
				if (entity != null) {
					data.add(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public int addNew() {
		int count = 0;
		try ( //
			FileInputStream file = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(file); //
		) {
			Set<Vocabulary> data = getAllWordInSheet("NEW");
			for (Vocabulary v : data) {
				String sheetName = String.valueOf(v.getWord().charAt(0)).toUpperCase();
				Set<Vocabulary> set = getAllWordInSheet(sheetName);
				if (!set.add(v)) {
					continue;
				}
				XSSFSheet sheet = workbook.getSheet(sheetName);
				// last row
				int i = sheet.getLastRowNum() + 1;
				XSSFRow row = sheet.createRow(i);
				// word
				row.createCell(0).setCellValue(v.getWord());
				// pronounce
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(v.getPronounce());
				// align center
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell1.setCellStyle(cellStyle);
				// translate
				row.createCell(2).setCellValue(v.getTranslate());
				count++;
			}
			
			if (!data.isEmpty()) {
				FileOutputStream out = new FileOutputStream(FILE.toFile());
				workbook.write(out);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
