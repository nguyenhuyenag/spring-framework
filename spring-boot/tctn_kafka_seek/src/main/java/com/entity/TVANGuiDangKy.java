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
@Table(name = "tvan_gui_dangky")
public class TVANGuiDangKy implements Serializable {

	private static final long serialVersionUID = 7981569539700410422L;

	@Id
	private String guid;

	@Column(name = "guid_dangky")
	private String guidDangky;

	@Column(name = "id_user")
	private String idUser;

	private String loaidangky;
	private String tentokhai;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "execute_time")
	private Date executeTime;

	@Column(name = "maloai_tdiep")
	private String maloaiTdiep;

	private String matdiep;
	private String manoigui;
	private String manoinhan;
	private String masothue;

	@Column(name = "masothue_tvan")
	private String masothueTvan;

	@Column(name = "mathdiep_thamchieu")
	private String mathdiepThamchieu;

	private int nam;

	private Date ngaycapnhat;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaygui_dangky")
	private Date ngayguiDangky;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaygui_tct")
	private Date ngayguiTct;

	@Column(name = "noidung_gui")
	private String noidungGui;

	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	private String spare5;

	@Column(name = "tinhtrang_gui")
	private int tinhtrangGui;

	@Column(name = "tinhtrang_ky")
	private int tinhtrangKy;

	@Column(name = "create_time")
	private long createTime = 0;

	@Column(name = "matdiep_tcgp")
	private String matdieptcgp;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}