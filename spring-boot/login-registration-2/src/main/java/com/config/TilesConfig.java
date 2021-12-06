package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {

	@Bean
	// @Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		// TilesView 3
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean
	// @Bean(name = "tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		// TilesView 3
		// String[] defs = { "/WEB-INF/tiles.xml" };
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
		return tilesConfigurer;
	}

}
