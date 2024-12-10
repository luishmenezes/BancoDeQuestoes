package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;
import com.example.BancoDeDados.Repositores.ListaRepository;
import com.example.BancoDeDados.Repositores.QuestaoRepositores;
import com.example.BancoDeDados.ResponseDTO.EnviarRespostaDTO;
import com.example.BancoDeDados.ResponseDTO.RespostaEstudanteDTO;
import com.example.BancoDeDados.Model.RespostaEstudantes;
import com.example.BancoDeDados.Repositores.RespostaEstudantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostaEstudantesService {

    @Autowired
    RespostaEstudantesRepository respostaEstudantesRepository;

    @Autowired
    ListaRepository listaRepository;
    @Autowired
    QuestaoRepositores questaoRepository;
    @Autowired
    EstudanteRepositores estudanteRepository;

    public RespostaEstudantesService(
            RespostaEstudantesRepository respostaEstudantesRepository,
            QuestaoRepositores questaoRepository,
            EstudanteRepositores estudanteRepository,ListaRepository listaRepository) {
        this.respostaEstudantesRepository = respostaEstudantesRepository;
        this.questaoRepository = questaoRepository;
        this.estudanteRepository = estudanteRepository;
        this.listaRepository = listaRepository;

    }
    public void salvarResposta(EnviarRespostaDTO enviarRespostaDTO) {
        Questao questao = questaoRepository.findById(enviarRespostaDTO.getQuestaoId())
                .orElseThrow(() -> new RuntimeException("Questão não encontrada."));
        Estudante estudante = estudanteRepository.findById(enviarRespostaDTO.getEstudanteId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));

        RespostaEstudantes resposta = new RespostaEstudantes();
        resposta.setQuestao(questao);
        resposta.setEstudante(estudante);
        resposta.setResposta(enviarRespostaDTO.getResposta());

        respostaEstudantesRepository.save(resposta);
    }
    public List<Integer> buscarQuestoesPorListaEEstudante(Long listaId, Long estudanteId) {
        // Validar se o estudante existe
        if (!respostaEstudantesRepository.existsByEstudanteId(estudanteId)) {
            throw new IllegalArgumentException("Estudante não encontrado.");
        }

        // Obter as questões associadas à lista
        return listaRepository.findById(listaId)
                .map(lista -> lista.getQuestoes().stream()
                        .map(Questao::getId)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada."));
    }

    public RespostaEstudanteDTO buscarRespostaPorQuestaoEEstudante(Long questaoId, Long estudanteId) {
        RespostaEstudantes resposta = respostaEstudantesRepository
                .findByQuestaoIdAndEstudanteId(questaoId, estudanteId)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada."));
        return new RespostaEstudanteDTO(
                resposta.getId(),
                resposta.getQuestao().getId(),
                resposta.getEstudante().getId(),
                resposta.getResposta()
        );
    }
}
