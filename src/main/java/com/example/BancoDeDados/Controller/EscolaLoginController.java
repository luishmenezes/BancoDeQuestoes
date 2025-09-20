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

import com.example.BancoDeDados.Model.Escola;

import com.example.BancoDeDados.Repositores.EscolaRespositores;

import com.example.BancoDeDados.ResponseDTO.EscLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.EscolaLoginResponseDTO;

import com.example.BancoDeDados.Security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/escola")
public class EscolaLoginController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EscolaRespositores escolaRespositores;

    public EscolaLoginController(TokenService tokenService, AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder, EscolaRespositores escolaRespositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.escolaRespositores = escolaRespositores;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid EscolaLoginResponseDTO escolaLoginResponseDTO) {
        try {
            Escola escola = this.escolaRespositores.findByEmail(escolaLoginResponseDTO.email())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if (passwordEncoder.matches(escolaLoginResponseDTO.senha(), escola.getSenha())) {
                String token = this.tokenService.gerarTokenEscola(escola);

                // Retorna o token e o nome do professor
                return ResponseEntity.ok(new EscLoginResponseDTO(token, escola.getNome(), escola.getRole()));
            }

            return ResponseEntity.badRequest().body("Credenciais inválidas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login.");
        }
    }
}
