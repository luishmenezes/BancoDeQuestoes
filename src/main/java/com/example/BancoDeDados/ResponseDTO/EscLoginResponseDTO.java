package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.EscolaRole;

//cadastro escola com role
public record EscLoginResponseDTO(String token, String nome, EscolaRole role) {

}
