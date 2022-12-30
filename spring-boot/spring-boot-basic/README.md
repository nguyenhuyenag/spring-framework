# Spring Boot
	
# @Component

	- Là một annotation đánh dấu trên class để Spring biết nó là Bean (hoặc dependency) và được Spring IoC container quản lý.

# @Scope

	- Bean với SINGLETON scope sẽ được IoC container khởi tạo duy nhất một instance sử dụng trong tất cả các yêu cầu sau này
	
	- Bean với PROTOTYPE scope sẽ trả về các instance khác nhau mỗi khi có một yêu mới sử dụng chúng đến IoC container.
  
	- Các Bean mặc định trong Context đều là singleton
	
			@Component
			@Scope("prototype")
			public class User {
			
			}
		
	- Scope trong Web application

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

 @Component và @Bean
 
 	- Bean được đánh dấu trên phương thức, còn @Component được dánh dấu trên class
 	
# @Configuration và @Bean

	- @Bean đánh dấu trên method, hàm này trả về một object và được đăng ký, quản lý bởi Spring IoC container.

	- @Configuration đánh dấu trên class để Spring biết đây là nơi định nghĩa ra các Bean.

	- @Bean sẽ nằm trong các class có đánh dấu @Configuration
	
	- Nếu method được đánh dấu bởi @Bean có tham số, thì Spring Boot sẽ tự inject các Bean đã có trong Context vào làm tham số.
	
	- Về bản chất, @Configuration cũng là @Component

# @DependsOn

	- Chúng ta có thể sử dụng annotation này trên một bean để thông báo với Spring rằng phải khởi tạo một bean khác trước khi khởi tạo nó với bean name được chỉ định trong @DependsOn.

	@Bean
	@DependsOn("fuel")
	Engine engine() {
	    return new Engine();
	}
	
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
	
# PUT & POST

	- POST method được dùng để gửi các request kèm theo một entity đến server yêu cầu tạo một tài nguyên mới dựa trên entity được cung cấp.

	- PUT method nên kèm theo một định danh (thường là ID) cùng với một entity. Nếu một tài nguyên được tìm thấy bởi mã định danh kèm theo thì tài nguyên này sẽ được thay thế bởi các giá trị trong entity kèm theo. Ngược lại, PUT method sẽ tạo một tài nguyên dựa trên entity đã cung cấp.

	- Một điểm khác biệt quan trọng giữa POST và PUT đó là nếu chúng ta gọi PUT method nhiều lần thì sẽ nó tạo hoặc cập nhật cùng một tài nguyên (dựa vào mã định danh). Còn với POST thì việc chúng ta thực thi nhiều lần với cùng một enity thì nó sẽ tạo ra nhiều tài nguyên ở phía server có giá trị tương tự nhau.

