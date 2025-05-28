package dev.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnJava -> Thỏa mãn nếu môi trường chạy version Java đúng với điều
 *                    kiện
 *
 */
@Configuration
@ConditionalOnJava(JavaVersion.EIGHT)
class UsingJavaVersion {

}
