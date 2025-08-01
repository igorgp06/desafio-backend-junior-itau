package br.com.igorgpDEV.backend_Junior_Itau.transacao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping(value = "/transacao")
@Slf4j
public record TransacaoController(TransacaoRepository transacaoRepository) {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> adicionar(@RequestBody TransacaoRequest transacaoRequest) {
        log.info("Adicionando transação");

        try {
            validarTransacao(transacaoRequest);
            transacaoRepository.add(transacaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity limpar() {
        log.info("Limpando transações");
        transacaoRepository.limpar();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void validarTransacao(TransacaoRequest transacaoRequest) {

        if (transacaoRequest.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor da transação inválido");
        }

        if (transacaoRequest.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Data da transação inválida");
        }

    }
}
