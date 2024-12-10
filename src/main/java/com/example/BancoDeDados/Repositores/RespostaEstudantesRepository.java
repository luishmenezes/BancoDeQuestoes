package com.example.BancoDeDados.Repository;

import com.example.BancoDeDados.Model.RespostaEstudantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespostaEstudantesRepository extends JpaRepository<RespostaEstudantes, Long> {
    Optional<RespostaEstudantes> findByQuestaoIdAndEstudanteId(Long questaoId, Long estudanteId);
}
