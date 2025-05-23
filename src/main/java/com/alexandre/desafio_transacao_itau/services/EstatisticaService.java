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
        log.info("Buscando estatisticas pelo periodo de {} segundos", intervaloBusca);

        long start = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();

        long tempoReq = finish - start;

        System.out.println("Tempo de requisicao: " + tempoReq + " milissigundos");

        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticaResponseDTO(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(), estatisticas.getMin(), estatisticas.getMax());

    }

}
