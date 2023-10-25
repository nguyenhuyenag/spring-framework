package com.entity.onetoone.withJoinColumn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * + mappedBy = "address": Dùng để cho JPA biết rằng thực thể MyUser không chứa
 * khóa ngoại, hãy tìm kiếm khóa ngoại trong thực thể MyAddress tại trường có
 * tên là 'address'. Khóa ngoại đó được chỉ ra bới giá trị của thuộc tính `name`
 * trong annotation @JoinColumn
 */
@Data
@Entity
@Table(name = "my_address")
public class MyAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String street;
	private String city;

	@OneToOne(mappedBy = "address")
	private MyUser user;

}
