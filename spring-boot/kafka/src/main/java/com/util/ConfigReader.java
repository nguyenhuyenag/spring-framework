package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:kafka-config.properties", "classpath:config.properties", })
public class ConfigReader {

	public static int JOB_TIME_PUT_HOADON;

	@Value("${JOB_TIME_PUT_HOADON}")
	private void setJOB_TIME_PUT_HOADON(int time) {
		ConfigReader.JOB_TIME_PUT_HOADON = time;
	}

}
