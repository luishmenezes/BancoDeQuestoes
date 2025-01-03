package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Lista;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Model.RespostaEstudantes;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;
import com.example.BancoDeDados.Repositores.ListaRepository;
import com.example.BancoDeDados.Repositores.QuestaoRepositores;
import com.example.BancoDeDados.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Endpoint para obter o dashboard de uma lista específica
    @GetMapping("/lista/{listaId}")
    public ResponseEntity<?> getDashboardByLista(@PathVariable Long listaId) {
        try {
            var dashboard = dashboardService.getDashboardByListaId(listaId);
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    // Endpoint para obter o dashboard com base no professor e na lista
    @GetMapping("/professor/{professorId}/lista/{listaId}")
    public ResponseEntity<?> getDashboardByProfessorAndLista(
            @PathVariable Long professorId,
            @PathVariable Long listaId) {
        try {
            var dashboard = dashboardService.getDashboardByProfessorAndLista(professorId, listaId);
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }
}
