package com.alexandre.desafio_transacao_itau.services;

import com.alexandre.desafio_transacao_itau.dto.TransacaoRequestDTO;
import com.alexandre.desafio_transacao_itau.infrastructure.exceptions.UnprocessableEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final List<TransacaoRequestDTO> listaTransacao = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {
        log.info("Gravando transacoes {}", dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora futuras");
            throw new UnprocessableEntity("Data e hora não podem ser futuros.");
        }

        if (dto.valor() < 0) {
            log.error("Valor nao pode ser menor que zero");
            throw new UnprocessableEntity("O valor nao pode ser menor que zero");
        }

        listaTransacao.add(dto);
        log.info("Transacoes adicionadas com sucesso");
    }

    public void limparTransacoes() {
        log.info("Deletando transacoes");
        listaTransacao.clear();
        log.info("Transacoes deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(int intervaloBusca) {
        log.info("Buscando transacoes durante o periodo de {} segundos", intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("Transacoes retornadas com sucesso");
        return listaTransacao.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }

}
