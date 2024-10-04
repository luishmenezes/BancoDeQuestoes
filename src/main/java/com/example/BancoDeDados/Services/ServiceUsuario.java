package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Repositores.UsuarioRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BancoDeDados.extencao.UserInvalid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioRepositores usuarioRepositores;

    private List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public void salvando(Usuario usuario) throws UserInvalid {
        if (usuario.getEmail().trim().isEmpty() || usuario.getNome().trim().isEmpty()
        ) {
            throw new UserInvalid("Os campos obrigatórios não podem estar vazio.");
        }
        if (this.usuarioRepositores.existsByEmail(usuario.getEmail())){
            throw new UserInvalid("Email já cadastrado");
        }

    }
    public Usuario criar(Usuario usuario) {
        return usuarioRepositores.save(usuario);

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
