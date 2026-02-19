package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteRequestDTO;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.service.IntegranteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/integrantes")
public class IntegrantesController {

    private final IntegranteService service;
    private final IntegranteRepository integranteRepository;

    public IntegrantesController(IntegranteService service, IntegranteRepository integranteRepository){
        this.service = service;
        this.integranteRepository = integranteRepository;
    }

    @PostMapping
    public Integrante criar(@RequestBody IntegranteRequestDTO dto) {

        Integrante integrante = new Integrante();
        integrante.setFranquia(dto.getFranquia());
        integrante.setNome(dto.getNome());
        integrante.setFuncao(dto.getFuncao());

        return integranteRepository.save(integrante);
    }

    @GetMapping
    public List<Integrante> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Integrante buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/franquia/{franquia}")
    public List<Integrante> buscarPorFranquia(@PathVariable String franquia) {
        return service.buscarPorFranquia(franquia);
    }


}
