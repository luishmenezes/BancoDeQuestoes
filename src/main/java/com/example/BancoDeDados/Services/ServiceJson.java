package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

public class ServiceJson {

    private final ServiceTratarTexto serviceTratarTexto = new ServiceTratarTexto();

    public String jsonDoCorpo() throws IOException {
        List<Questao> questoes = serviceTratarTexto.pegarQuestoes();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(questoes);
    }

    public void exibirQuestoesDoJson(String json) {
        System.out.println("Questões em formato JSON formatado: \n" + json);
    }
}
