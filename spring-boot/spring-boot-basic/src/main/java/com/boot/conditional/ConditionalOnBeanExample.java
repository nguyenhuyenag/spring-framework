package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * - Bean được đánh dấu @ConditionalOnBean(MyBean.class) chỉ được tạo khi MyBean
 * tồn tại trong Context.
 * 
 * - Khi get bean ContextUtils.getBean(ABeanWithCondition.class); -> A component
 * required a bean of type '...ABeanWithCondition' that could not be found.
 * 
 * - Nếu gắn nó trên một @Configuration thì toàn bộ các @Bean bên trong class sẽ
 * chịu tác động
 */
@Configuration
public class ConditionalOnBeanExample {

	public static class MyBean {

	}

	public static class ABeanWithCondition {

	}

	@Bean // <- Đóng/mở để thấy sự khác biệt
	public MyBean MyBean() {
		return new MyBean();
	}

	@Bean
	@ConditionalOnBean(MyBean.class)
	public ABeanWithCondition beanWithCondition() {
		return new ABeanWithCondition();
	}

}
