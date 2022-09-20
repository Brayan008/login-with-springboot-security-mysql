/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author mares
 */
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String toEmail,
            String subject,
            String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mares.bueno.brayan@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        
        mailSender.send(message);
    }
}
