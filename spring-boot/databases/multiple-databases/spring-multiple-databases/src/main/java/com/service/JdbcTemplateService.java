package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.primary.entity.Customer;
import com.second.entity.Office;

@Service
public class JdbcTemplateService {

	@Autowired
	@Qualifier("Jdbc1Template") // no need
	private JdbcTemplate jdbc1Template;

	@Autowired
	@Qualifier("jdbc2Template")
	private JdbcTemplate jdbc2Template;

	public void showDataSourceURL() {
		try {
			String url1 = jdbc1Template.getDataSource().getConnection().getMetaData().getURL();
			String url2 = jdbc2Template.getDataSource().getConnection().getMetaData().getURL();
			System.out.println(url1);
			System.out.println(url2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findAll() {
		find1All();
		find2All();
	}

	private BeanPropertyRowMapper<Customer> customerRowMapper() {
		return BeanPropertyRowMapper.newInstance(Customer.class);
	}

	private BeanPropertyRowMapper<Office> officeRowMapper() {
		return BeanPropertyRowMapper.newInstance(Office.class);
	}

	public void find1All() {
		String sql = "SELECT t.* FROM customer t;";
		List<Customer> result = jdbc1Template.query(sql, customerRowMapper());
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	public void find2All() {
		String sql = "SELECT t.* FROM office t;";
		List<Office> result = jdbc2Template.query(sql, officeRowMapper());
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

}
