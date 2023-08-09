package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

/**
 * Multiple condition
 */
@Configuration
// @ConditionalOnExpression("NOT ${spring.kafka.use-ssl}")
// @ConditionalOnExpression("#{'${spring.kafka.use-ssl}' == 'false'}")
// @ConditionalOnExpression("${loda.expression1.enabled:true} and ${loda.expression2.enabled:true}")
@ConditionalOnExpression("${properties.first.property.enable:true} && ${properties.second.property.startServer:false}")
public class MultipleExpression {

}
