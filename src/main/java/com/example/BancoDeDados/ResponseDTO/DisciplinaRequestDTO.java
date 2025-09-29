package com.example.BancoDeDados.ResponseDTO;

public record DisciplinaRequestDTO(
        String nome,
        String emailProfessor,
        Integer escolaId
) {}
