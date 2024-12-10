package com.example.BancoDeDados.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviarRespostaDTO {
    private Integer questaoId;
    private Integer estudanteId;
    private Boolean resposta;
}
