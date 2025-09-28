package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.EscolaRole;
import com.example.BancoDeDados.Model.PaisRole;

public record PaisLoginResponseDTO(String token, String nome, PaisRole role) {
}
