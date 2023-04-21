package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnMissingClass -> ngược lại với @ConditionalOnClass
 */
@Configuration
@ConditionalOnMissingClass(value = "com.me.spring.LodaHandsome")
class ForClassMissing {

}
