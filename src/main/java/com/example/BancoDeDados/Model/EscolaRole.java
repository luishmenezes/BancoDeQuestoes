package com.example.BancoDeDados.Model;

public enum EscolaRole {
    ESCOLA("escola"),
    USER("user");

    private String role;

    EscolaRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
