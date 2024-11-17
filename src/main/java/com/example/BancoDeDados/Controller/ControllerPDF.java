package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Services.ServiceJson;
import com.example.BancoDeDados.Services.ServicePDF;
import com.example.BancoDeDados.Services.ServiceQuestao;
import com.example.BancoDeDados.Services.ServiceTratarTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class ControllerPDF {

    @Autowired
    private ServicePDF servicePDF;

    @Autowired
    private ServiceTratarTexto tratarTexto;

    @Autowired
    private ServiceJson json;

    @Autowired
    private ServiceQuestao serviceQuestao;


    @PostMapping("/processar-salvar")
    public String processarESalvarPdf() throws IOException {
        List<Questao> questoes = tratarTexto.pegarQuestoes();
        serviceQuestao.salvarQuestoes(questoes);
        return json.exibirQuestoesDoJson(json.jsonDoCorpo());
    }
}