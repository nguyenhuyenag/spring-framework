
# Tight-coupling (liên kết ràng buộc) & loosely coupled

	- Các class không nên phụ thuộc vào các kế thừa cấp thấp, mà nên phụ thuộc vào abstraction (lớp trừu tượng)

# DI (Dependency Injection)

	- Là việc các object nên phụ thuộc vào các abstract class và thể hiện chi tiết của nó sẽ được inject vào đối tượng lúc runtime
	
# @Component

	- Là một annotation đánh dấu trên class giúp Spring biết nó là một Bean (hoặc dependency) và sẽ được Spring quản lý.
  
	- Spring Boot sẽ dò tìm toàn bộ các class cùng cấp hoặc các package thấp hơn so với class Application. Khi gặp một class được đánh dấu @Component thì nó sẽ tạo ra một instance và đưa vào ApplicationContext để quản lý.
  
	- Các Bean trong ApplicationContext đều là singleton. Trong trường hợp muốn mỗi lần sử dụng là một instance hoàn toàn mới thì đánh dấu Bean đó bằng @Scope("prototype")
	
	@Component
	@Scope("prototype")
	public class User {
	
		@Autowired
	    Outfit outfit;
	
	    public Girl(Outfit outfit) {
	        this.outfit = outfit;
	    }
	}

# @Autowired

	- Đánh dấu rằng đối tượng này sẽ được init bởi bean container của Spring
	
	- Nếu có hàm Constructor thì sẽ inject Bean vào bởi tham số của hàm
	
	- Nếu có hàm Setter thì sẽ inject Bean vào bởi tham số của hàm
	
	- Nếu không sẽ sử dụng Java Reflection để đưa đối tượng vào thuộc tính được đánh dấu
	
	- Thuộc tính required của annotation @Autowired mặc định là true.
	
	@Autowired(required = true)
	- Nếu spring container không tìm thấy bean để inject vào thì nó sẽ báo lỗi
	
	@Autowired(required = false)
	- Nếu spring container không tìm thấy bean để inject vào thì nó sẽ inject null
	
	- Không thể thực hiện autowire với các dữ liệu nguyên thủy như int, String, ...

# Vấn đề của @Autowired

	- Thực tế, sẽ có trường hợp chúng ta sử dụng @Autowired khi Spring Boot có chứa 2 Bean cùng loại trong Context
	
	- Field ... in ... required a single bean, but 2 were found
	
	=> @Primary: Đánh dấu bean luôn được ưu tiên lựa chọn nếu có bean cũng loại
	
	=> @Qualifier: Xác định tên của một Bean mà bạn muốn chỉ định inject
	
# @PostConstruct & @PreDestroy

	- @PostConstruct được đánh dấu trên một method duy nhất bên trong Bean. IoC Container hoặc ApplicationContext sẽ gọi hàm này sau khi một Bean được tạo ra và quản lý
	
	- @PreDestroy được đánh dấu trên một method duy nhất bên trong Bean. IoC Container hoặc ApplicationContext sẽ gọi hàm này trước khi một Bean bị xóa hoặc không được quản lý nữa.
	
	- Bean life cycle: https://i.imgur.com/5SxxUQ0.jpg

# MVC

	- Controller: là tầng giao tiếp với bên ngoài và handler các request từ bên ngoài tới hệ thống.

	- Service Layer: Thực hiện các nghiệp vụ và xử lý logic
	- @Service Đánh dấu một Class là tầng Service, phục vụ các logic nghiệp vụ.

	- Repository Layer:: Chịu trách nhiệm giao tiếp với các DB, thiết bị lưu trữ, xử lý query và trả về các kiểu dữ liệu mà tầng Service yêu cầu.
	- @Repository Đánh dấu một Class Là tầng Repository, phục vụ truy xuất dữ liệu.
	
	- @Service gắn cho các Bean đảm nhiệm xử lý logic
	- @Repository gắn cho các Bean đảm nhiệm giao tiếp với DB
	- @Component gắn cho các Bean khác.
	
	- Về bản chất @Service và @Repository cũng chính là @Component và có thể thay thế nhau

# @Configuration & @Bean

	- @Bean là một annotation được đánh dấu trên các method cho phép Spring Boot biết được đây là Bean và sẽ thực hiện đưa Bean này vào Context.

	- @Configuration là một annotation đánh dấu trên một class cho phép Spring Boot biết được đây là nơi định nghĩa ra các Bean.

	- @Bean sẽ nằm trong các class có đánh dấu @Configuration
	
	- Spring Boot lần đầu khởi chạy, ngoài việc đi tìm các @Component thì nó còn làm một nhiệm vụ nữa là tìm các class @Configuration.

    - Đi tìm class có đánh dấu @Configuration
    - Tạo ra đối tượng từ class có đánh dấu @Configuration tìm các method có đánh dấu @Bean trong đối tượng vừa tạo
    - Thực hiện gọi các method có đánh dấu @Bean để lấy ra các Bean và đưa vào `Context.

	- Ngoài ra, về bản chất, @Configuration cũng là @Component. Nó chỉ khác ở ý nghĩa sử dụng. (Giống với việc class được đánh dấu @Service chỉ nên phục vụ logic vậy).
	
	
	