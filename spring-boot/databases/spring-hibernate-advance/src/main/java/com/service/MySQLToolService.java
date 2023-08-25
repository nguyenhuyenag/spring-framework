package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/*-
 * - Check table exist
 * 
 * - Get MySQL connnection
 */
@Service
public class MySQLToolService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * SCHEMA() -> Current database name
	 */
	public boolean checkTableExist(String tableName) {
		String sql = "SHOW TABLES";
		// String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA()";
		List<String> listTables = jdbcTemplate.queryForList(sql, String.class);
		System.out.println("All tables: " + listTables);
		return listTables != null ? listTables.stream().anyMatch(t -> t.equalsIgnoreCase(tableName)) : false;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
