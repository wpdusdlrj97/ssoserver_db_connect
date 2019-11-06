package com.example.sso.ssoserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


//Now, let's move to the configuration and define a simple form login mechanism:
//Note that we used simple in-memory authentication, but we can simply replace it with a custom userDetailsService.
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                // client 의 인증정보를 Header 가 아닌, form 으로 받을 수 있도록 한다.
                .allowFormAuthenticationForClients();;
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("SampleClientId")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code")
                .scopes("user_info")
                //.autoApprove(true)
                .redirectUris("http://localhost:8080/login")
        // .accessTokenValiditySeconds(3600)
        ; // 1 hour
    }

    //http://localhost:8081/auth/oauth/authorize?client_id=SampleClientId&response_type=code&scope=user_info
    //코드를 받으려면 해당 url로 접근하면 토큰을 받을 수 있다


}