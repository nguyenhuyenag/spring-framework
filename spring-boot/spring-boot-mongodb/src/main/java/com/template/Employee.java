package com.template;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Employee")
public class Employee {

	@Id
	private Long id;

	@Indexed(unique = true)
	@Field(value = "Emp_No")
	private String empNo;

	@Field(value = "Full_Name")
	private String fullName;

	@Field(value = "Hire_Date")
	private Date hireDate;

	@Override
	public String toString() {
		return "id:" + this.id + ", empNo: " + empNo //
				+ ", fullName: " + this.fullName + ", hireDate: " + this.hireDate;
	}
}
