package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/times")
public class TimeFormController {

    private final IntegranteRepository integranteRepository;

    private final TimeService service;

    public TimeFormController(TimeRepository timeRepository, TimeService service,
                              IntegranteRepository integranteRepository) {
        this.service = service;
        this.integranteRepository = integranteRepository;
    }


    @PostMapping("/form")
    public String criarForm(@RequestParam(required = false) String data,
                            @RequestParam(required = false) List<Long> integrantesIds) {

        if (data == null || data.isBlank()) {
            return "redirect:/tela/times";
        }

        Time time = new Time();
        time.setData(LocalDate.parse(data));

        List<ComposicaoTime> composicoes = new ArrayList<>();

        if (integrantesIds != null) {
            for (Long integranteId : integrantesIds) {

                Integrante integrante = integranteRepository
                        .findById(integranteId)
                        .orElseThrow();

                ComposicaoTime composicao = new ComposicaoTime();
                composicao.setTime(time);
                composicao.setIntegrante(integrante);

                composicoes.add(composicao);
            }
        }

        time.setComposicaoTime(composicoes);
        service.salvar(time);

        return "redirect:/tela/times";
    }

}
