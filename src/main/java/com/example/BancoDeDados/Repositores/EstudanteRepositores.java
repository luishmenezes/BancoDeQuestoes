package com.example.BancoDeDados.Repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Estudante;

@Component
public interface EstudanteRepositores extends JpaRepository<Estudante, Integer> {
    Optional<Estudante> findByNome(String nome);

    Optional<Estudante> findByEmail(String email);
}
