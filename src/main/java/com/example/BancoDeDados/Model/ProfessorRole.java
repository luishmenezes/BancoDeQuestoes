package com.example.BancoDeDados.Model;

public enum ProfessorRole {

    PROFESSOR("professor"),
    USER("user");

    private String role;

    ProfessorRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
