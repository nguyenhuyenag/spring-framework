package com.ts24.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tvan_tdiep_mq")
public class TS24TVANTDiepMQ implements Serializable {

	private static final long serialVersionUID = -9030654146132483940L;

	@Id
	private String id;

	private String maloai;
	private String matdiep;
	private String matdieptchieu;
	private int nam = 0;
	private String ngaycapnhat;
	private String ngaynhan;
	private String ngaytao;
	private String noidung;
	private String masothue;
	private String mota;
	private String macqt;
	private String qrcode;
	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	private String spare5;
	private int thang = 0;

	@Column(name = "trangthai_tdiep")
	private int trangthaitdiep = 0;

	@Column(name = "file_tdiep")
	private String fileTdiep;

	@Column(name = "tinhtrang")
	private int tinhTrang;

	@Column(name = "executetime")
	private String executeTime = null;

	@Column(name = "create_time")
	private long createTime = 0;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
