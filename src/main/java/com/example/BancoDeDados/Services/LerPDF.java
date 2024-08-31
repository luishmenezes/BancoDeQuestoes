package com.example.BancoDeDados.Services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class LerPDF {

    public String extrairTextoPDF(String caminhoArquivo) throws IOException {
        File arquivoPDF = new File(caminhoArquivo);

        if (!arquivoPDF.exists()) {
            throw new IOException("Arquivo não encontrado: " + arquivoPDF.getAbsolutePath());
        }

        PDDocument documento = Loader.loadPDF(arquivoPDF);
        PDFTextStripper textType = new PDFTextStripper();
        String texto = textType.getText(documento);
        documento.close();
        System.out.println(texto);

        return texto;
    }
}
