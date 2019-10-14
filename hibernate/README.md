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
	
# Composite Primary Key (Bảng có nhiều khóa chính)
	
	- @Embeddable
			+ Tạo class chứa các khóa chính, đánh dấu @Embeddable
			+ Nếu dùng @EmbeddedId => thì không dùng thể @Id
			+ Khi khai báo id là 1 class ta dùng annotation @EmbeddedId
	
	- @IdClass

# @OneToMany & @ManyToOne
	
	class Company {
		Integer id;
		
		@OneToMany(mappedBy = "company")
		private List<Staff> listStaff;
	}
	
	class Staff {
		@ManyToOne
		@JoinColumn(name = "company_id") // optional
		private Company company;
	}

	- @OneToMany: Một đối tượng Company có thể chứa nhiều đối tượng Staff (Collection)
	- mappedBy = "company" hiểu là mapping thông qua thuộc tính company trong Staff
	
	- @ManyToOne: Nhiều đối tượng Staff sẽ cùng thuộc một đối tượng Company
	- @JoinColumn: Chỉ rõ mapping qua cột company_id trong table staff, quy tắc tự động
		
		TenClassChuKhoaChinh_TenKhoaChinh	=> Ví dụ `company_id`
	

	