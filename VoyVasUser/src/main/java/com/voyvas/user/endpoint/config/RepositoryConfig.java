package com.voyvas.user.endpoint.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.Authority;
import com.voyvas.user.model.Profile;
import com.voyvas.user.model.Tenant;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Profile.class, Authority.class, Account.class, Tenant.class);
	}

}
