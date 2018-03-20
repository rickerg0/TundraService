package com.tundra.springconfig;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.tundra")
@PropertySource("classpath:tundra.properties")
@Import({DispatcherConfig.class, DatabaseConfig.class, SecurityConfig.class})
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
	    MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
	    bean.setTargetClass(SecurityContextHolder.class);
	    bean.setTargetMethod("setStrategyName");
	    bean.setArguments(new Object[]{SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
	    return bean;
	}
}
