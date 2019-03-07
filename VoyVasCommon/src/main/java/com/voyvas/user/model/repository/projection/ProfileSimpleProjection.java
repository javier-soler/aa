package com.voyvas.user.model.repository.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.voyvas.user.model.Profile;

@Projection(name = "simpleProfile", types = Profile.class)
public interface ProfileSimpleProjection {
	@Value("#{target.id}")
	Long getId();

	String getName();
}
