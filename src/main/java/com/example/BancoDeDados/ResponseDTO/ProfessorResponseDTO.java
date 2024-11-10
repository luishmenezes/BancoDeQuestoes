package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.ProfessorRole;

import java.util.Date;

public record ProfessorResponseDTO(String nome, String email, String senha, Date dataNascimento, ProfessorRole role) {

}
