package com.example.BancoDeDados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BancoDeDados.Model.Materia;
import com.example.BancoDeDados.ResponseDTO.MateriaResponseDTO;
import com.example.BancoDeDados.Services.ServiceMateria;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/escola/materias")
@CrossOrigin(origins = "*") // Permite requisições do React
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
    public void deletarMateria(@PathVariable Long id) {
        materiaService.deletarMateria(id);
    }
}
