package com.example.BancoDeDados.Repositores;


        import com.example.BancoDeDados.Model.Usuario;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Component;
        import java.util.Optional;
@Component
public interface UsuarioRepositores extends JpaRepository<Usuario, Integer> {

    // Método para procurar um usuário pelo endereço de e-mail
    public Optional<Usuario> findByEmail(String email);

    // Métodos para verificar a existência de um usuário pelo endereço de e-mail, CPF e número
    boolean existsByEmail(String email);
    @Query("SELECT u From Usuario u WHERE u.email LIKE :email")
    Usuario findByEmail2(@Param("email") String email);


}