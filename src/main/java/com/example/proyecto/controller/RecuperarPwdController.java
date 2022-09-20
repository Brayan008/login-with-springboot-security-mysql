/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.controller;

import com.example.proyecto.dao.IRecuperarPwdDao;
import com.example.proyecto.dao.IUsuarioDao;
import com.example.proyecto.domain.RecuperarPwd;
import com.example.proyecto.domain.Usuario;
import com.example.proyecto.service.EmailSenderService;
import com.example.proyecto.util.EncriptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mares
 */
@Controller
@RequestMapping("/recuperar")
public class RecuperarPwdController {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IRecuperarPwdDao recuperarPwdDao;

    @Autowired
    private EmailSenderService senderService;

    @PostMapping("/contrasena")
    public String peticionParaRecuperarPwd(@RequestParam String email) {

        Usuario usuarioExiste = usuarioDao.findByEmail(email);
        if (usuarioExiste == null) {
            return "redirect:/login?resetPwdNoUser";
        }

        RecuperarPwd recuperacionExiste = recuperarPwdDao.findByEmail(email);
        String token = EncriptPassword.generateRandomToken();
        if (recuperacionExiste != null) {
            recuperacionExiste.setToken(token);
            recuperarPwdDao.save(recuperacionExiste);
        } else {
            RecuperarPwd recuperar = new RecuperarPwd();
            recuperar.setEmail(email);
            recuperar.setToken(token);
            recuperarPwdDao.save(recuperar);
        }

        senderService.sendEmail(email,
                "Password recovery",
                "This is an email to recover password in <Name Page> of the user " + email + "\n"
                + "If you don't know, ignore this email. \n"
                + "Go to http://localhost:8080/recuperar/nueva-contrasena?token=" + token + " \n"
                + "to change your password.");
        return "redirect:/login?resetPwd";
    }

    @GetMapping("/nueva-contrasena")
    public String nuevaContrasena(@RequestParam String token, Model model) {
        RecuperarPwd recuperacionExiste = recuperarPwdDao.findByToken(token);
        if (recuperacionExiste == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", recuperacionExiste.getEmail());
        return "recuperarPwd";
    }

    @PostMapping("/nueva-contrasena")
    public String guardarNuevaContrasena(Model model, Usuario usuario) {
        Usuario user = usuarioDao.findByEmail(usuario.getEmail());
        RecuperarPwd recuperarPwd = recuperarPwdDao.findByEmail(usuario.getEmail());

        user.setPassword(EncriptPassword.encriptarPassword(usuario.getPassword()));
        usuarioDao.save(user);
        recuperarPwdDao.deleteById(recuperarPwd.getIdRecuperarPwd());
        return "redirect:/login?pwdCambiada";
    }
}
