package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Services.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;


    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = serviceUsuario.criarUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataNascimento());
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(serviceUsuario.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        Usuario usuario = serviceUsuario.buscarUsuario(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = serviceUsuario.atualizarUsuario(id, usuario.getNome(), usuario.getEmail(),usuario.getSenha(),usuario.getDataNascimento());
        return usuarioAtualizado != null ? ResponseEntity.ok(usuarioAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable int id) {
        boolean removido = serviceUsuario.removerUsuario(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
