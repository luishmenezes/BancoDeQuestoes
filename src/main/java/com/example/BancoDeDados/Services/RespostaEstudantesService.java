package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;
import com.example.BancoDeDados.Repositores.QuestaoRepositores;
import com.example.BancoDeDados.ResponseDTO.EnviarRespostaDTO;
import com.example.BancoDeDados.ResponseDTO.RespostaEstudanteDTO;
import com.example.BancoDeDados.Model.RespostaEstudantes;
import com.example.BancoDeDados.Repository.RespostaEstudantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespostaEstudantesService {

    private final RespostaEstudantesRepository respostaEstudantesRepository;

    @Autowired
    QuestaoRepositores questaoRepository;
    @Autowired
    EstudanteRepositores estudanteRepository;

    public RespostaEstudantesService(
            RespostaEstudantesRepository respostaEstudantesRepository,
            QuestaoRepositores questaoRepository,
            EstudanteRepositores estudanteRepository) {
        this.respostaEstudantesRepository = respostaEstudantesRepository;
        this.questaoRepository = questaoRepository;
        this.estudanteRepository = estudanteRepository;
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
