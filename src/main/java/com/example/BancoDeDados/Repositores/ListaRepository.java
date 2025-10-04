package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Lista;
import com.example.BancoDeDados.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    List<Lista> findByProfessorId(UUID professorId);

    Optional<Professor> findByIdAndProfessor(Long listaId, Professor professor);
}
