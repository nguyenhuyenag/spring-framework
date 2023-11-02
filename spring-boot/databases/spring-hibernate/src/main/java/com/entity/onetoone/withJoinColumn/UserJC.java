package com.entity.onetoone.withJoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * - Hoặc là with Foreign Key
 * 
 * - Cách quan hệ 1 - 1 trong cơ sở dữ liệu biểu thị rằng một thực thể A tương
 * ứng với một thực thể B và ngược lại. Ví dụ một người sẽ có một địa chỉ duy
 * nhất (giả sử).
 * 
 * - Khi tạo table trong DB để biểu thị mối quan hệ này, thì sẽ có một bảng chứa
 * khóa ngoại của bảng còn lại.
 * 
 * - Trong mối quan hệ hai chiều (bi-directional), ta dùng chú thích @OneToOne ở
 * cả 2 entity nhưng chỉ một entity là chủ sở hữu (owner) của mối quan hệ. Thông
 * thường, entity con là chủ sở hữu của mối quan hệ và thực thể cha là bên
 * nghịch đảo của mối quan hệ. Chủ sở hữu của mối quan hệ (MyUser) chứa chú
 * thích @JoinColumn để chỉ ra cột nào là khóa ngoại và entity nào là nghịch đảo
 * của mối quan hệ (MyAddress). Entity nghịch đảo này sẽ chứa thuộc tính
 * mappedBy để chỉ ra rằng mối quan hệ được ánh xạ bởi thực thể còn lại.
 * 
 * + CascadeType.ALL: Tất cả các hoạt động (CREATE, READ, UPDATE, DELETE) trên
 * đối tượng chính cũng sẽ được áp dụng cho đối tượng liên quan
 * 
 * + CascadeType.PERSIST: Áp dụng cho hoạt động tạo mới (INSERT). Nó đảm bảo
 * rằng khi ta tạo một đối tượng gốc, đối tượng liên quan cũng sẽ được tạo nếu
 * chưa tồn tại.
 */
@Data
@Entity
@Table(name = "user_jc")
public class UserJC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	/**
	 * name = "address_id" là tên cột trong DB 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressJC address;

}
