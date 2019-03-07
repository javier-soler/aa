package com.voyvas.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-20)
public class LoginConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 // @formatter:off
        http.httpBasic().disable()
            .formLogin().permitAll()
        .and()
            .requestMatchers().antMatchers("/", "/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
        .and()
            .authorizeRequests().anyRequest().authenticated();
        // @formatter:on
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}