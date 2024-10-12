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
