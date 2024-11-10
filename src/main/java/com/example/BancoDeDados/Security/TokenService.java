package com.example.BancoDeDados.Security;

import com.example.BancoDeDados.Model.Professor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.secret}")
    private String secret;
    public String gerarToken(Professor professor){
        try {

        }catch (){

        }
    }
}
