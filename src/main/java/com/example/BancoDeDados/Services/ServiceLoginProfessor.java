package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class ServiceLoginProfessor implements UserDetailsService {


    @Autowired
    ProfessorRepositores professorRepositores;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {

        Professor professor =this.professorRepositores.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not Found"));
        return new org.springframework.security.core.userdetails.User(professor.getEmail(),professor.getSenha(),professor.getAuthorities());
    }
}

