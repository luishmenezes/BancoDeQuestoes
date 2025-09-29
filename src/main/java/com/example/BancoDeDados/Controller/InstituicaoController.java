package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.ResponseDTO.InstituicaoRequest;
import com.example.BancoDeDados.ResponseDTO.InstituicaoResponse;
import com.example.BancoDeDados.Services.InstituicaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @PostMapping
    public ResponseEntity<InstituicaoResponse> cadastrarInstituicao(@Valid @RequestBody InstituicaoRequest request) {
        InstituicaoResponse response = instituicaoService.cadastrarInstituicao(request);
        return ResponseEntity.ok(response);
    }
}
