package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tela")
public class TelaController {

    private final IntegranteRepository integranteRepository;
    private final TimeRepository timeRepository;

    public TelaController(IntegranteRepository integranteRepository,
                          TimeRepository timeRepository) {
        this.integranteRepository = integranteRepository;
        this.timeRepository = timeRepository;
    }

    @GetMapping("/times")
    public String telaTime(Model model) {
        model.addAttribute("integrantes", integranteRepository.findAll());
        model.addAttribute("times", timeRepository.findAll()); // 👈 AQUI
        return "time";
    }

    @GetMapping("/integrantes")
    public String telaIntegrante() {
        return "integrantes";
    }
}

