package com.example.BancoDeDados.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RespostaEstudantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questao_id", nullable = false)
    private Questao questao;

    @Column(nullable = false)
    private Boolean resposta; // true: Acertou | false: Errou

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante; // Associação com o estudante que respondeu
}
