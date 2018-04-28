package com.personal.config.provider;

import com.personal.service.CustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/2 11:40
 * @Path: com.personal.config.handler
 * 自定义验证方式
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private CustomUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = userService.loadUserByUsername(username);
        LOGGER.info("password={}, needPassword={}", password, user.getPassword());
        //密码匹配验证
        if (passwordEncoder().matches(password, user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Wrong password.");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
