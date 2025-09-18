package com.example.BancoDeDados.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviarRespostaDTO {

    private Integer questaoId;
    private UUID estudanteId;
    private Boolean resposta;
}
