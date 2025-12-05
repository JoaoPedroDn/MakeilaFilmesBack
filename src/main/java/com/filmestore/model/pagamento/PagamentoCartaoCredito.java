package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;


public class PagamentoCartaoCredito extends PagamentoCartao {

    public PagamentoCartaoCredito() {}


    public PagamentoCartaoCredito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) {

        if (valor <= 14.99) {
            this.status = StatusPagamento.APROVADO;
            return true;
        } else {
            this.status = StatusPagamento.RECUSADO;
            return false;
        }
    }
}
