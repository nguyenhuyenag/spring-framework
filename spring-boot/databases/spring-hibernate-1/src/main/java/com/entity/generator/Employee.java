package com.entity.generator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id được định nghĩa ở class GeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.entity.generator.GeneratorId")
	private String id;

	@SuppressWarnings("unused")
	private String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
