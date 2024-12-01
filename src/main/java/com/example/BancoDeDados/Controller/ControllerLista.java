package com.example.BancoDeDados.Controller;

import com.example.BancoDeDados.Model.Lista;
import com.example.BancoDeDados.Model.Questao;
import com.example.BancoDeDados.Repositores.ListaRepository;
import com.example.BancoDeDados.ResponseDTO.ListaAddResponseDTO;
import com.example.BancoDeDados.ResponseDTO.ListaResponseDTO;
import com.example.BancoDeDados.ResponseDTO.QuestaoResponseDTO;
import com.example.BancoDeDados.Services.ListaService;
import com.example.BancoDeDados.Services.ServiceQuestao;
import com.example.BancoDeDados.Services.ServiceTratarRespostaIA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/listas")
public class ControllerLista {

    private final ListaService listaService;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ServiceQuestao serviceQuestao;

    @Autowired
    private ServiceTratarRespostaIA serviceTratarRespostaIA;

    public ControllerLista(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping("/salvar-questoes-do-pdf/{listaId}")
    public ListaAddResponseDTO salvarQuestoesDoPdf(@PathVariable Integer listaId) {
        List<Questao> questoesProcessadas = serviceTratarRespostaIA.processarRespostaIA();

        List<Questao> questoesSalvas = serviceQuestao.salvarQuestoes(questoesProcessadas);

        List<Integer> questaoIds = questoesSalvas.stream()
                .map(Questao::getId)
                .collect(Collectors.toList());

        listaService.adicionarQuestoes(listaId, questaoIds);

        return new ListaAddResponseDTO(listaId, questoesSalvas);
    }

    @PostMapping("/{listaId}/questoes")
    public ListaResponseDTO adicionarQuestao(@PathVariable Integer listaId,
                                             @RequestBody Questao questao) {
        Questao novaQuestao = serviceQuestao.criarQuestao(
                questao.getCabecalho(),
                questao.getEnunciado(),
                questao.getAlternativas(),
                questao.getGabarito()
        );
        System.out.println("Ver se a questão que está vindo daqui vem vazia"+questao.getEnunciado());
        ListaResponseDTO listaResponseDTO = listaService.adicionarQuestao(listaId, novaQuestao.getId());

        return listaResponseDTO;
    }

    @GetMapping("/{listaId}/questoes")
    public List<QuestaoResponseDTO> listarQuestoes(@PathVariable Integer listaId) {
        Lista lista = listaRepository.findById(Long.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        List<QuestaoResponseDTO> questoesDTO = lista.getQuestoes().stream()
                .map(questao -> new QuestaoResponseDTO(questao.getId(), questao.getCabecalho(),
                        questao.getEnunciado(), questao.getAlternativas(), questao.getGabarito()))
                .collect(toList());

        return questoesDTO;
    }

    @GetMapping("/professor/{professorId}")
    public List<ListaResponseDTO> buscarListasPorProfessor(@PathVariable Integer professorId) {
        return listaService.buscarListasPorProfessor(professorId);
    }

    @PostMapping
    public ListaResponseDTO criarLista(@RequestParam String titulo, @RequestParam Integer professorId) {
        return listaService.criarLista(titulo, professorId);
    }

    @PutMapping("/{listaId}")
    public ListaResponseDTO editarLista(@PathVariable Integer listaId, @RequestParam String novoTitulo) {
        return listaService.editarLista(listaId, novoTitulo);
    }

    @DeleteMapping("/{listaId}")
    public void excluirLista(@PathVariable Integer listaId) {
        listaService.excluirLista(listaId);
    }
}
