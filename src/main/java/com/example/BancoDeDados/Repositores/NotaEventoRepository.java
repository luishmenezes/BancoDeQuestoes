package com.example.BancoDeDados.Repositores;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Model.Evento;
import com.example.BancoDeDados.Model.NotaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotaEventoRepository extends JpaRepository<NotaEvento, UUID> {


    Optional<NotaEvento> findByEstudanteAndEvento(Estudante estudante, Evento evento);

    // Buscar todas as notas de um evento
    List<NotaEvento> findByEvento(Evento evento);
}
