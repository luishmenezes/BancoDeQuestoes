package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.*;
//import com.example.BancoDeDados.Repositores.EscolaRepository;
import com.example.BancoDeDados.Repositores.*;
import com.example.BancoDeDados.ResponseDTO.DisciplinaRequestDTO;
import com.example.BancoDeDados.ResponseDTO.DisciplinaResponseDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepositores professorRepository;
    private final EscolaRespositores escolaRepository;
    private final EstudanteRepositores estudanteRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository,
                             ProfessorRepositores professorRepository,
                             EscolaRespositores escolaRepository,
                             EstudanteRepositores estudanteRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.escolaRepository = escolaRepository;
        this.estudanteRepository = estudanteRepository;
    }

    @Transactional
    public Disciplina criar(DisciplinaRequestDTO dto) {
        Escola escola = escolaRepository.findById(dto.escolaId())
                .orElseThrow(() -> new IllegalArgumentException("Escola não encontrada"));

        Professor professor = professorRepository.findByEmail(dto.emailProfessor())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        if (!professor.getEscola().equals(escola)) {
            throw new IllegalArgumentException("Professor não pertence à escola informada");
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setEscola(escola);
        disciplina.setProfessor(professor);

        return disciplinaRepository.save(disciplina);
    }

    public List<DisciplinaResponseDTO> listar() {
        return disciplinaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<DisciplinaResponseDTO> buscarPorId(UUID id) {
        return disciplinaRepository.findById(id).map(this::mapToDTO);
    }

    public List<DisciplinaResponseDTO> buscarPorEscola(String nomeEscola) {
        return disciplinaRepository.findByEscola_Nome(nomeEscola)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deletar(UUID id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        disciplina.getAlunos().clear();
        disciplinaRepository.delete(disciplina);
    }

    private DisciplinaResponseDTO mapToDTO(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getProfessor().getNome(),
                disciplina.getEscola().getNome(),
                disciplina.getAlunos().stream().map(Estudante::getNome).collect(Collectors.toList())
        );
    }
}
