
# Tight coupling (liên kết ràng buộc) & Loosely coupled

	- Các class không nên phụ thuộc vào các kế thừa cấp thấp, mà nên phụ thuộc vào abstract class

# DI (Dependency Injection)

	- Các object nên phụ thuộc vào các abstract class và thể hiện chi tiết của nó sẽ được inject vào đối tượng lúc runtime
	
# @Component

	- Là 1 annotation đánh dấu trên CLASS để Spring biết nó là 1 Bean (hoặc dependency) và được Spring quản lý
  
	- Các Bean trong Context đều là singleton. Trong trường hợp muốn mỗi lần sử dụng là một instance mới thì đánh dấu Bean đó bằng @Scope("prototype")
	
	@Component
	@Scope("prototype")
	public class User {
	
	}

# @Autowired

	- Đánh dấu trên OBJECT
	
	- Thứ tự ưu tiên khi inject bean: Constructor > Setter > Reflection
	
	- Thuộc tính required của annotation @Autowired mặc định là true.
	
	- @Autowired(required = true): Nếu spring không tìm thấy bean để inject thì nó sẽ báo lỗi (mặc định)
	
	- @Autowired(required = false): Nếu spring không tìm thấy bean để inject thì nó sẽ inject null
	
	- Không thể Autowire với các dữ liệu nguyên thủy

# Vấn đề của @Autowired

	- Trường hợp dùng @Autowired khi Spring Boot có 2 Bean cùng loại, khi đó sẽ xuất hiện lỗi
	
		=> “Field ... in ... required a single bean, but 2 were found”
	
	=> @Primary: Ưu tiên chọn Bean được đánh dấu nếu có bean cũng loại
	
	=> @Qualifier: Xác định tên của Bean muốn chỉ định inject
	
	- Xem: learn.of.component.autowired
	
# @PostConstruct & @PreDestroy

	- @PostConstruct đánh dấu trên METHOD bên trong Bean và method này sẽ được gọi sau khi Bean đó được khởi tạo
	
	- @PreDestroy đánh dấu trên METHOD bên trong Bean và method này sẽ được gọi sau khi Bean bị xóa
	
	- Bean life cycle: https://i.imgur.com/5SxxUQ0.jpg

# @Service & @Repository (MVC)

	- @Service đánh dấu một CLASS là tầng Service, phục vụ các logic nghiệp vụ
	
	- @Repository đánh dấu một CLASS đảm nhiệm giao tiếp với database
	
	- Bản chất @Service & @Repository cũng chính là @Component và có thể thay thế cho nhau

# @Bean & @Configuration

	- @Bean đánh dấu trên METHOD để Spring Boot biết đây là Bean và sẽ đưa Bean này vào Context

	- @Configuration đánh dấu trên CLASS để Spring Boot biết đây là nơi định nghĩa ra các Bean

	- @Bean sẽ nằm trong các class có đánh dấu @Configuration
	
	- Về bản chất, @Configuration cũng là @Component
	
# Khi Spring Boot chạy
		
	> Tìm các class có đánh dấu @Component (cùng cấp hoặc trong package thấp hơn với class Application)
		
		> Tạo 1 instance của class và đưa vào ApplicationContext để Spring quản lý
	
	> Tìm các class có đánh dấu @Configuration

		> Tạo 1 instance của class này

		> Tìm & gọi các method có đánh dấu @Bean để lấy ra các Bean và đưa vào Context
# Spring JPA

	find...By, read...By, query...By, count..By, và get..By
