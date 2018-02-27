package com.tundra.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.tundra.security.AdminSecurityAspect;
import com.tundra.security.PublicSecurityAspect;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.tundra" })
@EnableAspectJAutoProxy
public class DispatcherConfig implements WebMvcConfigurer {
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true).favorParameter(true).parameterName("format").ignoreAcceptHeader(true)
				.useRegisteredExtensionsOnly(true).mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML)
				.defaultContentType(MediaType.APPLICATION_JSON);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public PublicSecurityAspect publicSecurityAspect() {
		return new PublicSecurityAspect();
	}

	@Bean
	public AdminSecurityAspect adminSecurityAspect() {
		return new AdminSecurityAspect();
	}
}
