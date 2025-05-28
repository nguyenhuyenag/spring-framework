package dev.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnMissingClass -> Ngược lại với @ConditionalOnClass
 */
@Configuration
@ConditionalOnMissingClass(value = "dev.boot.configuration.AppConfig")
class IfClassMissing {

}
