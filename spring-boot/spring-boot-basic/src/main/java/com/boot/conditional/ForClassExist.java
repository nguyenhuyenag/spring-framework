package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnClass -> thỏa mãn nếu trong classpath có tồn tại class mà bạn
 *                     yêu cầu
 */
@Configuration
@ConditionalOnClass(name = "com.me.spring.LodaHandsome")
public class ForClassExist {

}
