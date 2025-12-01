package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;

/**
 * Implementação de pagamento com cartão de débito.
 * Nesta simulação, TODO pagamento é aprovado.
 */
public class PagamentoCartaoDebito extends PagamentoCartao {

    public PagamentoCartaoDebito() {}

    public PagamentoCartaoDebito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) {
        // Débito sempre aprova no nosso sistema simplificado
        this.status = StatusPagamento.APROVADO;
        return true;
    }
}
