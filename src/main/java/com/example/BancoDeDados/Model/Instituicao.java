package com.example.BancoDeDados.Model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "instituicoes")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String role = "INSTITUICAO";

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> emailsPermitidos = new ArrayList<>();

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<String> getEmailsPermitidos() { return emailsPermitidos; }
    public void setEmailsPermitidos(List<String> emailsPermitidos) { this.emailsPermitidos = emailsPermitidos; }
}
