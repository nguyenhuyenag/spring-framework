package com.ioc.beanfactory;

/**
 * 
 * - 5 scope của Spring Bean
 * 
 * - Singleton: Chỉ duy nhất một thể hiện của bean sẽ được tạo cho mỗi
 * container. Đây là scope mặc định, khi sử dụng scope này cần chắc chắn rằng
 * các bean không có các biến/thuộc tính được share.
 * 
 * - Prototype: Một thể hiện của bean sẽ được tạo cho mỗi lần request.
 * 
 * - Request: Giống prototype, nhưng dùng cho ứng dụng web, một thể hiện của
 * bean sẽ được tạo cho mỗi HTTP request.
 * 
 * - Session: Mỗi thể hiện của bean sẽ được tạo cho mỗi HTTP Session.
 * 
 * - Global-Session: Được sử dụng để tạo global sesion bean cho các ứng dụng
 * Portlet.
 *
 */
public class HelloWorld {

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Print : " + message);
	}

}
