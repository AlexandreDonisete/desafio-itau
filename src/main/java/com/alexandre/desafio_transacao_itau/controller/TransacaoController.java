package com.alexandre.desafio_transacao_itau.controller;

import com.alexandre.desafio_transacao_itau.dto.TransacaoRequestDTO;
import com.alexandre.desafio_transacao_itau.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"), @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos"), @ApiResponse(responseCode = "400", description = "Erro de requisição"), @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {

        transacaoService.adicionarTransacao(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar todas as transações")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transação deletadas com sucesso"), @ApiResponse(responseCode = "400", description = "Erro de requisição"), @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }

}
