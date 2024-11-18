package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.ResponseDTO.QuestaoResponseDTO;
import com.example.BancoDeDados.Services.ServiceQuestao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questao")
public class ControllerQuestao {

    @Autowired
    private ServiceQuestao serviceQuestao;

    @PostMapping("/criar")
    public Questao criarQuestao(
            @RequestParam String cabecalho,
            @RequestParam String enunciado,
            @RequestParam List<String> alternativas,
            @RequestParam Integer gabarito) {
        return serviceQuestao.criarQuestao(cabecalho, enunciado, alternativas, gabarito);
    }

    @GetMapping("/{id}")
    public QuestaoResponseDTO buscarQuestao(@PathVariable int id) {
        return serviceQuestao.buscarQuestaoPorIdComGabaritoDTO(id);  // Certificando que retorna o DTO
    }

    @GetMapping("/questoes")
    public List<Questao> listarQuestoes() {
        return serviceQuestao.listarQuestoes();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarQuestao(@PathVariable int id) {
        serviceQuestao.deletarQuestao(id);
    }
}
