package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import com.example.BancoDeDados.ResponseDTO.LoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorResponseDTO;
import com.example.BancoDeDados.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/professo")
public class ControllerProfessorLogin {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ProfessorRepositores professorRepositores;

    public ControllerProfessorLogin(TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ProfessorRepositores professorRepositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.professorRepositores = professorRepositores;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ProfessorLoginResponseDTO professorLoginResponseDTO) {
        Professor professor = this.professorRepositores.findByEmail(professorLoginResponseDTO.email()).orElseThrow(() -> new RuntimeException("usuario não encontrado"));
        if (passwordEncoder.matches(professorLoginResponseDTO.senha(), professor.getSenha())) {
            String token = this.tokenService.gerarToken(professor);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        return ResponseEntity.badRequest().build();

    }


}
