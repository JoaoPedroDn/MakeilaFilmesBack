package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;
import java.time.LocalDate;

/**
 * Classe ABSTRATA que representa informações comuns a cartões.
 * Não pode ser instanciada diretamente.
 *
 * PagamentoCartaoCredito e PagamentoCartaoDebito herdam dela.
 */
public abstract class PagamentoCartao implements IPagamento {

    // Número do cartão (não validamos, porque é só simulação)
    protected String numeroCartao;

    // Nome do titular
    protected String nomeTitular;

    // Data de validade do cartão
    protected LocalDate dataValidade;

    // Código CVV
    protected String cvv;

    // Estado inicial é sempre pendente
    protected StatusPagamento status = StatusPagamento.PENDENTE;

    public PagamentoCartao() {}

    /** Construtor completo (não obrigatório para este projeto) */
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
