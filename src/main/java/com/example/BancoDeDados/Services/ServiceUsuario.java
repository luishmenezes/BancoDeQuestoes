package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Usuario;
import com.example.BancoDeDados.Repositores.UsuarioRepositores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepositores usuarioRepositores;

    @Transactional
    public Usuario criar(Usuario usuario) {
        try {
            return usuarioRepositores.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listar(Usuario usuario) {
        return usuarioRepositores.findAll();
    }

    public boolean deletar(Integer id) {
        try {
            if (usuarioRepositores.existsById(id)) {
                usuarioRepositores.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    public Optional<Usuario> editar(Integer id) {
        try {
            return usuarioRepositores.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }

    public long contarUsuarios() {
        return usuarioRepositores.count();
    }

}