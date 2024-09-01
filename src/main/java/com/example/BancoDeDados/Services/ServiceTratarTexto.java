package com.example.BancoDeDados.Services;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceTratarTexto {

    private ServicePDF texto = new ServicePDF();

    public String pegarQuestao() throws IOException {
        StringBuilder resultado = new StringBuilder();

        Pattern ModeloQuestao = Pattern.compile("(\\d+\\.\\s\\([^\\)]+\\))(.*?)(?=\\d+\\.\\s\\([^\\)]+\\)|$)", Pattern.DOTALL);
        Matcher Questao = ModeloQuestao.matcher(texto.TextoExtraido());

        while (Questao.find()) {
            resultado.append(Questao.group()).append("\n");
        }

        return resultado.toString();
    }
    public String mandartextoFiltrado() throws IOException {
        return pegarQuestao();
    }
}