package com.alexandre.desafio_transacao_itau.service;

import com.alexandre.desafio_transacao_itau.dto.EstatisticaResponseDTO;
import com.alexandre.desafio_transacao_itau.dto.TransacaoRequestDTO;
import com.alexandre.desafio_transacao_itau.services.EstatisticaService;
import com.alexandre.desafio_transacao_itau.services.TransacaoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatistica = new EstatisticaResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticasComSucesso() {
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacao));

        EstatisticaResponseDTO resultado = estatisticaService.calculaEstatistica(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatistica);
    }

    @Test
    void calcularEstatisticasListaVazia() {

        EstatisticaResponseDTO estatisticaEsperada = new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());

        EstatisticaResponseDTO resultado = estatisticaService.calculaEstatistica(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperada);
    }
}
