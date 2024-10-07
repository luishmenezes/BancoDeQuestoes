package com.example.BancoDeDados.Services;

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

}
