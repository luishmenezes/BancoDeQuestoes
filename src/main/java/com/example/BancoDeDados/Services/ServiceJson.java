package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ServiceJson {

    private ServiceTratarTexto tratarTexto = new ServiceTratarTexto();

       public String jsonDoCorpo() throws IOException {
        String corpo = tratarTexto.pegarQuestao();
        Gson gson = new Gson();
        String corpoJson = gson.toJson(corpo);
        return corpoJson;
    }

    public List<Questao> lerQuestoesDoJson(String json) throws IOException {
        Gson gson = new Gson();
        Type questaoListType = new TypeToken<List<Questao>>() {}.getType();
        List<Questao> questoes = gson.fromJson(json, questaoListType);
        return questoes;
    }

        public void exibirQuestoesDoJson(String json) throws IOException {
        List<Questao> questoes = lerQuestoesDoJson(json);

        for (Questao questao : questoes) {
            System.out.println("Cabeçalho: " + questao.getCabecalho());
            System.out.println("Enunciado: " + questao.getEnunciado());
            System.out.println("Alternativas: " + questao.getAlternativas());
            System.out.println();
        }
    }
}
