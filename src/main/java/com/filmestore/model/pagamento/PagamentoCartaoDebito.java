package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;


public class PagamentoCartaoDebito extends PagamentoCartao {

    public PagamentoCartaoDebito() {}

    public PagamentoCartaoDebito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) {
        this.status = StatusPagamento.APROVADO;
        return true;
    }
}
