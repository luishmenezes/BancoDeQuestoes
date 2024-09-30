package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceUsuario {
    private List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public Usuario criarUsuario(Integer id, String nome, String email, String senha, Date dataNascimento) {
        Usuario novoUsuario = new Usuario(proximoId++, nome, email, senha, dataNascimento);
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuario(int id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst().orElse(null);
    }

    public Usuario atualizarUsuario(int id, String nome, String email,String senha, Date dataNascimento) {
        Usuario usuarioExistente = buscarUsuario(id);
        if (usuarioExistente != null) {
            usuarioExistente.setNome(nome);
            usuarioExistente.setEmail(email);
            usuarioExistente.setSenha(senha);
            usuarioExistente.setDataNascimento(dataNascimento);
            return usuarioExistente;
        }
        return null;
    }

    public boolean removerUsuario(int id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }
}
