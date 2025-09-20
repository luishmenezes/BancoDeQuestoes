package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.ResponseDTO.QuestaoResponseDTO;
import com.example.BancoDeDados.Services.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questao")
public class ControllerQuestao {

    @Autowired
    private QuestaoService questaoService;

    @PostMapping("/criar")
    public Questao criarQuestao(
            @RequestParam String cabecalho,
            @RequestParam String enunciado,
            @RequestParam List<String> alternativas,
            @RequestParam Integer gabarito) {
        return questaoService.criarQuestao(cabecalho, enunciado, alternativas, gabarito);
    }

    @GetMapping("/{id}")
    public QuestaoResponseDTO buscarQuestao(@PathVariable int id) {
        return questaoService.buscarQuestaoPorIdComGabaritoDTO(id);  // Certificando que retorna o DTO
    }

    @GetMapping("/questoes")
    public List<Questao> listarQuestoes() {
        return questaoService.listarQuestoes();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarQuestao(@PathVariable int id) {
        questaoService.deletarQuestao(id);
    }
}
