package com.example.BancoDeDados.Services;

import lombok.Data;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


    @Service
    @Data
    public class ServicePDFIA {
        private String textoExtraido;

        public String extrairTextoPDF(MultipartFile arquivoPDF) throws IOException {
            // Usando o arquivo recebido como MultipartFile
            try (PDDocument documento = Loader.loadPDF((RandomAccessRead) arquivoPDF.getInputStream())) {
                PDFTextStripper textType = new PDFTextStripper();
                String texto = textType.getText(documento);
                return texto;
            }
        }

        public String TextoExtraido(MultipartFile arquivoPDF) throws IOException {
            textoExtraido = extrairTextoPDF(arquivoPDF);
            return textoExtraido;
        }
    }
