package com.entity.onetoone.withJoinColumn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * - Hay còn gọi với tên with Foreign Key
 * 
 * - Cách quan hệ 1 - 1 trong cơ sở dữ liệu biểu thị rằng một thực thể A tương
 * ứng với một thực thể B và ngược lại. Ví dụ một người sẽ có một địa chỉ duy
 * nhất (giả sử)
 * 
 * - Bình thường, khi tạo table trong csdl để biểu thị mối quan hệ này, thì sẽ
 * có một bảng chứa khóa ngoại của bảng còn lại
 */
@Entity
public class MyAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(mappedBy = "address")
	private MyUser user;

}
