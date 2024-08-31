package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Services.LerPDF;
import com.example.BancoDeDados.Services.serviceChatGPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class ControllerPDF {

    private final LerPDF lerPDF;
    private final serviceChatGPT serviceChatGPT;

    @Autowired
    public ControllerPDF(LerPDF lerPDF, serviceChatGPT serviceChatGPT) {
        this.lerPDF = lerPDF;
        this.serviceChatGPT = serviceChatGPT;
    }

    @GetMapping("/processar")
    public String processarPDF() {
        try {
            String texto = lerPDF.extrairTextoPDF("C:\\Users\\David\\Downloads\\BancoDeDados\\BancoDeDados\\src\\main\\resources\\Arquivos\\teste1.pdf");
            return texto;
        } catch (IOException e) {
            return "Erro ao processar o PDF: " + e.getMessage();
        }
    }

}
