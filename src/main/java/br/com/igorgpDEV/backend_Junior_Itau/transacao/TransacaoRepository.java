package br.com.igorgpDEV.backend_Junior_Itau.transacao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private final List<TransacaoRequest> transacoes = new ArrayList<>();

    public void add(TransacaoRequest transacaoRequest) {
        transacoes.add(transacaoRequest);
    }

    public void limpar() {
        transacoes.clear();
    }
}
