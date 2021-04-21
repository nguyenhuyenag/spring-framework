# Spring Boot
	
# @Component

	- Là một annotation đánh dấu trên class để Spring biết nó là Bean (hoặc dependency) và được Spring IoC container quản lý.

# @Scope
  
	- Các Bean trong Context đều là singleton. Nếu muốn mỗi lần sử dụng là một instance mới thì đánh dấu Bean đó bằng @Scope("prototype")
	
		@Component
		@Scope("prototype")
		public class User {
		
		}
		
	- Cacs Scope trong spring: Singleton, Prototype, Request, Session, Global-Session.

# @Autowired

	- Đánh dấu trên object, thứ tự ưu tiên khi inject bean: Constructor > Setter > Reflection.
	
	- @Autowired(required = true): Nếu spring không tìm thấy bean để inject thì nó sẽ báo lỗi (mặc định).
	
	- @Autowired(required = false): Nếu spring không tìm thấy bean để inject thì nó sẽ inject null.
	
	- Không thể Autowire với các dữ liệu nguyên thủy.

	- Vấn đề của @Autowired

		+ Khi có 2 Bean cùng loại, sẽ xuất hiện lỗi
		
			=> “Field ... in ... required a single bean, but 2 were found”.
		
		+ Sử dụng @Primary hoặc @Qualifier
	
# @PostConstruct & @PreDestroy

	- @PostConstruct: Đánh dấu trên method bên trong Bean và method này sẽ được gọi sau khi Bean đó được khởi tạo.
	
	- @PreDestroy: Tương tự, method này sẽ được gọi sau khi Bean bị xóa.
	
	- Bean life cycle: https://i.imgur.com/5SxxUQ0.jpg

# 3Layer: Presentation Layers, Busines Logic Layers, Data Access Layers

	- @Controller (Presentation)

	- @Service (Busines Logic): Đánh dấu một class là tầng Service, phục vụ các logic nghiệp vụ.
	
	- @Repository (Data Access): Đánh dấu một class đảm nhiệm giao tiếp với database.
	
	- Bản chất cả 3 đều là @Component và có thể thay thế cho nhau.

# @Configuration và @Bean

	- @Bean đánh dấu trên method, hàm này trả về một object và được đăng ký, quản lý bởi Spring IoC container.

	- @Configuration đánh dấu trên class để Spring biết đây là nơi định nghĩa ra các Bean.

	- @Bean sẽ nằm trong các class có đánh dấu @Configuration
	
	- Nếu method được đánh dấu bởi @Bean có tham số, thì Spring Boot sẽ tự inject các Bean đã có trong Context vào làm tham số.
	
	- Về bản chất, @Configuration cũng là @Component
	
# Khi Spring Boot chạy
		
	> Tìm các class có đánh dấu @Component (cùng cấp hoặc trong package thấp hơn với class Application)
		
		> Tạo 1 instance của class và đưa vào ApplicationContext để Spring quản lý
	
	> Tìm các class có đánh dấu @Configuration

		> Tạo 1 instance của class này

		> Tìm & gọi các method có đánh dấu @Bean để lấy ra các Bean và đưa vào Context

# Spring JPA

	find...By, read...By, query...By, count..By, và get..By

# @RestController & @Controller

	@RestController = @Controller + @ResponseBody
