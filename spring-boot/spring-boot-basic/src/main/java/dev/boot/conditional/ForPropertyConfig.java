package dev.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ABeanWithCondition2 {

}

@Configuration
public class ForPropertyConfig {

	/*
	 * @ConditionalOnProperty giúp gắn điều kiện cho @Bean dựa theo property trong
	 * config
	 */
	@Bean
	@ConditionalOnProperty( //
		value = "conditional.on.property.enabled", //
		havingValue = "true",	// Nếu giá trị conditional.on.property.enabled = true thì Bean mới được khởi tạo
		matchIfMissing = false 	// giá trị mặc định nếu không tìm thấy property conditional.on.property.enabled
	)
	public ABeanWithCondition2 aBeanWithCondition2() {
		return new ABeanWithCondition2();
	}

}
