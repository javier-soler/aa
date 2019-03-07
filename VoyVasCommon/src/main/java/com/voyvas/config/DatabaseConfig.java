package com.voyvas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.voyvas.user.model.repository.AccountHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	@Bean
	@Primary
	@ConditionalOnClass(name = "com.mysql.jdbc.Driver")
	public DataSource mainMysqlDs(
			@Value("${voyvas.main-db.url}") String url,
			@Value("${voyvas.main-db.user}") String user,
			@Value("${voyvas.main-db.password}") String password) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(user);
		config.setPassword(password);

		// logger.log(Level.INFO, "Configuration Obeject: {0}", configObj);
		return new HikariDataSource(config);
	}

	@Bean
	@Qualifier("token_store")
	@ConditionalOnClass(name = "com.mysql.jdbc.Driver")
	public DataSource tokenStoreMysqlDs() {
		// fixme, use properties for this and everything in the app that requires it
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/token_store?useLegacyDatetimeCode=false&serverTimezone=UTC");
		config.setUsername("voyvas");
		config.setPassword("1qazxsw2");

		// logger.log(Level.INFO, "Configuration Obeject: {0}", configObj);
		return new HikariDataSource(config);
	}

	@Bean
	@ConditionalOnClass(name = "org.h2.Driver")
	public DataSource dataSource2() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:file:C:\\Users\\jsole\\H2\\xx");
		config.setUsername("sa");
		config.setPassword("");
		// logger.log(Level.INFO, "Configuration Obeject: {0}", configObj);
		return new HikariDataSource(config);
	}

	@Bean
	public AccountHandler accountHandler() {
		return new AccountHandler();
	}

}
