package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEscola {
    @Autowired
    private EstudanteRepositores estudanteRepositores;

    @Transactional
    public Estudante criar(Estudante estudante) {
        try {
            return estudanteRepositores.save(estudante);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    public List<Estudante> listar(Estudante estudante) {
        return estudanteRepositores.findAll();
    }

    public boolean deletar(Integer id) {
        try {
            if (estudanteRepositores.existsById(id)) {
                estudanteRepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    public Optional<Estudante> editar(Integer id) {
        try {
            return estudanteRepositores.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }
}
