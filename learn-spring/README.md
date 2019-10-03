
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
	
	@Component("bikini")
	public class Bikini implements Outfit {
	    
	}
	
	@Component("naked")
	public class Naked implements Outfit {
	    
	}
	
	
	
	