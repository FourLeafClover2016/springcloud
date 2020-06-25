package com.hwx.myauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //添加客户端信息 //使用内存存储OAuth客户端信息
        clients.inMemory()
                // client_id
                .withClient("test")
                // 该client允许的授权类型，不同的类型，则获得token的方式不一样。
                .authorizedGrantTypes("password","authorization_code","client_credentials","refresh_token","implicit")
                // client_secret
                .secret(encoder.encode("123456"))
                .resourceIds("*")
                // 允许的授权范围
                .scopes("all")
                .accessTokenValiditySeconds(1000) //token过期时间
                .refreshTokenValiditySeconds(50000); //refresh过期时间;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
        //reuseRefreshTokens设置为false时，每次通过refresh_token获得access_token时，也会刷新refresh_token；也就是说，会返回全新的access_token与refresh_token。
        //默认值是true，只返回新的access_token，refresh_token不变。
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore() {
        //token保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        return new InMemoryTokenStore();

    }
    // 设置添加用户信息,正常应该从数据库中读取
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("admin").password("{noop}admin1234")
                .authorities("ROLE_USER").build());
        /*userDetailsService.createUser(User.withUsername("user2").password("{noop}123456")
                .authorities("ROLE_USER").build());*/
        return userDetailsService;
    }


}
