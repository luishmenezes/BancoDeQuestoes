package com.example.BancoDeDados.Model;

import com.example.BancoDeDados.ResponseDTO.ProfessorResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "professor")
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    public Professor(ProfessorResponseDTO professorDTO) {
        this.nome = professorDTO.nome();
        this.email = professorDTO.email();
        this.senha = professorDTO.senha();
        this.dataNascimento = professorDTO.dataNascimento();
    }

}
