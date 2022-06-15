package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lorem_ipsum")
public class Ipsum implements Serializable {

	private static final long serialVersionUID = 515027760598197195L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
	private String content;
	private int status = 0;

	@CreationTimestamp
	@Column(name = "create_time")
	private Date createTime;

	@UpdateTimestamp
	@Column(name = "send_time")
	private Date sendTime;

//	@UpdateTimestamp
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "ngaygui_hoadon")
//	private Date ngayguiHoadon;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}