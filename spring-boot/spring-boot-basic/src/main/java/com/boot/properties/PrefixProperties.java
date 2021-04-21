//package com.boot.properties;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
///**
// * Những thuộc tính bắt đầu là "config" và phần còn lại trùng với tên thuộc tính
// * trong class sẽ được map giá trị. Phần còn lại của những properties này không
// * phân biệt hoa, thường, gạch dưới hay gạch ngang
// */
//// @Component
//@ConfigurationProperties("pre") // <- prefix
//@PropertySource("classpath:prefix.properties")
//public class PrefixProperties {
//
//	public static String PORT;
//	public static String URL;
//
//	@Value("${port}")
//	// @Value("${pre.port}")
//	public void setPort(String port) {
//		PrefixProperties.PORT = port;
//	}
//
//	@Value("${url}")
//	// @Value("${pre.url}")
//	public void setUrl(String url) {
//		PrefixProperties.URL = url;
//	}
//
//}
