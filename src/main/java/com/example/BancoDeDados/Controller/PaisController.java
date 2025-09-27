package com.example.BancoDeDados.Controller;


import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Model.Pais;
import com.example.BancoDeDados.Repositores.PaisRepositores;
import com.example.BancoDeDados.ResponseDTO.EscLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.PaisLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.PaisResponseDTO;
import com.example.BancoDeDados.Security.TokenService;
import com.example.BancoDeDados.Services.EmailService;
import com.example.BancoDeDados.Services.PaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;
    @Autowired
    private PaisRepositores paisRepositores;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    public PaisController(TokenService tokenService, AuthenticationManager authenticationManager,
                            PasswordEncoder passwordEncoder, PaisRepositores paisRepositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.paisRepositores = paisRepositores;
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @PostMapping("/cadastro")
    public ResponseEntity<?> registrar(@RequestBody @Valid PaisResponseDTO paisResponseDto) {
        Pais pais = new Pais(paisResponseDto);
        try {

            pais.setSenha(passwordEncoder.encode(paisResponseDto.senha()));
            paisService.createPais(pais);

            String token = tokenService.gerarTokenPais(pais);
            String assunto = "Confirmação de cadastro";
            String mensagem = String
                    .format("Olá " + pais.getNome() + " obrigado por se cadastrar no nosso site! ");
            emailService.enviarEmail(pais.getEmail(), assunto, mensagem);
            return ResponseEntity.ok(new PaisLoginResponseDTO(token, pais.getNome(), pais.getRole()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar o Pai." + e.getMessage());
        }
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public String listar(Model model, Pais pais) {
        return model.addAttribute("pais", this.paisService.listarPais(pais)).toString();
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/editar/{id}")
    public ResponseEntity<Pais> editar(@PathVariable Integer id) {
        Optional<Pais> paisOpt = paisService.editar(id);
        return paisOpt.map(pais -> new ResponseEntity<>(pais, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (paisService.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String cleanedToken = token.replace("Bearer ", "");

        tokenService.revokeToken(cleanedToken);

        return ResponseEntity.ok("Logout realizado com sucesso");
    }
}
