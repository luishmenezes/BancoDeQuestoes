package com.example.BancoDeDados.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BancoDeDados.Model.Materia;

@Repository
public interface MateriaRepositores extends JpaRepository<Materia, Long> {

}
