package com.boot.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ResourceUtils {

    public static String getResourcePath(String resourcePath) {
        if (resourcePath == null || resourcePath.trim().isEmpty()) {
            return null;
        }

        URL resourceUrl = ResourceLoader.class.getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            System.err.println("Resource not found: " + resourcePath);
            return null;
        }

        // Handle URL encoding and special characters
        String decodedPath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8);

        // Remove leading "/" on Windows (file:/C:/path -> C:/path)
        if (decodedPath.startsWith("/") && System.getProperty("os.name").contains("Windows")) {
            decodedPath = decodedPath.substring(1);
        }

        return decodedPath;
    }

    // rootCA = Thư mục chứa các file .p7b trong resources/rootCA
    public static void getAllStructCertCAFromResources(String rootCA) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            // Tìm tất cả file .p7b trong thư mục chỉ định
            Resource[] resources = resolver.getResources("classpath:" + rootCA + "/*.p7b");

            if (resources.length == 0) {
                log.warn("No .p7b certificates found in directory: {}", rootCA);
                return;
            }

            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try (InputStream is = resource.getInputStream()) {
                        // Todo: Chuyển đổi InputStream thành đối tượng cần thiết
                    } catch (Exception e) {
                        log.error("Error processing file: {}", resource.getFilename(), e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error accessing certificates directory: {}", rootCA, e);
        }
    }

}
