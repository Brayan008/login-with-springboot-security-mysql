/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.service;

import com.example.proyecto.dao.IUsuarioDao;
import com.example.proyecto.domain.Rol;
import com.example.proyecto.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(email);
        }

        User user = new User(
                usuario.getEmail(), 
                usuario.getPassword(), 
                getGrantedAuthorities(usuario)
        );
        return user;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Rol rol = usuario.getRol();
        authorities.add(new SimpleGrantedAuthority(rol.getNameRol()));
        return authorities;
    }
}

