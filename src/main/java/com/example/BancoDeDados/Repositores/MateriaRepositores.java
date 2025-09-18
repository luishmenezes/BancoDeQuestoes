package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BancoDeDados.Model.Materia;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MateriaRepositores extends JpaRepository<Materia, UUID> {

    Optional<Materia> findById(UUID Id);
}
