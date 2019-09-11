package com.boot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:log4j.properties")
public class Log4jProperties {

	private String log4jRootLogger;

	private String log4jAppenderA1Layout;

	@Value("${log4j.rootLogger}")
	private void setLog4jRootLogger(String log4jRootLogger) {
		this.log4jRootLogger = log4jRootLogger;
	}

	@Value("${log4j.appender.A1.layout}")
	private void setLog4jAppenderA1Layout(String log4jAppenderA1Layout) {
		this.log4jAppenderA1Layout = log4jAppenderA1Layout;
	}

	public String getLog4jAppenderA1Layout() {
		return log4jAppenderA1Layout;
	}

	public String getLog4jRootLogger() {
		return log4jRootLogger;
	}

}
