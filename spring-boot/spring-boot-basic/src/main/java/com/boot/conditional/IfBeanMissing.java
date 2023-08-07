package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

class SomeMissingBean {

}

@Configuration
public class IfBeanMissing {

	/**
	 * Nếu trong Context chưa tồn tại một SomeMissingBean (hoặc cùng loại) nào thì
	 * bean ở dưới đây mới được tạo
	 */
	@ConditionalOnMissingBean
	public SomeMissingBean someMissingBean() {
		return new SomeMissingBean();
	}

}
