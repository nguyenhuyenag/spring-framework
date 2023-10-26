package com.entity.onetoone.withJoinTable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * + joinColumns = @JoinTable(name = "user_id"): Xác định cột trong bảng liên
 * kết sẽ được dùng để lưu trữ khoá ngoại từ đối tượng User. Ở đây, cột trong
 * bảng liên kết có tên "user_id" sẽ được dùng để lưu trữ khoá ngoại từ đối
 * tượng User. Điều này có nghĩa rằng cột "user_id" trong bảng liên kết sẽ tham
 * chiếu đến khoá chính trong bảng my_user.
 * 
 * + inverseJoinColumns = @JoinTable(name = "address_id"): Xác định cột trong
 * bảng liên kết sẽ được dùng để lưu trữ khoá ngoại từ đối tượng Address. Ở đây,
 * cột trong bảng liên kết có tên "address_id" sẽ được dùng để lưu trữ khoá
 * ngoại từ đối tượng Address. Có nghĩa là cột "address_id" trong bảng liên kết
 * sẽ tham chiếu đến khoá chính trong bảng my_address.
 * 
 * + referencedColumnName: Xác định tên của cột trong bảng mà cột khoá ngoại của
 * đối tượng hiện tại sẽ tham chiếu tới.
 */
@Data
@Entity
@Table(name = "user_jt")
public class UserJT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable( //
		name = "user_address", //
		// user_address(user_id) = user(id)
		joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, //
		// user_address(address_id) = address(id)
		inverseJoinColumns = { @JoinColumn(name = "address_id", referencedColumnName = "id") } //
	)
	private AddressJT address;

}
