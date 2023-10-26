package com.entity.onetoone.withJoinColumn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * + @OneToOne(mappedBy = "address"): Dùng để cho JPA biết rằng thực thể User
 * không chứa khóa ngoại, hãy tìm kiếm khóa ngoại trong thực thể Address tại
 * trường có tên là 'address'. Khóa ngoại đó được chỉ ra bới giá trị của thuộc
 * tính `name`.
 */
@Data
@Entity
@Table(name = "address_jc")
public class AddressJC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String street;
	private String city;

	@OneToOne(mappedBy = "address")
	private UserJC user;

}
