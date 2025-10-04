package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    List<Disciplina> findByEscola_Nome(String nomeEscola);
}
