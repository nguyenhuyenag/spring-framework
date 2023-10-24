package com.entity.onetoone.withForeignKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Một thực thể A tương ứng với một thực thể B và ngược lại
 */
@Entity
public class MyAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "address")
	private MyUser user;

}
