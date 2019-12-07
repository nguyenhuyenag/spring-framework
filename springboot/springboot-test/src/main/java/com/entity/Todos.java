package com.entity;

import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "todos")
public class Todos {

	private Integer userId;
	private Integer id;
	private String title;
	private boolean completed;

}
