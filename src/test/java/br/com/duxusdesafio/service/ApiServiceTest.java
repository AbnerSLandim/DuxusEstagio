package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ApiServiceTest {

    private ApiService apiService = new ApiService();


    @Test
    public void deveRetornarTimeDaDataCorreta() {

        LocalDate data = LocalDate.of(2023, 1, 1);

        Time time = new Time();
        time.setData(data);

        List<Time> lista = List.of(time);

        Time resultado = apiService.timeDaData(data, lista);

        assertNotNull(resultado);
        assertEquals(data, resultado.getData());
    }

    @Test
    public void deveRetornarIntegranteMaisUsado() {

        Integrante a = new Integrante();
        a.setNome("A");

        Integrante b = new Integrante();
        b.setNome("B");

        Time t1 = new Time();
        t1.setData(LocalDate.of(2023,1,1));
        t1.setComposicaoTime(Arrays.asList(
                new ComposicaoTime(t1, a),
                new ComposicaoTime(t1, b)
        ));

        Time t2 = new Time();
        t2.setData(LocalDate.of(2023,1,2));
        t2.setComposicaoTime(Arrays.asList(
                new ComposicaoTime(t2, a)
        ));

        List<Time> lista = Arrays.asList(t1, t2);

        Integrante resultado =
                apiService.integranteMaisUsado(null, null, lista);

        assertEquals("A", resultado.getNome());
    }

    @Test
    public void deveRetornarTimeMaisComum() {

        Integrante a = new Integrante();
        a.setNome("A");

        Integrante b = new Integrante();
        b.setNome("B");

        Integrante c = new Integrante();
        c.setNome("C");

        Time t1 = new Time();
        t1.setData(LocalDate.of(2023,1,1));
        t1.setComposicaoTime(Arrays.asList(
                new ComposicaoTime(t1, a),
                new ComposicaoTime(t1, b),
                new ComposicaoTime(t1, c)
        ));

        Time t2 = new Time();
        t2.setData(LocalDate.of(2023,1,2));
        t2.setComposicaoTime(Arrays.asList(
                new ComposicaoTime(t2, a),
                new ComposicaoTime(t2, b),
                new ComposicaoTime(t2, c)
        ));

        List<Time> lista = Arrays.asList(t1, t2);

        List<String> resultado =
                apiService.integrantesDoTimeMaisComum(null, null, lista);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains("A"));
    }

    @Test
    public void deveRetornarFuncaoMaisComumNoPeriodo() {

        // Integrantes
        Integrante i1 = new Integrante("Marvel", "Tony", "Heroi", null);
        Integrante i2 = new Integrante("Marvel", "Steve", "Heroi", null);
        Integrante i3 = new Integrante("Marvel", "Nick", "Suporte", null);

        // Composições
        ComposicaoTime c1 = new ComposicaoTime();
        c1.setIntegrante(i1);

        ComposicaoTime c2 = new ComposicaoTime();
        c2.setIntegrante(i2);

        ComposicaoTime c3 = new ComposicaoTime();
        c3.setIntegrante(i3);

        // Time dentro do período
        Time time1 = new Time();
        time1.setData(LocalDate.of(2023, 1, 10));
        time1.setComposicaoTime(Arrays.asList(c1, c2, c3));

        // Serviço
        ApiService service = new ApiService();

        String resultado = service.funcaoMaisComum(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                Collections.singletonList(time1)
        );

        assertEquals("Heroi", resultado);
    }

    @Test
    public void deveRetornarFranquiaMaisFamosaNoPeriodo() {

        Integrante i1 = new Integrante("Marvel", "Tony", "Heroi", null);
        Integrante i2 = new Integrante("Marvel", "Steve", "Heroi", null);
        Integrante i3 = new Integrante("DC", "Batman", "Heroi", null);

        ComposicaoTime c1 = new ComposicaoTime();
        c1.setIntegrante(i1);

        ComposicaoTime c2 = new ComposicaoTime();
        c2.setIntegrante(i2);

        ComposicaoTime c3 = new ComposicaoTime();
        c3.setIntegrante(i3);

        Time time = new Time();
        time.setData(LocalDate.of(2023, 5, 10));
        time.setComposicaoTime(Arrays.asList(c1, c2, c3));

        ApiService service = new ApiService();

        String resultado = service.franquiaMaisFamosa(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                Collections.singletonList(time)
        );

        assertEquals("Marvel", resultado);
    }

    @Test
    public void deveContarFranquiasNoPeriodo() {

        Integrante i1 = new Integrante("Marvel", "Tony", "Heroi", null);
        Integrante i2 = new Integrante("Marvel", "Steve", "Heroi", null);
        Integrante i3 = new Integrante("DC", "Batman", "Heroi", null);

        ComposicaoTime c1 = new ComposicaoTime();
        c1.setIntegrante(i1);

        ComposicaoTime c2 = new ComposicaoTime();
        c2.setIntegrante(i2);

        ComposicaoTime c3 = new ComposicaoTime();
        c3.setIntegrante(i3);

        Time time = new Time();
        time.setData(LocalDate.of(2023, 5, 10));
        time.setComposicaoTime(Arrays.asList(c1, c2, c3));

        ApiService service = new ApiService();

        Map<String, Long> resultado = service.contagemPorFranquia(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                Arrays.asList(time)
        );

        assertEquals(Long.valueOf(2), resultado.get("Marvel"));
        assertEquals(Long.valueOf(1), resultado.get("DC"));
    }

    @Test
    public void deveContarFuncoesNoPeriodo() {

        Integrante i1 = new Integrante("Marvel", "Tony", "Heroi", null);
        Integrante i2 = new Integrante("Marvel", "Nick", "Suporte", null);
        Integrante i3 = new Integrante("DC", "Batman", "Heroi", null);

        ComposicaoTime c1 = new ComposicaoTime();
        c1.setIntegrante(i1);

        ComposicaoTime c2 = new ComposicaoTime();
        c2.setIntegrante(i2);

        ComposicaoTime c3 = new ComposicaoTime();
        c3.setIntegrante(i3);

        Time time = new Time();
        time.setData(LocalDate.of(2023, 6, 10));
        time.setComposicaoTime(Arrays.asList(c1, c2, c3));

        ApiService service = new ApiService();

        Map<String, Long> resultado = service.contagemPorFuncao(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                Arrays.asList(time)
        );

        assertEquals(Long.valueOf(2), resultado.get("Heroi"));
        assertEquals(Long.valueOf(1), resultado.get("Suporte"));
    }
}