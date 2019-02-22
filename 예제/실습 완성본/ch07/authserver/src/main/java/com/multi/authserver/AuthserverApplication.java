package com.multi.authserver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthserverApplication extends AuthorizationServerConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;

//	public TokenStore jdbcTokenStore() {
//		return new JdbcTokenStore(dataSource);
//	}
	
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("fdhfelleaijansrblnsfliahlfihrs");
		return converter;
	}

	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	public ApprovalStore inmemoryApprovalStore() {
		return new InMemoryApprovalStore();
	}

	public AuthorizationCodeServices inmemoryAuthorizationCodeServices() {
		return new InMemoryAuthorizationCodeServices();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client1").secret("1234")
				.authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials",
						"refresh_token")
				.scopes("profile", "message", "read").accessTokenValiditySeconds(3600)
				.refreshTokenValiditySeconds(-1)
				.redirectUris("http://localhost:8080/client/callback");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
			.approvalStore(inmemoryApprovalStore())
			.authorizationCodeServices(inmemoryAuthorizationCodeServices())
			.authenticationManager(authenticationManager);
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}
}
