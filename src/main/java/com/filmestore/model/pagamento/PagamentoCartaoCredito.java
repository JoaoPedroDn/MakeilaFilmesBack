package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;

/**
 * Implementação de pagamento com cartão de crédito.
 *
 * Regra dessa simulação:
 *  - Se o valor for <= 5000, o pagamento é aprovado.
 *  - Acima disso, é recusado.
 */
public class PagamentoCartaoCredito extends PagamentoCartao {

    public PagamentoCartaoCredito() {}

    /**
     * Construtor simples: apenas número do cartão.
     * O resto dos dados não é necessário nesse projeto.
      viudvhiulrdbgdrlig*/
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
