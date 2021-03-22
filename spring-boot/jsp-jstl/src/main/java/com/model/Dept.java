package com.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

	private int deptNo;
	private String deptName;
	private String location;
	private Set<Emp> employees;

	public Dept(int deptNo, String deptName, String location) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.location = location;
	}

	public void addEmployee(Emp employee) {
		if (this.employees == null) {
			this.employees = new HashSet<Emp>();
		}
		this.employees.add(employee);
	}

}
