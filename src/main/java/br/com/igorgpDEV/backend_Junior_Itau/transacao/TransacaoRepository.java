package br.com.igorgpDEV.backend_Junior_Itau.transacao;

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

    public Object estatistica(OffsetDateTime horaInicial) {

        final BigDecimal[] valoresFiltrados = transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(horaInicial) || t.getDataHora().equals(horaInicial))
                .map(t -> t.getValor()).toArray(BigDecimal[]::new);

        DoubleStream doubleStream = Arrays.stream(valoresFiltrados).mapToDouble(BigDecimal::doubleValue);
        return doubleStream.summaryStatistics();
    }
}
