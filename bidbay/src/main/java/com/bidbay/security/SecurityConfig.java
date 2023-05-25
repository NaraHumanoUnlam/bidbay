package com.bidbay.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/carrito").setViewName("carritoView");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/usuario/agregar").setViewName("register");
	}

}
