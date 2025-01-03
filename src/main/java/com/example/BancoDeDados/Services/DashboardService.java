package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Lista;
import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.ListaRepository;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ListaRepository listaRepository;
    private final ProfessorRepositores professorRepository;

    @Autowired
    public DashboardService(ListaRepository listaRepository, ProfessorRepositores professorRepository) {
        this.listaRepository = listaRepository;
        this.professorRepository = professorRepository;
    }

    // Obter dashboard por ID da lista
    public Map<String, Object> getDashboardByListaId(Long listaId) {
        Optional<Lista> listaOpt = listaRepository.findById(listaId);
        if (listaOpt.isEmpty()) {
            throw new RuntimeException("Lista não encontrada para o ID: " + listaId);
        }

        Lista lista = listaOpt.get();

        // Construir o dashboard
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("listaId", lista.getId());
        dashboard.put("tituloLista", lista.getTitulo());
        dashboard.put("professor", lista.getProfessor().getNome());
        dashboard.put("questoes", lista.getQuestoes().stream()
                .map(Questao::getCabecalho)
                .collect(Collectors.toList()));
        dashboard.put("estudantes", lista.getEstudantes().stream()
                .map(estudante -> Map.of(
                        "id", estudante.getId(),
                        "nome", estudante.getNome()
                ))
                .collect(Collectors.toList()));

        return dashboard;
    }

    // Obter dashboard por ID do professor e ID da lista
    public Map<String, Object> getDashboardByProfessorAndLista(Long professorId, Long listaId) {
        Optional<Professor> professorOpt = professorRepository.findById(Math.toIntExact(professorId));
        if (professorOpt.isEmpty()) {
            throw new RuntimeException("Professor não encontrado para o ID: " + professorId);
        }

        Professor professor = professorOpt.get();

        Optional<Lista> listaOpt = listaRepository.findById(listaId);
        if (listaOpt.isEmpty()) {
            throw new RuntimeException("Lista não encontrada para o ID: " + listaId);
        }

        Lista lista = listaOpt.get();

        if (!lista.getProfessor().getId().equals(professor.getId())) {
            throw new RuntimeException("A lista não pertence ao professor com ID: " + professorId);
        }

        // Construir o dashboard
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("listaId", lista.getId());
        dashboard.put("tituloLista", lista.getTitulo());
        dashboard.put("professor", professor.getNome());
        dashboard.put("questoes", lista.getQuestoes().stream()
                .map(Questao::getCabecalho)
                .collect(Collectors.toList()));
        dashboard.put("estudantes", lista.getEstudantes().stream()
                .map(estudante -> Map.of(
                        "id", estudante.getId(),
                        "nome", estudante.getNome()
                ))
                .collect(Collectors.toList()));

        return dashboard;
    }
}
