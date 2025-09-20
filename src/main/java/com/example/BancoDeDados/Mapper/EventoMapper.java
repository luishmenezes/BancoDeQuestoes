package com.example.BancoDeDados.Mapper;


import com.example.BancoDeDados.Model.Evento;
import com.example.BancoDeDados.Model.Materia;
import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.ResponseDTO.EventoComNotasResponse;
import com.example.BancoDeDados.ResponseDTO.EventoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventoMapper {

    public EventoComNotasResponse toResponse(Evento evento) {
        EventoComNotasResponse response = new EventoComNotasResponse();
        response.setId(evento.getId());
        response.setTitulo(evento.getTitulo());
        response.setDescricao(evento.getDescricao());
        response.setNotaMaxima(evento.getNotaMaxima());
        response.setData(evento.getData());
        response.setMateriaId(evento.getMateria().getId());
        response.setMateriaNome(evento.getMateria().getNome());
        response.setNotasEstudantes(new ArrayList<>());

        return response;
    }

    public Evento toEntity(EventoRequest dto, Materia materia, Professor professor) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setNotaMaxima(dto.getNotaMaxima());
        evento.setData(dto.getData());
        evento.setArquivos(dto.getArquivos());
        evento.setMateria(materia);
        evento.setProfessor(professor);

        return evento;
    }
}
