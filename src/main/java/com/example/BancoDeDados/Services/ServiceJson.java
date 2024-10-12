package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ServiceJson {

    @Autowired
    private ServiceTratarTexto serviceTratarTexto;

    public String jsonDoCorpo() throws IOException {
        List<Questao> questoes = serviceTratarTexto.pegarQuestoes();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(questoes);
    }

    public String exibirQuestoesDoJson(String json) {
        System.out.println("Questões em formato JSON no formatado: \n" + json);
        return json;
    }
}
