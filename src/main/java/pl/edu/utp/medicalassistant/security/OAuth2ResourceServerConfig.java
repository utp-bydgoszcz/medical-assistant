package pl.edu.utp.medicalassistant.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String USERS_CREATE = "/user/create";
    private static final String USERS_CREATE_ALL = "/user/create-all";
    private static final String USERS_DELETE = "/user/delete-all";
    private static final String AED_LIST = "/photo-aed/get-aed";
	private static final String INFORMATIONS = "/web/informations/**";
	private static final String FILES = "/api/v1/file/**";


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/oauth/token/revokeById/**").permitAll()
                .antMatchers("/tokens/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers( USERS_CREATE, USERS_CREATE_ALL, USERS_DELETE,AED_LIST,INFORMATIONS,FILES).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().csrf().disable();
    }
}
