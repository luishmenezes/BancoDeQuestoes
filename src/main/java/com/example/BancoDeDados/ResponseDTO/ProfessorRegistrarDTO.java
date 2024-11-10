package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.ProfessorRole;

public record ProfessorRegistrarDTO(String email, String senha, ProfessorRole role) {
}
