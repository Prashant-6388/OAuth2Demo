package com.pc.OAuthDemo.config;


import com.mysql.cj.xdevapi.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Objects;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("<signedKey>");

        endpointsConfigurer.tokenStore(tokenStore(jdbcTemplate))
                .reuseRefreshTokens(false)
                .accessTokenConverter(converter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception {
        securityConfigurer.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientConfigurer) throws Exception {
        clientConfigurer.jdbc(jdbcTemplate.getDataSource());
    }

    public TokenStore tokenStore(JdbcTemplate jdbcTemplate) {
        return new JdbcTokenStore(Objects.requireNonNull(jdbcTemplate.getDataSource()));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore(jdbcTemplate));
        defaultTokenServices.setReuseRefreshToken(true);
        return  defaultTokenServices;
    }
}
