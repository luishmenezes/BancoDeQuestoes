package com.example.BancoDeDados.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BancoDeDados.Model.Materia;
import com.example.BancoDeDados.Repositores.MateriaRepositores;

@Service
public class ServiceMateria {

    @Autowired
    private MateriaRepositores materiarepositores;

    public List<Materia> listarMaterias() {
        return materiarepositores.findAll();
    }

    public Materia adicionarMateria(Materia materia) {

        return materiarepositores.save(materia);
    }

    public boolean deletarMateria(UUID id) {
        try {
            if (materiarepositores.existsById(id)) {
                materiarepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a materia: " + e.getMessage());
        }
    }

}
