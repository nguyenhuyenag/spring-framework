package dev.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnClass -> Thỏa mãn nếu trong classpath tồn tại CLASS yêu cầu
 */
@Configuration
@ConditionalOnClass(name = "dev.boot.configuration.AppConfig")
public class IfClassExist {

}
