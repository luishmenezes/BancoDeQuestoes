package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.QuestaoRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceQuestao {

    @Autowired
    private QuestaoRepositores questaoRepositores;

    public Questao criarQuestao(String cabecalho, String enunciado, List<String> alternativas) {
        Questao novaQuestao = new Questao();
        novaQuestao.setCabecalho(cabecalho);
        novaQuestao.setEnunciado(enunciado);
        novaQuestao.setAlternativas(alternativas);
        return questaoRepositores.save(novaQuestao);
    }

    public Questao salvarQuestao(Questao questao){
        return questaoRepositores.save(questao);
    }

    public void salvarQuestoes(List<Questao> questoes) {
        questaoRepositores.saveAll(questoes);
    }

    public List<Questao> listarQuestoes() {
        return questaoRepositores.findAll();
    }

    public void deletarQuestao(Integer id) {
        questaoRepositores.deleteById(id);
    }

    public Questao buscarQuestaoPorId(Integer id) {
        return questaoRepositores.findById(id).orElse(null);
    }
}
