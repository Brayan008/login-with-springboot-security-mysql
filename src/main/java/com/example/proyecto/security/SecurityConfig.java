/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author mares
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
    
    @Autowired
    public void configurerGlogal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}123") //noop indica que no encripte el password
//                .roles("ADMIN", "USER") //automáticamente spring agrega ROLE_
//                .and()
//                .withUser("user")
//                .password("{noop}123") //noop indica que no encripte el password
//                .roles("USER") //automáticamente spring agrega ROLE_
//                ;
//    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/indexAdmin")
                .hasRole("ADMIN")
                .antMatchers("/","/otro")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    
}

