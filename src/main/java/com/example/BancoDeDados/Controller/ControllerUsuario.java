package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Services.ServiceUsuario;
import com.example.BancoDeDados.extencao.UserInvalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;


    @GetMapping("/usuario/cadastro")
    public String cadastro(){
        return "index";
    }

    @PostMapping("/usuario/cadastro")
    public String criar(Usuario usuario, RedirectAttributes ra, Model model)  {

        try {
            // Criptografa a senha do usuário antes de salvá-lo no banco de dados

            serviceUsuario.salvando(usuario);
            serviceUsuario.criar(usuario);
            return "redirect:/usuario/cadastro";
        }

        catch (UserInvalid e) {
            // Se ocorrer algum erro de validação, exibe a mensagem de erro e redireciona de volta para a página de cadastro
            ra.addFlashAttribute("msgError", e.getMessage());
        }
        return "apenas faça:L";
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
