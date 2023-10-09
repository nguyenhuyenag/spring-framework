//package com.service.impl;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//	// private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
//
//	@Autowired
//	private NamedParameterJdbcTemplate namedJdbcTemplate;
//
//	public List<String> getRolesByUserId(int userId) {
//		String sql = "SELECT t1.role_name" //
//				+ " FROM role t1" //
//				+ " JOIN user_roles t2" //
//				+ " ON t1.role_id = t2.role_id" //
//				+ " WHERE t2.user_id = :userId";
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("userId", userId);
//		// LOG.info(sql);
//		return namedJdbcTemplate.queryForList(sql, params, String.class);
//	}
//
//	public List<GrantedAuthority> getGrantedAuthorityByUserId(int userId) {
//		List<String> roles = getRolesByUserId(userId);
//		return roles == null ? Collections.emptyList()
//				: roles.stream() //
//						.map(SimpleGrantedAuthority::new) //
//						.collect(Collectors.toList());
//	}
//
//}
