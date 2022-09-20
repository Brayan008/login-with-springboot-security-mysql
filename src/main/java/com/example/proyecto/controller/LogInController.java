/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mares
 */
@Controller
@Slf4j
public class LogInController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page() {
        log.info("entrando al index");
        return "index";
    }
    
    @RequestMapping(value = "/otro", method = RequestMethod.GET)
    public String pageOtro() {
        log.info("entrando al otro");
        return "otro";
    }
    
    @RequestMapping(value = "/indexAdmin", method = RequestMethod.GET)
    public String indexAdmin() {
        log.info("Entrando al index del admin");
        return "indexAdmin";
    }
    
}
