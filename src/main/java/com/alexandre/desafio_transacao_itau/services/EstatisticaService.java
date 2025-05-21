package com.alexandre.desafio_transacao_itau.services;

import com.alexandre.desafio_transacao_itau.dto.EstatisticaResponseDTO;
import com.alexandre.desafio_transacao_itau.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private static final Logger log = LoggerFactory.getLogger(EstatisticaService.class);
    @Autowired
    public TransacaoService transacaoService;

    public EstatisticaResponseDTO calculaEstatistica(int intervaloBusca) {
        log.info("Buscando estatísticas pelo período de {} segundos", intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatísticas retornadas com sucesso");
        return new EstatisticaResponseDTO(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(), estatisticas.getMin(), estatisticas.getMax());

    }

}
