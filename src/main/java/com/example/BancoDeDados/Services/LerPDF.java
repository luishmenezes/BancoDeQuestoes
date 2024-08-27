package com.example.BancoDeDados.Services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class LerPDF {
    public static void main(String[] args) throws IOException {
        // Corrigi o caminho para apontar diretamente para o arquivo PDF
        File arquivoPDF = new File("C:\\Users\\David\\Downloads\\BancoDeDados\\BancoDeDados\\src\\main\\resources\\Arquivos\\teste.pdf");

        System.out.println("Tentando carregar o arquivo: " + arquivoPDF.getAbsolutePath());

        
        if (!arquivoPDF.exists()) {
            System.out.println("Arquivo não encontrado: " + arquivoPDF.getAbsolutePath());
            return;
        }

        PDDocument documento = Loader.loadPDF(arquivoPDF);

        PDFTextStripper textType = new PDFTextStripper();
        String texto = textType.getText(documento);

        documento.close();
        System.out.println(texto);

    }
}
