package com.example.BancoDeDados.ResponseDTO;

import java.util.Date;


public record ProfessorResponseDTO(Integer id, String nome, String materia1, String materia2, String instituicao,
                String email,
                String senha, Date dataNascimento) {

}
