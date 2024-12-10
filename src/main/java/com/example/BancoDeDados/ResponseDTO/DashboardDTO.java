package com.example.BancoDeDados.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DashboardDTO {
    private long totalQuestoes;
    private long totalProfessores;
    private long totalEscolas;
    private long totalUsuarios;
    private Map<String, Long> professoresPorInstituicao;
    private Map<String, Long> contarProfessoresPorMaterias;
    private Map<String, Long> contarProfessoresPorMaterias2;

    public DashboardDTO(long totalQuestoes, long totalProfessores, long totalEscolas, long totalUsuarios, Map<String, Long> professoresPorInstituicao, Map<String, Long> contarProfessoresPorMaterias, Map<String, Long> contarProfessoresPorMaterias2) {
        this.totalQuestoes = totalQuestoes;
        this.totalProfessores = totalProfessores;
        this.totalEscolas = totalEscolas;
        this.totalUsuarios = totalUsuarios;
        this.professoresPorInstituicao = professoresPorInstituicao;
        this.contarProfessoresPorMaterias = contarProfessoresPorMaterias;
        this.contarProfessoresPorMaterias2 = contarProfessoresPorMaterias2;
    }

    public DashboardDTO() {}
}
