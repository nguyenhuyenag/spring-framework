package dev.boot.component.life.cycle;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * ((ConfigurableApplicationContext) ctx).getBeanFactory().destroyBean(bean);
 */
@Component
public class BeanLifeCycle {

	@PostConstruct
	// IoC Container hoặc ApplicationContext sẽ gọi hàm này sau khi Bean được tạo
	public void postConstruct() {
		System.out.println(new Date() + " [BeanLifeCycle] Call PostConstruct");
	}

	@PreDestroy // hàm sẽ gọi trước khi một Bean bị xóa (hoặc tắt server)
	public void preDestroy() {
		System.out.println(new Date() + " [BeanLifeCycle] Call PreDestroy");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println(new Date() + " [BeanLifeCycle] AfterStartup");
	}

}
