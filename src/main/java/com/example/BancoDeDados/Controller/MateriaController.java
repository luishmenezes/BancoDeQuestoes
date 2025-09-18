package com.example.BancoDeDados.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BancoDeDados.Model.Materia;
import com.example.BancoDeDados.ResponseDTO.MateriaResponseDTO;
import com.example.BancoDeDados.Services.ServiceMateria;

@RestController
@RequestMapping("/escola/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private ServiceMateria materiaService;

    @GetMapping
    public List<Materia> listarMaterias() {
        return materiaService.listarMaterias();
    }

    @PostMapping
    public Materia adicionarMateria(@RequestBody MateriaResponseDTO materiaResponseDTO) {
        Materia materia = new Materia(materiaResponseDTO);
        return materiaService.adicionarMateria(materia);
    }


    @DeleteMapping("/{id}")
    public void deletarMateria(@PathVariable UUID id) {
        materiaService.deletarMateria(id);
    }
}
