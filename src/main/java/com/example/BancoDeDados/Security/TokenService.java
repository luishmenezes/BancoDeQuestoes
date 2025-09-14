package com.example.BancoDeDados.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Professor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {

    private final Set<String> revokedTokens = new HashSet();

    private String secret = "1234";

    public String gerarTokenProfessor(Professor professor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("BancoDeQuestoes")
                    .withSubject(professor.getEmail())
                    .withExpiresAt(this.generateTokenExpiration())

                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String gerarTokenEstudante(Estudante estudante) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("BancoDeQuestoes")
                    .withSubject(estudante.getEmail())
                    .withExpiresAt(this.generateTokenExpiration())

                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String gerarTokenEscola(Escola escola) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("BancoDeQuestoes")
                    .withSubject(escola.getEmail())
                    .withExpiresAt(this.generateTokenExpiration())

                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String validarToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("BancoDeQuestoes")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateTokenExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public void revokeToken(String token) {
        revokedTokens.add(token);
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }
}
