package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * - Bean được đánh dấu @ConditionalOnBean(RandomBean.class) chỉ được tạo khi
 * RandomBean tồn tại trong Context.
 * 
 * - Khi get bean ContextUtils.getBean(ABeanWithCondition.class); -> A component
 * required a bean of type '...ABeanWithCondition' that could not be found.
 * 
 * - Nếu bạn gắn nó trên một @Configuration thì toàn bộ các @Bean bên trong sẽ
 * bị chịu tác động
 */
@Configuration
public class ConditionalOnBeanExample {

	public static class RandomBean {

	}

	public static class ABeanWithCondition {

	}

	@Bean // <- Đóng/mở để thấy sự khác biệt
	public RandomBean randomBean() {
		return new RandomBean();
	}

	@Bean
	@ConditionalOnBean(RandomBean.class)
	public ABeanWithCondition beanWithCondition() {
		return new ABeanWithCondition();
	}

}
