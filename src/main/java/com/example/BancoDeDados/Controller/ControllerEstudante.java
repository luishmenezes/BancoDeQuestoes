package com.example.BancoDeDados.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BancoDeDados.Model.Estudante;
import com.example.BancoDeDados.Services.ServiceEstudante;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ControllerEstudante {

@Autowired
private ServiceEstudante serviceEstudante;

@GetMapping("/estudante/cadastrar")
public String CadastrarEstudante() {
    return "cadastroEstudante";
}

@PostMapping("/estudante/cadastrar")
public String CriarEstudante(Estudante estudante, RedirectAttributes attributes, Model model) {
serviceEstudante.criar(estudante);

    return "redirect:/estudante/cadastrar";

}


}
