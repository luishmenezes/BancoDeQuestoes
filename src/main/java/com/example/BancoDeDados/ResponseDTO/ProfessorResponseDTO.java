package com.example.BancoDeDados.ResponseDTO;

import java.util.Date;

import com.example.BancoDeDados.Model.ProfessorRole;

public record ProfessorResponseDTO(String nome, String materia1, String materia2, String instituicao, String email,
        String senha, Date dataNascimento, ProfessorRole role) {

}
