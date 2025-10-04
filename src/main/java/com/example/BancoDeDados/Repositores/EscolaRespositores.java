package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Escola;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRespositores extends JpaRepository<Escola, UUID> {
    Optional<Escola> findByNome(String nome);

    Optional<Escola> findByEmail(String email);
}
