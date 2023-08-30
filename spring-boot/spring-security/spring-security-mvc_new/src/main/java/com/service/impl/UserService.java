package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	// private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<String> getRolesByUserId(int userId) {
		String sql = "SELECT t1.role_name" //
				+ " FROM role t1" //
				+ " JOIN user_roles t2" //
				+ " ON t1.role_id = t2.role_id" //
				+ " WHERE t2.user_id = :userId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		// LOG.info(sql);
		List<String> roles = namedJdbcTemplate.queryForList(sql, params, String.class);
		return roles;
	}

}
