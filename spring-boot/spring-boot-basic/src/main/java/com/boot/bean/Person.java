package com.boot.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Person implements Serializable {

	private static final long serialVersionUID = 5956882926782425852L;

	private int age;
	private String name;

}
