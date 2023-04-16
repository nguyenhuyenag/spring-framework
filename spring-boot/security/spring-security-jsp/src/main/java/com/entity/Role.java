package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = -5653427440193976467L;

	@Id
	@Column(name = "role_id")
	private String roleId;

	@Column(name = "role_name")
	private String roleName;

	@OneToMany(mappedBy = "role") // trỏ tới tên biến role ở trong UserRole
	private Set<UserRole> userRoles = new HashSet<>();
	
}
