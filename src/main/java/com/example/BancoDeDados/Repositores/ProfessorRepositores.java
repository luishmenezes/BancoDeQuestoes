package com.example.BancoDeDados.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Professor;

import java.util.Optional;
import java.util.UUID;

@Component
public interface ProfessorRepositores extends JpaRepository<Professor, UUID> {
 Optional<Professor> findByEmail(String email);
}
