package com.alexandre.desafio_transacao_itau.controller;

import com.alexandre.desafio_transacao_itau.dto.EstatisticaResponseDTO;
import com.alexandre.desafio_transacao_itau.services.EstatisticaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticaController estatisticaController;

    EstatisticaResponseDTO estatistica;

    @Mock
    EstatisticaService estatisticaService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatistica = new EstatisticaResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void BuscaEstatisticaComSucesso() throws Exception {
        when(estatisticaService.calculaEstatistica(60)).thenReturn(estatistica);

        mockMvc.perform(get("/estatistica")
                .param("intervaloBusca", "60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatistica.count()));
    }
}
