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
            return usuarioRepositores.save(usuario);  // Salva o usuário no banco
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário: " + e.getMessage());  // Lança exceção em caso de falha
        }
    }

    public List<Usuario> listar(Usuario usuario) {
        return usuarioRepositores.findAll();
    }

    public boolean deletar(Integer id) {
        try {
            if (usuarioRepositores.existsById(id)) {  // Verifica se o ID existe antes de deletar
                usuarioRepositores.deleteById(id);
                return true;  // Retorna true caso a exclusão tenha sido bem-sucedida
            } else {
                return false;  // Retorna false caso o usuário não seja encontrado
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    public Optional<Usuario> editar(Integer id) {
        try {
            return usuarioRepositores.findById(id);  // Busca o usuário pelo ID
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }
}
