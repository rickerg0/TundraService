package com.tundra.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.tundra")
@PropertySource("classpath:tundra.properties")
@Import({DispatcherConfig.class, DatabaseConfig.class})
public class ApplicationConfig implements WebMvcConfigurer {
	

}
