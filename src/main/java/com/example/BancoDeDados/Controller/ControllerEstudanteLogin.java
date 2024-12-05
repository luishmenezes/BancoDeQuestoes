package com.example.BancoDeDados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BancoDeDados.Model.Estudante;

import com.example.BancoDeDados.Repositores.EstudanteRepositores;

import com.example.BancoDeDados.ResponseDTO.EstudanteLoginResponseDTO;
import com.example.BancoDeDados.Security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudante")
public class ControllerEstudanteLogin {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EstudanteRepositores estudanteRepositores;

    public ControllerEstudanteLogin(TokenService tokenService, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, EstudanteRepositores estudanteRepositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.estudanteRepositores = estudanteRepositores;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid EstudanteLoginResponseDTO estudanteLoginResponseDTO) {
        try {
            Estudante estudante = this.estudanteRepositores.findByEmail(estudanteLoginResponseDTO.email())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if (passwordEncoder.matches(estudanteLoginResponseDTO.senha(), estudante.getSenha())) {
                String token = this.tokenService.gerarTokenEstudante(estudante);

                // Retorna o token e o nome do professor
                return ResponseEntity.ok(new EstudanteLoginResponseDTO(token, estudante.getNome()));
            }

            return ResponseEntity.badRequest().body("Credenciais inválidas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login.");
        }
    }
}
