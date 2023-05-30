package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "role_id")
	private String roleId;

	@Column(name = "role_name")
	private String roleName;

//	@OneToMany(mappedBy = "role") // trỏ tới tên biến role ở trong UserRole
//	private Set<UserRole> userRoles = new HashSet<>();

}
