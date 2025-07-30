package br.com.igorgpDEV.backend_Junior_Itau.transacao;

import br.com.igorgpDEV.backend_Junior_Itau.estatistica.EstatisticaDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

@Repository
public class TransacaoRepository {

    private final List<TransacaoRequest> transacoes = new ArrayList<>();

    public void add(TransacaoRequest transacaoRequest) {
        transacoes.add(transacaoRequest);
    }

    public void limpar() {
        transacoes.clear();
    }

    public EstatisticaDTO estatistica(OffsetDateTime horaInicial) {

        if(transacoes.isEmpty()) {

            return new EstatisticaDTO();
        } else {
            final BigDecimal[] valoresFiltrados = transacoes.stream()
                    .map(t -> t.getValor()).toArray(BigDecimal[]::new);

            DoubleStream doubleStream = Arrays.stream(valoresFiltrados).mapToDouble(BigDecimal::doubleValue);
            return new EstatisticaDTO(doubleStream.summaryStatistics());
        }

    }
}
