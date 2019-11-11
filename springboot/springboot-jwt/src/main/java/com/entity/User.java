package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	@Column(unique = true, nullable = false)
	private String username;

	@Size(min = 8, message = "Minimum password length: 8 characters")
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	@JoinTable( //
		name = "user_role", //
		joinColumns = { @JoinColumn(name = "user_id") }, //
		inverseJoinColumns = { @JoinColumn(name = "role_id") } //
	)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Role> listRoles = new HashSet<>();

}