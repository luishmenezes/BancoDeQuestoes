package com.example.BancoDeDados.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questao {
    private String cabecalho;
    private String enunciado;
    private List<String> alternativas;
}
