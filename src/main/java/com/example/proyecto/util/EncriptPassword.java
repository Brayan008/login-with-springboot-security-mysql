/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.util;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author mares
 */
public class EncriptPassword {

    public static void main(String[] args) {
        String password = "123";
        System.out.println("pwd:" + password);
        System.out.println("pwd encriptado:" + encriptarPassword(password));

    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static String generateRandomToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
