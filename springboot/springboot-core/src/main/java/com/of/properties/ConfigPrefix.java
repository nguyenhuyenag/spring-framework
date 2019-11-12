package com.of.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Những properties nào bắt đầu là “config” và phần còn lại trùng với tên thuộc
 * tính trong class Configs sẽ được map giá trị. Phần còn lại của những
 * properties này không phân biệt hoa, thường, gạch dưới hay gạch ngang
 */
@Getter
@Setter
@Component
@ConfigurationProperties("config") // <- prefix
@PropertySource("classpath:config.properties")
public class ConfigPrefix {

	private String url;
	private List<String> version;

}
