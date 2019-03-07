package com.voyvas.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.voyvas.config.DatabaseConfig;
import com.voyvas.config.SecurityConfig;

/**
 * 
 * @author javier soler
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityScan("com.voyvas.user.model")
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.voyvas.user.model.repository", "com.voyvas.user.endpoint" })
@Import({ DatabaseConfig.class, SecurityConfig.class })
public @interface EnableVoyVasCommon {

}
