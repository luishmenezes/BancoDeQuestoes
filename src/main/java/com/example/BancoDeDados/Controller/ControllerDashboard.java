package com.example.BancoDeDados.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
public class ControllerDashboard {

    @GetMapping("/api/dashboard")
    public Map<String, Object> getDashboardData() {
        return Map.of(
                "usuariosAtivos", 125,
                "Alunos", 91,
                "Professores", 34
        );
    }
}
