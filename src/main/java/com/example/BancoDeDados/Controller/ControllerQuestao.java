package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
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
        public Questao criarQuestao(@RequestBody Questao questao){
            return serviceQuestao.salvarQuestao(questao);

        }
    @GetMapping("/questoes")
    public List<Questao> listarQuestoes(){
        return serviceQuestao.listarQuestoes();
    }

    @GetMapping("/{id}")
    public Questao buscarQuestao(@PathVariable int id){
        return serviceQuestao.buscarQuestaoPorId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarQuestao(@PathVariable int id){
        serviceQuestao.deletarQuestao(id);
    }
    }





