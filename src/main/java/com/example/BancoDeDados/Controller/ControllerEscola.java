package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Repositores.EscolaRespositores;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import com.example.BancoDeDados.ResponseDTO.ELoginRespondeDTO;
import com.example.BancoDeDados.ResponseDTO.EscLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.EscolaResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorResponseDTO;
import com.example.BancoDeDados.Security.TokenService;
import com.example.BancoDeDados.Services.EmailService;
import com.example.BancoDeDados.Services.ServiceEscola;

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
@RequestMapping("/escola")
public class ControllerEscola {
    @Autowired
    private ServiceEscola serviceEscola;
    @Autowired
    private EscolaRespositores escolaRepositores;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    public ControllerEscola(TokenService tokenService, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, EscolaRespositores escolaRespositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.escolaRepositores = escolaRespositores;
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @PostMapping("/cadastro")
    public ResponseEntity<?> registrar(@RequestBody @Valid EscolaResponseDTO escolaRegristrarDto) {
        Escola escola = new Escola(escolaRegristrarDto);
        try {

            escola.setSenha(passwordEncoder.encode(escolaRegristrarDto.senha()));
            serviceEscola.criar(escola);

            String token = tokenService.gerarTokenEscola(escola);
            String assunto = "Confirmação de cadastro";
            String mensagem = String
                    .format("Olá " + escola.getNome() + " obrigado por se cadastrar no nosso site! ");
            emailService.enviarEmail(escola.getEmail(), assunto, mensagem);
            return ResponseEntity.ok(new EscLoginResponseDTO(token, escola.getNome(), escola.getRole()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar o estudante." + e.getMessage());
        }
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public String listar(Model model, Escola escola) {
        return model.addAttribute("escola", this.serviceEscola.listar(escola)).toString();
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/editar/{id}")
    public ResponseEntity<Escola> editar(@PathVariable Integer id) {
        Optional<Escola> escolaOpt = serviceEscola.editar(id);
        return escolaOpt.map(escola -> new ResponseEntity<>(escola, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (serviceEscola.deletar(id)) {
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
