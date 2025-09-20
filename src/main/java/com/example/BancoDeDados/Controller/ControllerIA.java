package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/serviceIA")
public class ControllerIA {

    @Autowired
    private PDFIAService PDFIAService;

    @Autowired
    private IAService IAService;

    @Autowired
    private TratarRespostaIAService tratarRespostaIA;

    @Autowired
    private QuestaoService questaoService;

    @Autowired
    private JsonIAService json;

    @PostMapping("/processar-pdf")
    public ResponseEntity<String> processarPdf(@RequestParam("file") MultipartFile arquivoPDF) {
        try {
            System.out.println("Recebido arquivo: " + arquivoPDF.getOriginalFilename()); // Log para verificar o arquivo recebido
            String textoPDF = PDFIAService.TextoExtraido(arquivoPDF);
            String respostaserviceIA = IAService.enviarParaGemini(textoPDF);
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
        questaoService.salvarQuestoes(questoes);
        return json.exibirQuestoesDoJson(json.gerarJsonRespostaIA());
    }
}
