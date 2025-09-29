package com.example.BancoDeDados.ResponseDTO;

import java.util.*;

public class InstituicaoResponse {

    private UUID id;
    private String nome;
    private String email;
    private String endereco;
    private String role;
    private List<String> emailsPermitidos;

    public InstituicaoResponse(UUID id, String nome, String email, String endereco, String role, List<String> emailsPermitidos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.role = role;
        this.emailsPermitidos = emailsPermitidos;
    }

    // Getters
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public String getRole() { return role; }
    public List<String> getEmailsPermitidos() { return emailsPermitidos; }
}
