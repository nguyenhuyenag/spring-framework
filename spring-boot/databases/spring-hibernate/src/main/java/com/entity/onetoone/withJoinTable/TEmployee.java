package com.entity.onetoone.withJoinTable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class TEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable( //
		name = "emp_workstation", //
		joinColumns = { @JoinColumn(name = "employee_id", referencedColumnName = "id") }, //
		inverseJoinColumns = { @JoinColumn(name = "workstation_id", referencedColumnName = "id") } //
	)
	private WorkStation workStation;

}
