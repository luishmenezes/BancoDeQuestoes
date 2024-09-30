package com.example.BancoDeDados.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;
}
