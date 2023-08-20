package com.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {

	private Integer id;
	private String joke;
	private List<String> categories;

}
