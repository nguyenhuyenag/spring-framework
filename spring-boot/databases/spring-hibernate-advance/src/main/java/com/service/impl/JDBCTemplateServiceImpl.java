package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.service.JDBCTemplateService;

@Service
public class JDBCTemplateServiceImpl implements JDBCTemplateService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;

	private BeanPropertyRowMapper<User> rowMapper() {
		return BeanPropertyRowMapper.newInstance(User.class);
	}

	@Override
	public void insert() {
		String age = RandomStringUtils.randomNumeric(2);
		String name = RandomStringUtils.randomAlphabetic(5).toUpperCase();
		String sql = "INSERT INTO t_user (name, age) VALUES (:name, :age)";
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("age", age);
		npJdbcTemplate.update(sql, params);
		// jdbcTemplate.update("INSERT INTO t_user (name, age) VALUES (?, ?)", new Object[] { name, age });
	}

	@Override
	public boolean deleteById(int id) {
		String sqlQuery = "DELETE FROM t_user t WHERE t.id = (:id)";
		Map<String, Integer> namedParameters = Collections.singletonMap("id", id);
		int rows = npJdbcTemplate.update(sqlQuery, namedParameters);
		return rows == 1;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("SELECT * FROM t_user", rowMapper());
	}

	@Override
	public List<String> findAllName() {
		return jdbcTemplate.queryForList("SELECT t.name FROM t_user t", String.class);
	}

	@Override
	public int findMaxAge() {
		Integer age = jdbcTemplate.queryForObject("SELECT max(t.age) FROM t_user t", Integer.class);
		return (age != null ? age.intValue() : 0);
	}

	@Override
	public User findByName(String name) {
		String sql = "SELECT * FROM t_user t WHERE t.name = (:name)";
		SqlParameterSource params = new MapSqlParameterSource() //
				.addValue("name", name);
		return npJdbcTemplate.queryForObject(sql, params, rowMapper());
	}

	@Override
	public void batchUpdate() {
		long start = 0, end = 0;
		String sql = "INSERT INTO t_user (name, age) VALUES (?, ?)";
		
		// Without batchUpdate
		start = System.currentTimeMillis();
		for (int i = 0; i < 99; i++) {
			String age = RandomStringUtils.randomNumeric(2);
			String name = RandomStringUtils.randomAlphabetic(5).toUpperCase();
			jdbcTemplate.update(sql, name, Integer.parseInt(age));
		}
		end = System.currentTimeMillis();
		System.out.println("Without batchUpdate: " + (end - start));
		
		// batchUpdate
		start = System.currentTimeMillis();
		List<Object[]> list = new ArrayList<>();
		for (int i = 0; i < 99; i++) {
			String[] arr = new String[2];
			arr[0] = RandomStringUtils.randomAlphabetic(5).toUpperCase();
			arr[1] = RandomStringUtils.randomNumeric(2);
			list.add(arr);
		}
		jdbcTemplate.batchUpdate(sql, list);
		end = System.currentTimeMillis();
		System.out.println("With batchUpdate: " + (end - start));
	}

}
