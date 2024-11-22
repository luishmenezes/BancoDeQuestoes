package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceTratarRespostaIA {
    @Autowired
    ServiceIA ServiceIA;

    public List<Questao> processarRespostaIA() {
        List<Questao> questoes = new ArrayList<>();

        Pattern modeloQuestaoCompleta = Pattern.compile(
                "\\{\\s*\"cabecalho\":\\s*\"([^\"]+)\",\\s*\"enunciado\":\\s*\"([^\"]+)\",\\s*\"alternativas\":\\s*\\[(.*?)\\],\\s*\"gabarito\":\\s*(\\d+)\\s*\\}",
                Pattern.DOTALL
        );

        Matcher matcherQuestaoCompleta = modeloQuestaoCompleta.matcher(ServiceIA.getRespostaDoGemini());

        while (matcherQuestaoCompleta.find()) {
            Questao questao = new Questao();

            questao.setCabecalho(matcherQuestaoCompleta.group(1).trim());
            questao.setEnunciado(matcherQuestaoCompleta.group(2).trim());

            String alternativasBrutas = matcherQuestaoCompleta.group(3);
            List<String> alternativas = processarAlternativas(alternativasBrutas);
            questao.setAlternativas(alternativas);

            questao.setGabarito(Integer.parseInt(matcherQuestaoCompleta.group(4).trim()));

            questoes.add(questao);
        }

        return questoes;
    }

    private List<String> processarAlternativas(String alternativasBrutas) {
        List<String> alternativas = new ArrayList<>();

        Pattern modeloAlternativas = Pattern.compile("\"[a-eA-E]\\)\\s*([^\\\"]+)\"");
        Matcher matcherAlternativas = modeloAlternativas.matcher(alternativasBrutas);

        while (matcherAlternativas.find()) {
            alternativas.add(matcherAlternativas.group().replace("\"", "").trim());
        }

        return alternativas;
    }
}
