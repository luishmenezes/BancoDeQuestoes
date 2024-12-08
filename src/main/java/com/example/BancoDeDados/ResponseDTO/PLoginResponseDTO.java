package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.ProfessorRole;

//cadastro
public record PLoginResponseDTO(Integer id,String token, String nome, ProfessorRole role) {
}
