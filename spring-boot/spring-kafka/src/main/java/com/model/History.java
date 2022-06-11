package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mathongdiep")
	private String maThongDiep;

	private String note;

	@CreationTimestamp
	@Column(updatable = false)
	private Date createtime;

	public History(String mtd) {
		this.maThongDiep = mtd;
	}
	
	public History(String mtd, String note) {
		this.note = note;
		this.maThongDiep = mtd;
	}

}
