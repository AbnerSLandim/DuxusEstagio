package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeService {

    private final TimeRepository repository;

    public TimeService(TimeRepository repository) {
        this.repository = repository;
    }

    public List<Time> listarTodos() {
        return repository.findAll();
    }

    public Time buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));
    }

    public Time salvar(Time time) {

        // 🔥 REGRA DE NEGÓCIO
        if (repository.existsByData(time.getData())) {
            throw new RuntimeException("Já existe um time cadastrado nessa data");
        }

        return repository.save(time);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Time não encontrado");
        }
        repository.deleteById(id);
    }

    public List<Time> buscarPorData(LocalDate data) {
        return repository.findByData(data);
    }
}