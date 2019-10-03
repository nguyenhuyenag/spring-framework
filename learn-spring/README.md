
# Tight-coupling (liên kết ràng buộc) & loosely coupled

	- Các class không nên phụ thuộc vào các kế thừa cấp thấp, mà nên phụ thuộc vào abstraction (lớp trừu tượng)

# DI (Dependency Injection)

	- Là việc các object nên phụ thuộc vào các abstract class và thể hiện chi tiết của nó sẽ được inject vào đối tượng lúc runtime
	
#
	
	ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

# @Component & @Autowired

	- @Component Là một annotation đánh dấu trên class để giúp Spring biết nó là một Bean (hoặc dependency) và sẽ được Spring quản lý.
  
	- Spring Boot sẽ dò tìm toàn bộ các class cùng cấp hoặc trong các package thấp hơn so với class App (đánh dấu @SpringBootApplication). Khi gặp một class được đánh dấu @Component thì nó sẽ tạo ra một instance và đưa vào ApplicationContext để quản lý
  
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
	
	- @Autowired
