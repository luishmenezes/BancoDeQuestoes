package com.example.BancoDeDados.Model;

import org.springframework.aot.generate.GeneratedTypeReference;

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
@Entity(name="estudante")
@Table(name="estudante")
@NoArgsConstructor
@AllArgsConstructor


public class Estudante {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)    
private Integer id;

@Column(nullable = false )
private String nome;
@Column (nullable = false)
private int idade;
@Column (nullable = false, unique = true)
private String email;
 


}
