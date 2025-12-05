package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;
import java.time.LocalDate;


public abstract class PagamentoCartao implements IPagamento {


    protected String numeroCartao;


    protected String nomeTitular;


    protected LocalDate dataValidade;


    protected String cvv;


    protected StatusPagamento status = StatusPagamento.PENDENTE;

    public PagamentoCartao() {}


    public PagamentoCartao(String numeroCartao, String nomeTitular, LocalDate dataValidade, String cvv) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    @Override
    public StatusPagamento getStatus() {
        return this.status;
    }
}
