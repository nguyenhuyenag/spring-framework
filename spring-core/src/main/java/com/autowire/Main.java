package com.autowire;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*-
 * Các loại Autowiring trong Spring
 * 
 * - Auto-wiring ‘no’: Nối bean thông qua thuộc tính ref="id" (đây là chế độ mặc định)
 * 
 * 		<bean id="person">
 * 			<property name="address" ref="address"></property>
 * 		</bean>
 * 		<bean id="address"></bean>
 * 
 * - Auto-wiring ‘byName’: Spring sẽ tìm bean có id trùng với attribute Address trong
 *   class Person và autowired thông qua setter setAddress(...)
 * 
 * 		<bean id="person" autowire="byName"></bean>
 * 		<bean id="address"></bean>
 * 
 * - Auto-wiring ‘byType’: Spring sẽ tìm bean có type là Address và autowired thông qua setter setAddress()
 * 
 * 		<bean id="person" autowire="byName"></bean>
 * 		<bean class="com.autowire.Address"></bean>
 * 
 * - Auto-Wiring ‘constructor’: Autowired thông qua constructor => Person(Address address)
 * 
 * - Auto-Wiring ‘autodetect’: Không hỗ trợ từ Spring 3+
 */
public class Main {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("autowire/context.xml");) {
			Person person = (Person) context.getBean("person");
			person.print();
		}
	}

}
