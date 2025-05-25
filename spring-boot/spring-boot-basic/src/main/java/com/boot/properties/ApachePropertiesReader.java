package com.boot.properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationConverter;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/*
    Cách đọc file properties thông thường (ReadPropertiesFileManual) sẽ không hỗ trợ cách cấu hình:

        dev.base.url=https://devjava.com
        dev.contact.url=${dev.base.url}/vi/lien-he
        dev.introduction.url=${dev.base.url}/vi/gioi-thieu

    Do đó ta cần sử dụng cách đọc khác
 */
public class ApachePropertiesReader {

    public static void main(String[] args) throws ConfigurationException {
        Path path = Paths.get("src", "main", "resources", "apache-config.properties");

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder
                = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class);

        Parameters parameters = new Parameters();
        builder.configure(parameters.properties().setFile(path.toFile()));

        Configuration configuration = builder.getConfiguration();
        Properties properties = ConfigurationConverter.getProperties(configuration);

        properties.forEach((key, value) -> System.out.println(key + " = " + value));
    }

}
