package com.alexandre.desafio_transacao_itau.dto;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {
}
