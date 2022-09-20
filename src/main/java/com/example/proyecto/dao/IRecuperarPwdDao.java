/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.dao;

import com.example.proyecto.domain.RecuperarPwd;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mares
 */
public interface IRecuperarPwdDao extends JpaRepository<RecuperarPwd, Long>{
    RecuperarPwd findByEmail(String email);
    RecuperarPwd findByToken(String token);
    RecuperarPwd deleteByEmail(String token);
}
