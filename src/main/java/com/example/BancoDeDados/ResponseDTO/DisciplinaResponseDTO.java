package com.example.BancoDeDados.ResponseDTO;

import java.util.List;
import java.util.UUID;

public record DisciplinaResponseDTO(
        UUID id,
        String nome,
        String nomeProfessor,
        String nomeEscola,
        List<String> alunos
) {}
