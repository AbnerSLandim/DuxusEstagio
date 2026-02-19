package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeResponseDTO {

    private Long id;
    private LocalDate data;
    private List<String> integrantes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }



    public TimeResponseDTO(Long id, LocalDate data, List<String> integrantes) {
        this.id = id;
        this.data = data;
        this.integrantes = integrantes;
    }


}