package com.example.BancoDeDados.ResponseDTO;

import java.util.Date;

import com.example.BancoDeDados.Model.EscolaRole;

public record EscolaResponseDTO(String nome, String email, String senha, Date dataNascimento, EscolaRole role) {
}
