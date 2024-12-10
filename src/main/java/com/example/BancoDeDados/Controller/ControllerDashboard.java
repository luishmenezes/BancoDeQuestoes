package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.ResponseDTO.DashboardDTO;
import com.example.BancoDeDados.Services.ServiceEscola;
import com.example.BancoDeDados.Services.ServiceQuestao;
import com.example.BancoDeDados.Services.ServiceProfessor;
import com.example.BancoDeDados.Services.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
public class ControllerDashboard {

    @Autowired
    private ServiceQuestao serviceQuestao;

    @Autowired
    private ServiceProfessor serviceProfessor;

    @Autowired
    private ServiceEscola serviceEscola;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/api/dashboard")
    public DashboardDTO getDashboardData() {
        long totalQuestoes = serviceQuestao.contarQuestoes();
        long totalProfessores = serviceProfessor.contarProfessores();
        long totalEscolas = serviceEscola.contarEscolas();
        long totalUsuarios = serviceUsuario.contarUsuarios();

        Map<String, Long> professoresPorInstituicao = serviceProfessor.contarProfessoresPorInstituicao();
        Map<String, Long> contarProfessoresPorMaterias = serviceProfessor.contarProfessoresPorMaterias();
        Map<String, Long> contarProfessoresPorMaterias2 = serviceProfessor.contarProfessoresPorMaterias2();


        return new DashboardDTO(totalQuestoes, totalProfessores, totalEscolas, totalUsuarios, professoresPorInstituicao, contarProfessoresPorMaterias, contarProfessoresPorMaterias2);
    }
}
