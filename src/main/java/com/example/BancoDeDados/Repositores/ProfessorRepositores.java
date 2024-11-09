package com.example.BancoDeDados.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.BancoDeDados.Model.Professor;

@Component
public interface ProfessorRepositores extends JpaRepository<Professor, Integer> {
 UserDetails findByLogin(String login);
}
