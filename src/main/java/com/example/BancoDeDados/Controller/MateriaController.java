package com.example.BancoDeDados.Controller;

import java.util.List;
import java.util.UUID;

import com.example.BancoDeDados.Repositores.EscolaRespositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BancoDeDados.Model.Materia;
import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.ResponseDTO.MateriaResponseDTO;
import com.example.BancoDeDados.Services.MateriaService;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;


@RestController
@RequestMapping("/escola/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ProfessorRepositores professorRepository;

    @Autowired
    private EscolaRespositores escolaRepository;

    @GetMapping
    public List<Materia> listarMaterias() {
        return materiaService.listarMaterias();
    }

    @PostMapping
    public Materia adicionarMateria(@RequestBody MateriaResponseDTO materiaResponseDTO) {
        Professor professor = professorRepository.findById(materiaResponseDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Escola escola = escolaRepository.findById(materiaResponseDTO.getEscolaId())
                .orElseThrow(() -> new RuntimeException("Escola não encontrada"));

        Materia materia = new Materia(materiaResponseDTO, professor, escola);
        return materiaService.adicionarMateria(materia);
    }

    @DeleteMapping("/{id}")
    public void deletarMateria(@PathVariable UUID id) {
        materiaService.deletarMateria(id);
    }
}