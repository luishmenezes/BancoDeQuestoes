package com.example.BancoDeDados.Model;

import com.example.BancoDeDados.ResponseDTO.MateriaResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference
    private Professor professor;

    @ManyToMany(mappedBy = "materias")
    private List<Estudante> estudantes = new ArrayList<>();
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    public Materia(MateriaResponseDTO dto) {
        this.nome = dto.getNome();
    }
}