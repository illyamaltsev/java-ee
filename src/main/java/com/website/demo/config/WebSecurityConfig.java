package com.website.demo.config;

import com.website.demo.domain.types.Permission;
import com.website.demo.repository.UserRepository;
import com.website.demo.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/admin", "/admin/**").hasAuthority(Permission.VIEW_ADMIN.name())
                .antMatchers("/").authenticated()
                .antMatchers("/pages/**").authenticated()
//                .antMatchers("/profile").authenticated()
//                .antMatchers("/pages/**").hasAuthority(Permission.VIEW_CATALOG.name())
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService(userRepository);
    }
}
