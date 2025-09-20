package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Repositores.EscolaRespositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EscolaService {
    @Autowired
    private EscolaRespositores escolaRepositores;

    @Transactional
    public Escola criar(Escola escola) {
        try {
            return escolaRepositores.save(escola);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    public List<Escola> listar(Escola escola) {
        return escolaRepositores.findAll();
    }

    public boolean deletar(Integer id) {
        try {
            if (escolaRepositores.existsById(id)) {
                escolaRepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    public Optional<Escola> editar(Integer id) {
        try {
            return escolaRepositores.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }
}
