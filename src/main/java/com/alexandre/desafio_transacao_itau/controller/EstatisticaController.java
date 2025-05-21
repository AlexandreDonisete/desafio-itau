package com.alexandre.desafio_transacao_itau.controller;

import com.alexandre.desafio_transacao_itau.dto.EstatisticaResponseDTO;
import com.alexandre.desafio_transacao_itau.services.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca das estatísticas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticaService.calculaEstatistica(intervaloBusca));
    }

}
