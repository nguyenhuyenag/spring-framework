package com.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

	private int empNo;
	private String empName;
	private String job;

	private Date hireDate;
	private float salary;

	private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

	// @hireDateStr - phải có định dạng 'MM/dd/yyyy'.
	public Emp(int empNo, String empName, String job, String hireDateStr, float salary) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.salary = salary;
		try {
			this.hireDate = df.parse(hireDateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
