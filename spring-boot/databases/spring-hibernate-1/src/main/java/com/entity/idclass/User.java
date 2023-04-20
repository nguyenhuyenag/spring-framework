package com.entity.idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@IdClass(UserId.class)
@Table(name = "student")
public class User {

	@Id
	private Integer id;

	@Id
	private Integer code;

	@Column
	private String name;

	// @Column
	@Transient // Đánh dấu cột này không có trong DB
	private String company;

	public User(final int code, final String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
