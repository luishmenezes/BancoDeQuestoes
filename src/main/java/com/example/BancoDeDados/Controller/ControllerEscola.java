package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Escola;
import com.example.BancoDeDados.Repositores.EscolaRespositores;
import com.example.BancoDeDados.ResponseDTO.EscolaResponseDTO;
import com.example.BancoDeDados.Services.ServiceEscola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/escola")
public class ControllerEscola {
    @Autowired
    private ServiceEscola serviceEscola;
    @Autowired
    private EscolaRespositores escolaRepositores;

    @CrossOrigin(originPatterns = "*",allowedHeaders = "*")
    @PostMapping("/cadastro")
    public void criar(@RequestBody EscolaResponseDTO escola) {
        Escola escolaDTO=new Escola(escola);
        escolaRepositores.save(escolaDTO);
        return;
    }   

    @CrossOrigin(originPatterns = "*",allowedHeaders = "*")
    @GetMapping("/listar")
    public String listar(Model model, Escola escola ){
        return model.addAttribute("escola", this.serviceEscola.listar(escola)).toString();
    }

    @CrossOrigin(originPatterns = "*",allowedHeaders = "*")
    @GetMapping("/editar/{id}")
    public ResponseEntity<Escola> editar(@PathVariable Integer id) {
        Optional<Escola> escolaOpt = serviceEscola.editar(id);
        return escolaOpt.map(escola -> new ResponseEntity<>(escola, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(originPatterns = "*",allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (serviceEscola.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
