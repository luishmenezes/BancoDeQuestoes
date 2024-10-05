package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Services.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios") // Define a rota base para a API de usuários
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = serviceUsuario.criar(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public String listar(Model model, Usuario usuario ){
        return model.addAttribute("usuario", this.serviceUsuario.listar(usuario)).toString();
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = serviceUsuario.editar(id);
        return usuarioOpt.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (serviceUsuario.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Sucesso na exclusão
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ID não encontrado
        }
    }
}
