package com.voyvas.user.model.repository.projection;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.voyvas.user.model.Account;
import com.voyvas.user.model.Profile;

@Projection(name = "simpleAccount", types = Account.class)
public interface AccountSimpleProjection {
	@Value("#{target.id}")
	Long getId();

	Long getTenantId();
	
	Long getProfileId();
	
	String getUserName();

	String getFirstName();

	String getLastName();

	String getEmail();

	Integer getAge();

	Date getCreatedAt();

	ProfileSimpleProjection getProfile();

}
