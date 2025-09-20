package com.example.BancoDeDados.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Repositores.EscolaRespositores;

@Component
public class LoginEscolaService implements UserDetailsService {

    @Autowired
    EscolaRespositores escolaRespositores;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        Escola escola = this.escolaRespositores.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return new org.springframework.security.core.userdetails.User(escola.getEmail(), escola.getSenha(),
                escola.getAuthorities());
    }
}
