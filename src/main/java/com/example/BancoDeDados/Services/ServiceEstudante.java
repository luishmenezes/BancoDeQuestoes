package com.example.BancoDeDados.Services;

import java.util.List;
import java.lang.Integer;
import java.util.Optional;

import org.antlr.v4.runtime.misc.IntegerList;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Repositores.EstudanteRepositores;

@Service
public class ServiceEstudante {
    @Autowired
    private EstudanteRepositores estudanteRepositores;

    public Estudante criar(Estudante estudante) {
        return estudanteRepositores.save(estudante);
    }

    public List<Estudante> listaEstudantes(Estudante estudante){
    
        return estudanteRepositores.findAll();
    
    }

    public boolean deletar(Integer id){
        try {
            if (estudanteRepositores.existsById(id)) {
                estudanteRepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o Estudante: " + e.getMessage());
        }

    }

    public Optional<Estudante> editar(Integer id){
        try {
            return estudanteRepositores.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }

    }

}
