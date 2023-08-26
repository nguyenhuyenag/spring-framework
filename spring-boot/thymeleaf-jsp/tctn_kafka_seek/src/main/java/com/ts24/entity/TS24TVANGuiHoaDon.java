package com.ts24.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.entity.TVANGuiHoaDon;

@Entity
@Table(name = "tvan_gui_hoadon")
public class TS24TVANGuiHoaDon extends TVANGuiHoaDon implements Serializable {

	private static final long serialVersionUID = 515027760598197195L;

}