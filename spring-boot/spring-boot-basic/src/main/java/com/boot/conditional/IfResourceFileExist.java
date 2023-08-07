package com.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;

/**
 * Bean sẽ được khởi tạo nếu tồn tại `application.properties` trong resources
 */
@Configuration
@ConditionalOnResource(resources = "/application.properties")
public class IfResourceFileExist {

}
