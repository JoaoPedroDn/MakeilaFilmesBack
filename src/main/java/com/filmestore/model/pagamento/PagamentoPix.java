package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;
import java.time.LocalDateTime;


public class PagamentoPix implements IPagamento {


    private String chavePix;


    private LocalDateTime dataPagamento;


    private StatusPagamento status = StatusPagamento.PENDENTE;

    public PagamentoPix() {}

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        this.dataPagamento = LocalDateTime.now();
        this.status = StatusPagamento.APROVADO;
        return true;
    }

    @Override
    public StatusPagamento getStatus() {
        return this.status;
    }

    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
}
