package dev.boot.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/*
    Khi build thành gói jar, cách đọc file bằng cách getFile() để lấy đường dẫn file sẽ không hoạt động, do đó phải sử dụng InputStream.
 */
@Slf4j
public class ResourceFile {

    public static String getFileFromResource(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        if (!resource.exists()) {
            log.info("File '{}' not found!", fileName);
        } else {
            // Cách này tốt hơn cách dùng resource.getFile(), vì resource.getFile() sẽ bị
            // lỗi với các file nén thành JAR.
            try (InputStream inputStream = resource.getInputStream()) {
                // byte[] bytes = IOUtils.toByteArray(inputStream);
                byte[] bytes = StreamUtils.copyToByteArray(inputStream);
                // byte[] bytes = inputStream.readAllBytes(); // Java 9
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.error("Error reading file '{}': {}", fileName, e.getMessage(), e);
            }
        }
        return "";
    }

    // Read file from `resources`
    public static void readAsProperties() {
        try {
            PropertiesFactoryBean factory = new PropertiesFactoryBean();
            factory.setLocation(new ClassPathResource("basic.properties"));
            factory.afterPropertiesSet();
            Properties properties = factory.getObject();
            if (Objects.nonNull(properties)) {
                properties.forEach((k, v) -> System.out.println(k + " -> " + v));
            }
        } catch (IOException e) {
            log.error("Error: {}", e.getMessage());
        }
    }

    public static void test() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
    }

    public static void main(String[] args) {
        test();
    }

}
