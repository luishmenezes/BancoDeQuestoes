package com.example.BancoDeDados.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "questoes")
@Table(name = "questoes")
public class Questao {

    @Column
    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    @Column
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Column
    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String cabecalho;
    @Column
    @Lob
    private String enunciado;
    @ElementCollection
    private List<String> alternativas;
    @Column
    private Integer gabarito;

}
