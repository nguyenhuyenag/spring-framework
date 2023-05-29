package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MySQLToolService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean checkTableExits(String tableName) {
		String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA();";
		List<String> tables = jdbcTemplate.queryForList(sql, String.class);
		return tables != null ? tables.stream().anyMatch(t -> t.equalsIgnoreCase(tableName)) : false;
	}

}
