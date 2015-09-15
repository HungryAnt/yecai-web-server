package com.antsoft.yecai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
* Created by sunlin05 on 2015/4/4.
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource);
                // .withDefaultSchema()
                // .withUser("ant").password("ant").roles("USER", "ADMIN").and()
                // .withUser("test").password("test").roles("USER", "ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests()
//                    .antMatchers("/resources/**", "/signup", "/about").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
                    .antMatchers("/superAdmin/**").hasRole("SUPER_ADMIN")
                    // .antMatchers("/admin/**").access("hasRole('ROLE_SUPER_ADMIN')")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .and()
//                .logout()
//                    .and()
                .httpBasic();
    }
}
