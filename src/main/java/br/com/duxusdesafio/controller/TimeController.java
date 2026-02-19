package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeRequestDTO;
import br.com.duxusdesafio.dto.TimeResponseDTO;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService service;
    private final ApiService apiService;

    public TimeController(TimeService service, ApiService apiService) {
        this.service = service;
        this.apiService = apiService;
    }

    @PostMapping
    public TimeResponseDTO criarTime(@RequestBody TimeRequestDTO dto) {
        return apiService.criarTime(dto);
    }

    @GetMapping
    public List<Time> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Time buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/data/{data}")
    public List<Time> buscarPorData(@PathVariable String data) {
        return service.buscarPorData(LocalDate.parse(data));
    }
}