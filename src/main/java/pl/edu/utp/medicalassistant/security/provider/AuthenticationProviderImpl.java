package pl.edu.utp.medicalassistant.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public AuthenticationProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();


        User user = userService.loginUser(username, password);

        return usernamePasswordAuthenticationToken(user);
    }

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(User user) {
        List<GrantedAuthority> authorities = user.getAuthorites().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user.getId(), null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
