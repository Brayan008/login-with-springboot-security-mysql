/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.security;

/**
 *
 * @author mares
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/403").setViewName("/403");

    }
}

