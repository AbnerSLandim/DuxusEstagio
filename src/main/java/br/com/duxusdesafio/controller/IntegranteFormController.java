package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/integrantes")
public class IntegranteFormController {

    private final IntegranteRepository integranteRepository;

    public IntegranteFormController(IntegranteRepository integranteRepository) {
        this.integranteRepository = integranteRepository;
    }

    @PostMapping("/form")
    public String criarForm(Integrante integrante) {
        integranteRepository.save(integrante);
        return "redirect:/tela/integrantes";
    }
}
