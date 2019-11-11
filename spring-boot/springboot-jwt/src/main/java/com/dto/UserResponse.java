package com.dto;

import java.util.List;

import com.entity.Role;

import lombok.Data;

@Data
public class UserResponse {

	private Integer id;
	private String username;
	private String email;
	List<Role> roles;

}
