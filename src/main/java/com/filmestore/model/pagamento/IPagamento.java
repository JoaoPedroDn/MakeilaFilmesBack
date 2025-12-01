package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;

/**
 * Interface para representar QUALQUER tipo de pagamento.
 * Assim, o Pedido pode chamar processarPagamento() sem saber o tipo exato.
 *
 * Implementações:
 *  - PagamentoPix
 *  - PagamentoCartaoCredito
 *  - PagamentoCartaoDebito
 */
public interface IPagamento {

    /**
     * Processa o pagamento do valor informado.
     * Retorna true se aprovado, false se recusado.
     */
    boolean processarPagamento(double valor);

    /**
     * Retorna o status atual do pagamento (PENDENTE / APROVADO / RECUSADO).
     */
    StatusPagamento getStatus();
}
