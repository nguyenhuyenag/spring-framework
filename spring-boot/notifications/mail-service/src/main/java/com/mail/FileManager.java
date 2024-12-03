package com.mail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager {

    public static String getFileFromResource(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        if (!resource.exists()) {
            log.info("File '{}' not found!", fileName);
        } else {
            try (InputStream inputStream = resource.getInputStream()) {
                byte[] bytes = StreamUtils.copyToByteArray(inputStream);
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.error("Error reading file '{}': {}", fileName, e.getMessage(), e);
            }
        }
        return "";
    }

}
