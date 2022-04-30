package com.entity;

import java.io.Serializable;
import java.util.List;

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

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	private String roleId;

	@Column(name = "role_name")
	private String roleName;

	@OneToMany(mappedBy = "role") // trỏ tới tên biến role ở trong UserRole
	private List<UserRole> userRoles;

//	public UserRole addUserRole(UserRole userRole) {
//		getUserRoles().add(userRole);
//		userRole.setRole(this);
//
//		return userRole;
//	}
//
//	public UserRole removeUserRole(UserRole userRole) {
//		getUserRoles().remove(userRole);
//		userRole.setRole(null);
//
//		return userRole;
//	}

}