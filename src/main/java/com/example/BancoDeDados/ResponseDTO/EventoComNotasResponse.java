package com.example.BancoDeDados.ResponseDTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EventoComNotasResponse {
    private UUID id;
    private String titulo;
    private String descricao;
    private Double notaMaxima;
    private LocalDateTime data;
    private String materiaNome;
    private List<NotaEstudanteResponse> notasEstudantes;
}
