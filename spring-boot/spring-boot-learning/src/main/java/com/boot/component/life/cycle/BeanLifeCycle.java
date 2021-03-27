package com.boot.component.life.cycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * ((ConfigurableApplicationContext) ctx).getBeanFactory().destroyBean(bean);
 */
@Component
public class BeanLifeCycle {

	@PostConstruct
	// IoC Container hoặc ApplicationContext sẽ gọi hàm này sau khi Bean được tạo
	public void postConstruct() {
		System.out.println("Call PostConstruct ...");
	}

	@PreDestroy // hàm sẽ gọi trước khi một Bean bị xóa (hoặc tắt server)
	public void preDestroy() {
		System.out.println("Call PreDestroy ...");
	}

}
