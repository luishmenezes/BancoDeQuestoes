package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Model.Questao;

import java.util.List;

public record ListaResponseDTO(
        Integer id,
        String titulo,
        String professorNome
) {
    public ListaResponseDTO(Long id, String titulo, Professor professor, List<Questao> questoes, List<Estudante> estudantes) {
        this(
                id != null ? id.intValue() : null,
                titulo,
                professor != null ? professor.getNome() : null
        );
    }
}
