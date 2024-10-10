package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Repositores.UsuarioRepositores;
import com.example.BancoDeDados.ResponseDTO.UsuarioResponseDTO;
import com.example.BancoDeDados.Services.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;
    @Autowired
    private UsuarioRepositores usuarioRepositores;

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @PostMapping("/cadastro")
    public void criar(@RequestBody UsuarioResponseDTO usuario) {
        Usuario usuarioDTO = new Usuario(usuario);
        usuarioRepositores.save(usuarioDTO);
        return;
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public String listar(Model model, Usuario usuario) {
        return model.addAttribute("usuario", this.serviceUsuario.listar(usuario)).toString();
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @GetMapping("/editar/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = serviceUsuario.editar(id);
        return usuarioOpt.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(originPatterns = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (serviceUsuario.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}