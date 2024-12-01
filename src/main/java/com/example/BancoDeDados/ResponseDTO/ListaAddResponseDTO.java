package com.example.BancoDeDados.ResponseDTO;

import com.example.BancoDeDados.Model.Questao;

import java.util.List;

public record ListaAddResponseDTO (Integer listaId, List<Questao> questoes){
}
