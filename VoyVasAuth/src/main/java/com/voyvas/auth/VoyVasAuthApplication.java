package com.voyvas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.voyvas.annotation.EnableVoyVasCommon;
import com.voyvas.config.dev.DevInitializer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableVoyVasCommon
@Import({ DevInitializer.class })
public class VoyVasAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoyVasAuthApplication.class, args);
	}

	@EnableWebMvc
	@Configuration
	class MvcConfig implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login123").setViewName("login");
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		}
	}

}
