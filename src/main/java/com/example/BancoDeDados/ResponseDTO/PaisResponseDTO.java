package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.PaisRole;


    public record PaisResponseDTO(String nome, String email, String senha,  PaisRole role) {
    }

