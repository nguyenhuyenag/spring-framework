package learn.of.component.scan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = { "learn.of.component.scan" })
public class ScanBasePackage {

	public static void call(String[] args) {

		ApplicationContext context = SpringApplication.run(ScanBasePackage.class, args);

		try {
			Car girl = context.getBean(Car.class);
			System.out.println("Bean: " + girl.toString());
		} catch (Exception e) {
			System.out.println("Bean Girl không tồn tại");
		}

		try {
			OtherCar otherGirl = context.getBean(OtherCar.class);
			if (otherGirl != null) {
				System.out.println("Bean: " + otherGirl.toString());
			}
		} catch (Exception e) {
			System.out.println("Bean Girl không tồn tại");
		}
	}

}
