package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.ResponseDTO.DisciplinaRequestDTO;
import com.example.BancoDeDados.ResponseDTO.DisciplinaResponseDTO;
import com.example.BancoDeDados.Services.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criar(@RequestBody DisciplinaRequestDTO dto) {
        var disciplina = disciplinaService.criar(dto);
        return ResponseEntity.ok(new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getProfessor().getNome(),
                disciplina.getEscola().getNome(),
                disciplina.getAlunos().stream().map(a -> a.getNome()).toList()
        ));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listar() {
        return ResponseEntity.ok(disciplinaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return disciplinaService.buscarPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/escola/{nomeEscola}")
    public ResponseEntity<List<DisciplinaResponseDTO>> buscarPorEscola(@PathVariable String nomeEscola) {
        return ResponseEntity.ok(disciplinaService.buscarPorEscola(nomeEscola));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
