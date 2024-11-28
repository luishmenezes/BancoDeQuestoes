package com.example.BancoDeDados.ResponseDTO;

import java.util.List;

public class QuestaoResponseDTO {
    private Integer id;
    private String cabecalho;
    private String enunciado;
    private List<String> alternativas;
    private Integer gabarito;


    public QuestaoResponseDTO(Integer id, String cabecalho, String enunciado, List<String> alternativas, Integer gabarito) {
        this.id = id;
        this.cabecalho = cabecalho;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.gabarito = gabarito;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public Integer getGabarito() {
        return gabarito;
    }

    public void setGabarito(Integer gabarito) {
        this.gabarito = gabarito;
    }
}
