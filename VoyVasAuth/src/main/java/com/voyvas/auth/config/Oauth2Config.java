package com.voyvas.auth.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("token_store")
	private DataSource tsDs;

	@Autowired
	private PasswordEncoder pe;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// fixme temp
		clients
				.inMemory()
				.withClient("voyvas")
				.secret(pe.encode("aaa"))
				.authorizedGrantTypes(
						"authorization_code",
						"password",
						"client_credentials",
						"implicit",
						"refresh_token")
				.scopes("webapp")
				// .autoApprove(true)
				.redirectUris("http://localhost:9050", "http://localhost:9050/login", "http://localhost:9050/profiles");

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

		enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccTkConverter()));

		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).tokenEnhancer(enhancerChain);
	}

	@Bean
	TokenEnhancer tokenEnhancer() {
		return new TokenEnhancer() {

			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				Map<String, Object> info = new HashMap<>();
				// info.put("wow", "wow");
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

				System.out.println(authentication.getUserAuthentication());
				return accessToken;
			}
		};
	}

	@Bean
	JwtAccessTokenConverter jwtAccTkConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("abc");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		// JdbcTokenStore ts = new JdbcTokenStore(tsDs);
		return new InMemoryTokenStore();
	}
}
