package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteService {

    private final IntegranteRepository repository;

    public IntegranteService(IntegranteRepository repository, IntegranteRepository repository1){
        this.repository = repository1;
    }

    public List<Integrante> listarTodos() {
        return repository.findAll();
    }

    public Integrante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Integrante não encontrado"));
    }

    public Integrante salvar(Integrante integrante){
        if (repository.existsByNomeAndFranquia(integrante.getNome(), integrante.getFranquia())){
            throw new RuntimeException("Já existe um integrante com essa franquia");
        }
        return  repository.save(integrante);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Integrante não encontrado");
        }
        repository.deleteById(id);
    }

    public List<Integrante> buscarPorFranquia(String franquia) {
        return repository.findByFranquia(franquia);
    }
}
