package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Account;
import com.example.BancoDeDados.Repositores.AccountRepository;
import com.example.BancoDeDados.Security.TokenService;
import com.example.BancoDeDados.ResponseDTO.LoginRequestDTO;
import com.example.BancoDeDados.ResponseDTO.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AccountRepository accountRepository;

    //teste
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        try {
            Account account = accountRepository.findByEmail(dto.email())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if (!passwordEncoder.matches(dto.senha(), account.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
            }

            String token = tokenService.gerarToken(account);

            LoginResponseDTO response = new LoginResponseDTO(
                    account.getId(),
                    account.getEmail(),
                    token
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no processo de login: " + e.getMessage());
        }
    }
}
