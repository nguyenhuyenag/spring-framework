# POJO (Plain Old Java Object)

	- POJO là những class thuần túy, không triển khai bất kì framework hay technology nào
	
# JavaBean

	- Là POJO có ít nhất default constructor & implements Serializable
	
#  @Entity & @Table

	- Đánh dấu 1 JavaBean trở thành một entity
	
	- Nếu không sử dụng @Table trong entity thì mặc định tên bảng trong database sẽ là tên lớp của entity
	
# @Id

	- Đánh dấu thuộc tính primary key của entity tương ứng với cột primary key trong bảng
	
	- Một entity bắt buộc phải có ít nhất một thuộc tính primary key đi kèm với @Id

# @GeneratedValue

	- strategy = GenerationType.(TABLE, SEQUENCE, AUTO, IDENTITY)
	
# @Column
	
	- columnDefinition: Ví dụ
	
		@Column(name = "name", columnDefinition = "VARCHAR(4) NOT NULL")
		private String className;
		
		=> Khi dùng JPA tool để tạo bảng sẽ nhận được: `name` VARCHAR(4) NOT NULL
	
	- insertable, updatable
	
	- length
	
	- nullable
	
	- unique
	
# Composite Primary Key

	- Bảng có nhiều khóa chính
	
	- @Embeddable: Nếu dùng @EmbeddedId => thì không dùng thể @Id
	
	- @IdClass

	