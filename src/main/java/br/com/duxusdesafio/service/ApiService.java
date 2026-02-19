package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.TimeRequestDTO;
import br.com.duxusdesafio.dto.TimeResponseDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;

    public ApiService(TimeRepository timeRepository, IntegranteRepository integranteRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
    }

    // Construtor apenas para testes unitários
    public ApiService() {
        this.timeRepository = null;
        this.integranteRepository = null;
    }

    public Time timeDaData(LocalDate data, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!

        if (data == null || todosOsTimes == null) {
            throw new IllegalArgumentException("Data e lista não podem ser nulas");
        }

        return todosOsTimes.stream()
                .filter(time -> data.equals(time.getData()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!

        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }

        Map<Integrante, Integer> contador = new HashMap<>();

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroPeriodo = true;

            if (dataInicial != null && dataTime.isBefore(dataInicial)) {
                dentroPeriodo = false;
            }

            if (dataFinal != null && dataTime.isAfter(dataFinal)) {
                dentroPeriodo = false;
            }

            if (!dentroPeriodo) continue;

            for (ComposicaoTime ct : time.getComposicaoTime()) {

                Integrante integrante = ct.getIntegrante();

                contador.put(
                        integrante,
                        contador.getOrDefault(integrante, 0) + 1
                );
            }
        }

        return contador.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return Collections.emptyList();
        }

        Map<List<String>, Integer> contador = new HashMap<>();

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroPeriodo = true;

            if (dataInicial != null && dataTime.isBefore(dataInicial)) {
                dentroPeriodo = false;
            }

            if (dataFinal != null && dataTime.isAfter(dataFinal)) {
                dentroPeriodo = false;
            }

            if (!dentroPeriodo) continue;

            List<String> nomes = time.getComposicaoTime()
                    .stream()
                    .map(ct -> ct.getIntegrante().getNome())
                    .sorted() // FUNDAMENTAL
                    .collect(Collectors.toList());

            contador.put(
                    nomes,
                    contador.getOrDefault(nomes, 0) + 1
            );
        }

        return contador.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Collections.emptyList());
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }

        Map<String, Integer> contador = new HashMap<>();

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroDoPeriodo = true;

            if (dataInicial != null && dataTime.isBefore(dataInicial)) {
                dentroDoPeriodo = false;
            }

            if (dataFinal != null && dataTime.isAfter(dataFinal)) {
                dentroDoPeriodo = false;
            }

            if (dentroDoPeriodo && time.getComposicaoTime() != null) {

                for (ComposicaoTime composicao : time.getComposicaoTime()) {

                    Integrante integrante = composicao.getIntegrante();

                    if (integrante != null) {
                        String funcao = integrante.getFuncao();

                        contador.put(funcao,
                                contador.getOrDefault(funcao, 0) + 1);
                    }
                }
            }
        }

        String funcaoMaisComum = null;
        int maiorQuantidade = 0;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > maiorQuantidade) {
                maiorQuantidade = entry.getValue();
                funcaoMaisComum = entry.getKey();
            }
        }

        return funcaoMaisComum;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!

        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }

        Map<String, Integer> contador = new HashMap<>();

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroDoPeriodo = true;

            if (dataInicial != null && dataTime.isBefore(dataInicial)) {
                dentroDoPeriodo = false;
            }

            if (dataFinal != null && dataTime.isAfter(dataFinal)) {
                dentroDoPeriodo = false;
            }

            if (dentroDoPeriodo && time.getComposicaoTime() != null) {

                for (ComposicaoTime composicao : time.getComposicaoTime()) {

                    Integrante integrante = composicao.getIntegrante();

                    if (integrante != null) {
                        String franquia = integrante.getFranquia();

                        contador.put(franquia,
                                contador.getOrDefault(franquia, 0) + 1);
                    }
                }
            }
        }

        String franquiaMaisFamosa = null;
        int maiorQuantidade = 0;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > maiorQuantidade) {
                maiorQuantidade = entry.getValue();
                franquiaMaisFamosa = entry.getKey();
            }
        }

        return franquiaMaisFamosa;
    }

    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        Map<String, Long> contador = new HashMap<>();

        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return contador;
        }

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroDoPeriodo =
                    (dataInicial == null || !dataTime.isBefore(dataInicial)) &&
                            (dataFinal == null || dataTime.isBefore(dataFinal)); // FINAL EXCLUSIVA

            if (dentroDoPeriodo && time.getComposicaoTime() != null) {

                Set<String> franquiasDoTime = new HashSet<>();

                for (ComposicaoTime composicao : time.getComposicaoTime()) {

                    Integrante integrante = composicao.getIntegrante();

                    if (integrante != null) {
                        franquiasDoTime.add(integrante.getFranquia());
                    }
                }

                for (String franquia : franquiasDoTime) {
                    contador.put(
                            franquia,
                            contador.getOrDefault(franquia, 0L) + 1L
                    );
                }
            }
        }

        return contador;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        Map<String, Long> contador = new HashMap<>();
        Set<Integrante> integrantesContados = new HashSet<>();

        for (Time time : todosOsTimes) {

            LocalDate dataTime = time.getData();

            boolean dentroDoPeriodo =
                    (dataInicial == null || !dataTime.isBefore(dataInicial)) &&
                            (dataFinal == null || dataTime.isBefore(dataFinal));

            if (dentroDoPeriodo && time.getComposicaoTime() != null) {
                for (ComposicaoTime composicao : time.getComposicaoTime()) {
                    Integrante integrante = composicao.getIntegrante();
                    if (integrante != null && !integrantesContados.contains(integrante)) {

                        integrantesContados.add(integrante);
                        String funcao = integrante.getFuncao();
                        contador.put(funcao,
                                contador.getOrDefault(funcao, 0L) + 1L);

                    }
                }
            }
        }

        return contador;
    }

    public TimeResponseDTO criarTime(TimeRequestDTO dto) {

        Time time = new Time();
        time.setData(dto.getData());

        List<ComposicaoTime> composicoes = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        for (Long id : dto.getIntegrantesIds()) {

            Integrante integrante = integranteRepository.findById(id)
                    .orElseThrow();

            ComposicaoTime ct = new ComposicaoTime();
            ct.setTime(time);
            ct.setIntegrante(integrante);

            composicoes.add(ct);
            nomes.add(integrante.getNome());
        }

        time.setComposicaoTime(composicoes);

        Time salvo = timeRepository.save(time);

        return new TimeResponseDTO(
                salvo.getId(),
                salvo.getData(),
                nomes
        );
    }
}
