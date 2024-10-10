package com.example.BancoDeDados.Repositores;


        import com.example.BancoDeDados.Model.Usuario;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Component;
@Component
public interface UsuarioRepositores extends JpaRepository<Usuario, Integer> {

}