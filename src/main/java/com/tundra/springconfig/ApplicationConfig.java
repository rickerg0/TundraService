package com.tundra.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tundra.controller.interceptor.AdminAuthenticationInterceptor;
import com.tundra.controller.interceptor.PublicAuthenticationInterceptor;

@Configuration
@ComponentScan("com.tundra")
@PropertySource("classpath:tundra.properties")
@Import({DispatcherConfig.class, DatabaseConfig.class})
public class ApplicationConfig implements WebMvcConfigurer {
	
	// REST interceptors
    
	@Autowired
    private PublicAuthenticationInterceptor publicAuthenticationInterceptor;
	
	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;

	 @Override
    public void addInterceptors(InterceptorRegistry registry){
		 
//        registry.addInterceptor(publicAuthenticationInterceptor)
//        	.addPathPatterns("/**")
//        	.excludePathPatterns("/admin/**")
//        	.excludePathPatterns("/login/**")
//        	.excludePathPatterns("/register/**");
//
//        registry.addInterceptor(adminAuthenticationInterceptor)
//	    	.addPathPatterns("/admin/**")
//	    	.excludePathPatterns("/admin/login/**");
        
	 }

}
