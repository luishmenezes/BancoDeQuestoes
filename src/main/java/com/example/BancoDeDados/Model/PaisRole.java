package com.example.BancoDeDados.Model;

public enum PaisRole {

    PAIS("pais"),
    USER("user");

    private String role;

    PaisRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
