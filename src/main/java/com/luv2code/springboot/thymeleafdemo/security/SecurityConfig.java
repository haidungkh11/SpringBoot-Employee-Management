package com.luv2code.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select * from members where user_id=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select * from roles where user_id=?"
        );
        return  jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/employees/list").hasRole("EMPLOYEE")
                        .requestMatchers("/employees/save").hasRole("MANAGER")
                        .requestMatchers("/employees/update").hasRole("MANAGER")
                        .requestMatchers("/employees/deleteEmployee").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form    .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticatedTheUser")
                                .permitAll()
                        )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configure ->
                        configure.accessDeniedPage("/access-denied")
                );
        ;
        return  http.build();
    }
}
