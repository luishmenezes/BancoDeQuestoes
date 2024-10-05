package com.example.BancoDeDados.Model;

import com.example.BancoDeDados.ResponseDTO.UsuarioResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of="id")
@Entity(name="usuario")
@Table(name="usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;


    public Usuario(UsuarioResponseDTO usuarioDTO){
     this.nome=usuarioDTO.nome();
     this.email=usuarioDTO.email();
     this.senha=usuarioDTO.senha();
     this.dataNascimento=usuarioDTO.dataNascimento();
    }

}
