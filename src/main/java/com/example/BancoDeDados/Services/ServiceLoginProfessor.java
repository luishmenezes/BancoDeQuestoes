package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceLoginProfessor implements UserDetailsService {


    @Autowired
    ProfessorRepositores professorRepositores;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return professorRepositores.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}
