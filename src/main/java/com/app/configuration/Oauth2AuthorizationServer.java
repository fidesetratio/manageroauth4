package com.app.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends 
AuthorizationServerConfigurerAdapter{

    private static final String RESOURCE_ID = "xxx";

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(new InMemoryTokenStore())
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            /*oauthServer.checkTokenAccess("hasRole('CLIENT')");
            */oauthServer.checkTokenAccess("permitAll()");
            
        }
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("clientauthcode")
                        .secret("123456")
                    //    .authorizedGrantTypes("authorization_code", "refresh_token")
                          .authorizedGrantTypes("authorization_code")
                        .scopes("read", "write")
                        .resourceIds(RESOURCE_ID)
                    .and()
                    .withClient("clientcred")
                        .secret("123456")
                        .authorizedGrantTypes("client_credentials")
                        .scopes("trust")
                        .resourceIds(RESOURCE_ID)
                    .and()
                    .withClient("clientapp")
                        .secret("123456")
                        .authorizedGrantTypes("password")
                        .scopes("read", "write")
                        .resourceIds(RESOURCE_ID)
                        .autoApprove(true)
                        .accessTokenValiditySeconds(200)
                        .refreshTokenValiditySeconds(100)
                    .and()
                    .withClient("jsclient")
                        .secret("jspasswd")
                        .authorizedGrantTypes("implicit")
                        .scopes("read", "write")
                        .resourceIds(RESOURCE_ID)
                        .authorities("CLIENT")
                        .redirectUris("http://localhost:20000/implicit-client/")
                        .accessTokenValiditySeconds(60 * 60 * 24) // token berlaku seharian, besok harus login ulang
                        .autoApprove(true)
                    ;
        }
    
}