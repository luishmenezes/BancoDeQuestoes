package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import com.example.BancoDeDados.ResponseDTO.PLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorLoginResponseDTO;
import com.example.BancoDeDados.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorLoginController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ProfessorRepositores professorRepositores;

    public ProfessorLoginController(TokenService tokenService, AuthenticationManager authenticationManager,
                                    PasswordEncoder passwordEncoder, ProfessorRepositores professorRepositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.professorRepositores = professorRepositores;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid ProfessorLoginResponseDTO professorLoginResponseDTO) {
        try {
            Professor professor = this.professorRepositores.findByEmail(professorLoginResponseDTO.email())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if (passwordEncoder.matches(professorLoginResponseDTO.senha(), professor.getSenha())) {
                String token = this.tokenService.gerarTokenProfessor(professor);

                // Retorna o token e o nome do professor
                return ResponseEntity.ok(new PLoginResponseDTO(professor.getId(), token, professor.getNome(), professor.getRole()));
            }

            return ResponseEntity.badRequest().body("Credenciais inválidas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login.");
        }
    }

}
