package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

/**
 * Multiple condition
 */
@Configuration
@ConditionalOnExpression("${loda.expression1.enabled:true} and ${loda.expression2.enabled:true}")
public class ForMultipleExpression {

}
