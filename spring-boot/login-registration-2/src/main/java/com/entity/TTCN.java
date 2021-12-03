package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ttcn")
public class TTCN implements Serializable {

	private static final long serialVersionUID = -5621369484896835672L;

	@Id
	private String masobhxh;
	// private Long id;
	private String sohoso;
	private String createDate;

	private String hoten;
	private String diachilh;
	private String hotennguoilh;
	private String sodienthoai;
	private String nguoiduyet;
	private String tencoquannguoiduyet;
	private String ngayduyet;
	private String vssidLogged;
	private String hosokekhai;
	private int trangthai = 0;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getSohoso() {
		return sohoso;
	}

	public void setSohoso(String sohoso) {
		this.sohoso = sohoso;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMasobhxh() {
		return masobhxh;
	}

	public void setMasobhxh(String masobhxh) {
		this.masobhxh = masobhxh;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getDiachilh() {
		return diachilh;
	}

	public void setDiachilh(String diachilh) {
		this.diachilh = diachilh;
	}

	public String getHotennguoilh() {
		return hotennguoilh;
	}

	public void setHotennguoilh(String hotennguoilh) {
		this.hotennguoilh = hotennguoilh;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getNguoiduyet() {
		return nguoiduyet;
	}

	public void setNguoiduyet(String nguoiduyet) {
		this.nguoiduyet = nguoiduyet;
	}

	public String getTencoquannguoiduyet() {
		return tencoquannguoiduyet;
	}

	public void setTencoquannguoiduyet(String tencoquannguoiduyet) {
		this.tencoquannguoiduyet = tencoquannguoiduyet;
	}

	public String getNgayduyet() {
		return ngayduyet;
	}

	public void setNgayduyet(String ngayduyet) {
		this.ngayduyet = ngayduyet;
	}

	public String getVssidLogged() {
		return vssidLogged;
	}

	public void setVssidLogged(String vssidLogged) {
		this.vssidLogged = vssidLogged;
	}

	public String getHosokekhai() {
		return hosokekhai;
	}

	public void setHosokekhai(String hosokekhai) {
		this.hosokekhai = hosokekhai;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
