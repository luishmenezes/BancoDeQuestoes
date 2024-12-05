package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.ProfessorRole;

//cadastro
public record PLoginResponseDTO(String token, String nome, ProfessorRole role) {
}
