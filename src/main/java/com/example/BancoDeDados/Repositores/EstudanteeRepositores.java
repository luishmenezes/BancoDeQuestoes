package com.example.BancoDeDados.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Estudante;

@Component
public interface EstudanteRepositores extends JpaRepository<Estudante,Integer> {

}
