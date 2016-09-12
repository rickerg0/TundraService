package com.tundra.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.tundra")

@Import({DispatcherConfig.class, DatabaseConfig.class})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
//	@Bean
//	public JsonpCallbackFilter jsonpCallbackFilter() { 
//		return new JsonpCallbackFilter();
//	}
	


}
