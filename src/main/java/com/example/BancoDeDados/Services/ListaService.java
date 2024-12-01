package com.example.BancoDeDados.Services;

import com.example.BancoDeDados.Model.Lista;
import com.example.BancoDeDados.Model.Professor;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.ListaRepository;
import com.example.BancoDeDados.Repositores.ProfessorRepositores;
import com.example.BancoDeDados.Repositores.QuestaoRepositores;
import com.example.BancoDeDados.ResponseDTO.ListaResponseDTO;
import com.example.BancoDeDados.ResponseDTO.QuestaoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private QuestaoRepositores questaoRepository;

    @Autowired
    private ProfessorRepositores professorRepository;

    public ListaResponseDTO adicionarQuestao(Integer listaId, Integer questaoId) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        Questao questao = questaoRepository.findById(questaoId)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));

        lista.getQuestoes().add(questao);
        Lista updatedLista = listaRepository.save(lista);

        return convertToDTO(updatedLista);
    }

    public ListaResponseDTO adicionarQuestoes(Integer listaId, List<Integer> questaoIds) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        for (Integer questaoId : questaoIds) {
            Questao questao = questaoRepository.findById(questaoId)
                    .orElseThrow(() -> new RuntimeException("Questão não encontrada"));
            lista.getQuestoes().add(questao);
        }

        Lista updatedLista = listaRepository.save(lista);

        return convertToDTO(updatedLista);
    }

    public List<QuestaoResponseDTO> buscarQuestoesPorLista(Integer listaId) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        return lista.getQuestoes().stream()
                .map(questao -> new QuestaoResponseDTO(
                        questao.getId(),
                        questao.getCabecalho(),
                        questao.getEnunciado(),
                        questao.getAlternativas(),
                        questao.getGabarito()
                ))
                .collect(Collectors.toList());
    }

    public ListaResponseDTO removerQuestao(Integer listaId, Integer questaoId) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        Questao questao = lista.getQuestoes().stream()
                .filter(q -> q.getId().equals(questaoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Questão não encontrada na lista"));

        lista.getQuestoes().remove(questao);
        Lista updatedLista = listaRepository.save(lista);

        return convertToDTO(updatedLista);
    }

    public ListaResponseDTO criarLista(String titulo, Integer professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Lista lista = new Lista();
        lista.setTitulo(titulo);
        lista.setProfessor(professor);

        return convertToDTO(listaRepository.save(lista));
    }

    public ListaResponseDTO editarLista(Integer listaId, String novoTitulo) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        lista.setTitulo(novoTitulo);
        return convertToDTO(listaRepository.save(lista));
    }

    public void excluirLista(Integer listaId) {
        if (!listaRepository.existsById(Long.valueOf(listaId))) {
            throw new RuntimeException("Lista não encontrada");
        }
        listaRepository.deleteById(Long.valueOf(listaId));
    }

    public List<ListaResponseDTO> buscarListasPorProfessor(Integer professorId) {
        List<Lista> listas = listaRepository.findByProfessorId(professorId);
        return listas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    private ListaResponseDTO convertToDTO(Lista lista) {
        return new ListaResponseDTO(
                lista.getId().intValue(),
                lista.getTitulo(),
                lista.getProfessor().getNome()
        );
    }
}
