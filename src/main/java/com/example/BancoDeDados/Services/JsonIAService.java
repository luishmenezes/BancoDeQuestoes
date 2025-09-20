package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonIAService {

    @Autowired
    private TratarRespostaIAService tratarRespostaIA;

    public String gerarJsonRespostaIA() {
        List<Questao> questoes = tratarRespostaIA.processarRespostaIA();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(questoes);
    }

    public String exibirQuestoesDoJson(String json) {
        System.out.println("Questões em formato JSON formatado: \n" + json);
        return json;
    }
}
