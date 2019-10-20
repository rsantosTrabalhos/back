package com.example.doggis.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.doggis.api.config.token.CustomTokenEnhancer;

/**
 * Classe de configuração para acesso aos recursos
 * @author Rodrigo
 *
 */
@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager administraAutenticacao;
	
	/**
	 * Configurando as informações de acesso ao cliente
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			   /* Login do client */
		       .withClient("angular")
		       /* Senha do client */
		       .secret("@ngul@0")
		       /* O que será permitido */
		       .scopes("read", "write")
		       /* Como será o método de acesso do usuário */
		       .authorizedGrantTypes("password", "refresh_token")
		       /* Tempo de validade do servidor */
		       .accessTokenValiditySeconds(1800)
		       /* Tempo de expiração do token de acesso para atualização do token de uso dos recursos */
		       .refreshTokenValiditySeconds(3600 * 24);
	}
	
	/**
	 * Configurando o comportamento do endpoint
	 * Local onde será guardado o token
	 * E a AuthenticationManager pra obtenção da ResourceServerConfig
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/* Gerando token personalizado */
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints.tokenStore(tokenStore())
				 .tokenEnhancer(tokenEnhancerChain)
				 /* Aplicação não deve reutilizar tokens quando for solicitado refresh */
				 .reuseRefreshTokens(false)
		         .authenticationManager(administraAutenticacao);
	}
	
	/**
	 * Devolve uma instância de token personalizado
	 * @return
	 */
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	/**
	 * Configurando o token bem como a assinatura de validação do mesmo
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("algaworks");
		return accessTokenConverter;
	}

	/**
	 * Tem a função de gerar um novo token
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
