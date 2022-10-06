package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 2557799464431032459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	private String password;
	private String username;
	private byte enabled;

	// need `fetch = FetchType.EAGER`
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // trỏ tới tên biến user ở trong UserRole
	private List<UserRole> userRoles = new ArrayList<>();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}