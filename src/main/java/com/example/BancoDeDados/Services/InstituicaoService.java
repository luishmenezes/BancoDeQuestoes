package com.example.BancoDeDados.Services;


import com.example.BancoDeDados.ResponseDTO.InstituicaoRequest;
import com.example.BancoDeDados.ResponseDTO.InstituicaoResponse;
import com.example.BancoDeDados.Exceptions.EmailJaCadastradoException;
import com.example.BancoDeDados.Model.Instituicao;
import com.example.BancoDeDados.Repositores.InstituicaoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public InstituicaoResponse cadastrarInstituicao(InstituicaoRequest request) {
        if (instituicaoRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já está em uso!");
        }

        Instituicao instituicao = new Instituicao();
        instituicao.setNome(request.getNome());
        instituicao.setEmail(request.getEmail());
        instituicao.setSenha(passwordEncoder.encode(request.getSenha()));
        instituicao.setEndereco(request.getEndereco());

        Instituicao saved = instituicaoRepository.save(instituicao);

        return new InstituicaoResponse(
                saved.getId(),
                saved.getNome(),
                saved.getEmail(),
                saved.getEndereco(),
                saved.getRole(),
                saved.getEmailsPermitidos()
        );
    }
}
