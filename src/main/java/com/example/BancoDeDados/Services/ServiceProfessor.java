package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProfessor {

    @Autowired
    private ProfessorRepositores professorRepositores;

    @Transactional
    public Professor criar(Professor professor) {
        try {
            return professorRepositores.save(professor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    public List<Professor> listar(Professor professor) {
        return professorRepositores.findAll();
    }

    public boolean deletar(Integer id) {
        try {
            if (professorRepositores.existsById(id)) {
                professorRepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    public Optional<Professor> editar(Integer id) {
        try {
            return professorRepositores.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }
}