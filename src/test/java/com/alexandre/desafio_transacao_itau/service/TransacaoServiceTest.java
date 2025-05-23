package com.alexandre.desafio_transacao_itau.service;

import com.alexandre.desafio_transacao_itau.dto.EstatisticaResponseDTO;
import com.alexandre.desafio_transacao_itau.dto.TransacaoRequestDTO;
import com.alexandre.desafio_transacao_itau.infrastructure.exceptions.UnprocessableEntity;
import com.alexandre.desafio_transacao_itau.services.TransacaoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatistica = new EstatisticaResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void adicionaTransacaoComSucesso() {

        transacaoService.adicionarTransacao(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void limpaTransacaoComSucesso() {

        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void buscaTransacaoDentroDoIntervalo() {

        TransacaoRequestDTO dataPassada = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacao(transacao);
        transacaoService.adicionarTransacao(dataPassada);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dataPassada));
    }

    @Test
    void lancaExcecaoCasoValorNegativo() {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("O valor nao pode ser menor que zero", exception.getMessage());
    }

    @Test
    void lancaExcecaoCasoDataFutura() {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora não podem ser futuros.", exception.getMessage());
    }
}
