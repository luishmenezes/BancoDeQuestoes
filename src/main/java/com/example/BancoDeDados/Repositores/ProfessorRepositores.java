package com.example.BancoDeDados.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Professor;

import java.util.Optional;

@Component
public interface ProfessorRepositores extends JpaRepository<Professor, Integer> {
 Optional<Professor> findByEmail(String email);
}
