package com.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private long id;
	private String name;
	private String contactNumber;
	private String workingArea;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", workingArea="
				+ workingArea + "]";
	}

}
