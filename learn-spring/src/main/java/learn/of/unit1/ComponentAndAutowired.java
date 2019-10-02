package learn.of.unit1;

/**
 * - @Component là một annotation đánh dấu trên các class để giúp Spring biết nó
 * là một Bean (hoặc dependency) và sẽ được Spring quản lý
 * 
 * - Spring Boot sẽ dò tìm toàn bộ các class cùng cấp hoặc ở trong các package
 * thấp hơn so với class App mà bạn cung cấp cho Spring. Trong quá trình dò tìm
 * này, khi gặp một class được đánh dấu @Component thì nó sẽ tạo ra một instance
 * và đưa vào ApplicationContext để quản lý.
 * 
 * - Các Bean được quản lý bên trong ApplicationContext đều là singleton. Trong
 * trường hợp muốn mỗi lần sử dụng là một instance hoàn toàn mới. Thì hãy đánh
 * dấu @Component đó bằng @Scope("prototype")
 */

public class ComponentAndAutowired {

}
