package com.mnit.tnt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

/**
 * Created by lihe on 1/19/17.
 */

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    @Value("${tntserver.stormpath.enabled}")
// with default value set
// @Value("${tntserver.stormpath.enabled: false}")
    Boolean stormpathEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (stormpathEnabled) {
            http.apply(stormpath()).and()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/public/*").permitAll();
        } else {
            http.authorizeRequests().antMatchers("/**").permitAll();
        }
    }
}