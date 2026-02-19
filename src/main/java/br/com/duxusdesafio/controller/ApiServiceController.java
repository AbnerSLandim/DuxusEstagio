package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estatisticas")
public class ApiServiceController {

    private final TimeRepository timeRepository;
    private final ApiService apiService;

    public ApiServiceController(TimeRepository timeRepository,
                                ApiService apiService) {
        this.timeRepository = timeRepository;
        this.apiService = apiService;
    }

    @GetMapping("/timeDaData")
    public Time timeDaData(@RequestParam String data) {

        List<Time> todos = timeRepository.findAll();

        return apiService.timeDaData(
                LocalDate.parse(data),
                todos
        );
    }

    @GetMapping("/integranteMaisUsado")
    public Integrante integranteMaisUsado(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.integranteMaisUsado(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }

    @GetMapping("/timeMaisComum")
    public List<String> timeMaisComum(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.integrantesDoTimeMaisComum(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }

    @GetMapping("/funcaoMaisComum")
    public String funcaoMaisComum(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.funcaoMaisComum(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }

    @GetMapping("/franquiaMaisFamosa")
    public String franquiaMaisFamosa(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.franquiaMaisFamosa(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }

    @GetMapping("/contagemPorFranquia")
    public Map<String, Long> contagemPorFranquia(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.contagemPorFranquia(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }

    @GetMapping("/contagemPorFuncao")
    public Map<String, Long> contagemPorFuncao(
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal) {

        List<Time> todos = timeRepository.findAll();

        return apiService.contagemPorFuncao(
                dataInicial != null ? LocalDate.parse(dataInicial) : null,
                dataFinal != null ? LocalDate.parse(dataFinal) : null,
                todos
        );
    }
}