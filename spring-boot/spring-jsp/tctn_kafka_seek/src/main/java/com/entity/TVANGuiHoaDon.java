package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tvan_gui_hoadon")
public class TVANGuiHoaDon implements Serializable {

	private static final long serialVersionUID = 515027760598197195L;

	@Id
	private String guid;

	@Column(name = "guid_hoadon")
	private String guidHoadon;

	@Column(name = "id_user")
	private String idUser;

	private String loaihoadon;

	@Column(name = "maloai_tdiep")
	private String maloaiTdiep;

	private String manoigui;

	private String manoinhan;

	private String masothue;

	@Column(name = "masothue_tvan")
	private String masothueTvan;

	private String matdiep;

	@Column(name = "mathdiep_thamchieu")
	private String mathdiepThamchieu;

	private int nam = 0;
	private int thang = 0;

	private Date ngaycapnhat;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "execute_time")
	private Date executeTime;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaygui_hoadon")
	private Date ngayguiHoadon;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaygui_tct")
	private Date ngayguiTct;

	@Column(name = "noidung_gui")
	private String noidungGui;

	private int soluong;

	@Column(name = "tenhoso")
	private String tenHoSo;

	@Column(name = "tinhtrang_gui")
	private int tinhtrangGui;

	@Column(name = "tinhtrang_ky")
	private int tinhtrangKy;

	@Column(name = "create_time")
	private long createTime = 0;

	@Column(name = "matdiep_tcgp")
	private String matdieptcgp;

	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	private String spare5;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}