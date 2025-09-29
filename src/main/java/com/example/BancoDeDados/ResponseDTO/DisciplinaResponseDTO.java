package com.example.BancoDeDados.ResponseDTO;

import java.util.List;

public record DisciplinaResponseDTO(
        Integer id,
        String nome,
        String nomeProfessor,
        String nomeEscola,
        List<String> alunos
) {}
