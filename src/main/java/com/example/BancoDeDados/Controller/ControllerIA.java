package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/serviceIA")
public class ControllerIA {

    @Autowired
    private ServicePDF servicePDF;

    @Autowired
    private ServiceIA serviceIA;

    @Autowired
    private ServiceTratarRespostaIA tratarRespostaIA;

    @Autowired
    private ServiceQuestao serviceQuestao;

    @Autowired
    private ServiceJsonIA json;

    @GetMapping("/promp")
    public ResponseEntity<String> getPromp() {
        try {
            String textoPDF = servicePDF.TextoExtraido();

            String respostaserviceIA = serviceIA.enviarParaGemini(textoPDF);

            return ResponseEntity.ok("Questões salvas com sucesso!");
        } catch (IOException e) {

            return ResponseEntity.status(500).body("Erro ao extrair o texto do PDF: " + e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Erro ao processar a requisição: " + e.getMessage());
        }

    }

    @PostMapping("/processar-salvar")
    public String processarESalvarPdf() throws IOException {
        List<Questao> questoes = tratarRespostaIA.processarRespostaIA();
        serviceQuestao.salvarQuestoes(questoes);
        return json.exibirQuestoesDoJson(json.gerarJsonRespostaIA());
    }
}