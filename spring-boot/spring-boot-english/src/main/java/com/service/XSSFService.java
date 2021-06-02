package com.service;

import java.util.List;

public interface XSSFService {

	/**
	 * Import vocabulary from "NEW" sheet to "A - Z" sheet
	 */
	int addNew();

	/**
	 * Import vocabulary to database
	 */
	List<String> importExcel();

}
