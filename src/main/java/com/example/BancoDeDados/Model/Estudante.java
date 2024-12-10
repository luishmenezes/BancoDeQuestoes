package com.example.BancoDeDados.Model;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import lombok.EqualsAndHashCode;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.BancoDeDados.ResponseDTO.EstudanteLoginResponseDTO;
import com.example.BancoDeDados.ResponseDTO.EstudanteResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ProfessorLoginResponseDTO;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "estudante")
@Table(name = "estudante")

public class Estudante implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date dataNascimento;

    @Column(nullable = false)
    private String instituicao;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;

    public Estudante(EstudanteResponseDTO estudantes) {
        this.nome = estudantes.nome();
        this.email = estudantes.email();
        this.senha = estudantes.senha();
        this.dataNascimento = estudantes.dataNascimento();
        this.instituicao = estudantes.instituicao();
    }

    public Estudante(EstudanteLoginResponseDTO estudanteLoginResponseDTO) {
        this.email = estudanteLoginResponseDTO.email();
        this.senha = estudanteLoginResponseDTO.senha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

}
