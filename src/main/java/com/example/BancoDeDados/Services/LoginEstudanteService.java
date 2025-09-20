package com.example.BancoDeDados.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;

@Component
public class LoginEstudanteService implements UserDetailsService {

    @Autowired
    EstudanteRepositores estudanteRepositores;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        Estudante estudante = this.estudanteRepositores.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return new org.springframework.security.core.userdetails.User(estudante.getEmail(), estudante.getSenha(),
                estudante.getAuthorities());
    }

}
