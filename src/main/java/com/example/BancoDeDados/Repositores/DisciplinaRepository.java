package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    List<Disciplina> findByEscola_Nome(String nomeEscola);
}
