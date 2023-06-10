package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.primary.entity.Address;
import com.second.entity.User;
import com.util.BeanName;

@Service
public class JdbcTemplateService {

	@Autowired
	private JdbcTemplate jdbc1Template;

	@Autowired
	@Qualifier(BeanName.DB2_JDBCTEMPLATE)
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

	public void find1All() {
		String sql = "SELECT t.* FROM address t;";
		List<Address> result = jdbc1Template.query(sql, addressRowMapper());
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	public void find2All() {
		String sql = "SELECT t.* FROM user t;";
		List<User> result = jdbc2Template.query(sql, userRowMapper());
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	private BeanPropertyRowMapper<Address> addressRowMapper() {
		return BeanPropertyRowMapper.newInstance(Address.class);
	}

	private BeanPropertyRowMapper<User> userRowMapper() {
		return BeanPropertyRowMapper.newInstance(User.class);
	}

}
