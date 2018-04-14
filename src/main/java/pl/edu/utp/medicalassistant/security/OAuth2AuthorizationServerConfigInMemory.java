package pl.edu.utp.medicalassistant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import pl.edu.utp.medicalassistant.security.provider.UserDetailsServiceImpl;

import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfigInMemory extends AuthorizationServerConfigurerAdapter {


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    private final TokenStore tokenStore = new InMemoryTokenStore();

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {// @formatter:off
        // @formatter:off
        clients.inMemory().withClient("health-assistant").secret("health-assistant-secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("read", "write")
                .accessTokenValiditySeconds(86400)
                .refreshTokenValiditySeconds(2592000);
        ;
    } // @formatter:on

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        // @formatter:off
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(tokenEnhancer()));
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        // @formatter:on
    }


    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore() {
        return tokenStore;
    }


}
