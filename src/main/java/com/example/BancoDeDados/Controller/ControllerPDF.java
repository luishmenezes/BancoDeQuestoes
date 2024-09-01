package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Services.ServiceJson;
import com.example.BancoDeDados.Services.ServicePDF;
import com.example.BancoDeDados.Services.ServiceTratarTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class ControllerPDF {
    ServiceJson json=new ServiceJson();
    private final ServicePDF servicePDF;
    ServiceTratarTexto TratarTexto=new ServiceTratarTexto();


    @Autowired
    public ControllerPDF(ServicePDF servicePDF) {
        this.servicePDF = servicePDF;

    }

    @GetMapping("/processar")
    public String processarPDF() throws IOException {
        String exibirTextoExtraido= servicePDF.TextoExtraido();

        return exibirTextoExtraido;
    }
    @GetMapping("/processado")
    public String processado() throws IOException {
        String exibirTextoTratado=TratarTexto.mandartextoFiltrado();
        json.jsonDoCorpo();
        return json.jsonDoCorpo();
    }





}
