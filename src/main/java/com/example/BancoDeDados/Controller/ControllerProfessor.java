package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import com.example.BancoDeDados.ResponseDTO.LoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorResponseDTO;
import com.example.BancoDeDados.Security.TokenService;
import com.example.BancoDeDados.Services.ServiceProfessor;

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
@RequestMapping("/professor")
public class ControllerProfessor {

    @Autowired
    private ServiceProfessor serviceProfessor;
    @Autowired
    private ProfessorRepositores professorRepositores;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    public ControllerProfessor(TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ProfessorRepositores professorRepositores) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.professorRepositores = professorRepositores;
    }


    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid ProfessorResponseDTO professorRegistrarDTO) {
        try {
            Optional<Professor> professorExistente = professorRepositores.findByEmail(professorRegistrarDTO.email());
            if (professorExistente.isPresent()) {
                return ResponseEntity.badRequest().body("Email já cadastrado.");
            }

            Professor novoProfessor = new Professor(professorRegistrarDTO);

            if (!serviceProfessor.validarEmail(professorRegistrarDTO.email())) {
                return ResponseEntity.badRequest().body("Email inválido.");
            }

            if (!serviceProfessor.validarSenha(professorRegistrarDTO.senha())) {
                return ResponseEntity.badRequest().body(
                        "A senha deve ter no mínimo 8 caracteres, incluindo letras maiúsculas, minúsculas e números.");
            }

            novoProfessor.setSenha(passwordEncoder.encode(professorRegistrarDTO.senha()));

            serviceProfessor.criar(novoProfessor);

            String token = tokenService.gerarToken(novoProfessor);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o professor.");
        }
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public String listar(Model model, Professor professor) {
        return model.addAttribute("professor", this.serviceProfessor.listar(professor)).toString();
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/editar/{id}")
    public ResponseEntity<Professor> editar(@PathVariable Integer id) {
        Optional<Professor> professorOpt = serviceProfessor.editar(id);
        return professorOpt.map(professor -> new ResponseEntity<>(professor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (serviceProfessor.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}