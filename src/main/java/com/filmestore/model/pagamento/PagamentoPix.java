package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;
import java.time.LocalDateTime;

/**
 * Implementação de pagamento via PIX.
 * Simulamos um pagamento instantâneo:
 *  - Sempre aprova
 *  - Registra data e hora do pagamento
 */
public class PagamentoPix implements IPagamento {

    // Chave Pix enviada pelo cliente
    private String chavePix;

    // Quando o pagamento foi realizado (simulação)
    private LocalDateTime dataPagamento;

    // Status é iniciado como PENDENTE
    private StatusPagamento status = StatusPagamento.PENDENTE;

    public PagamentoPix() {}

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        // PIX sempre aprova
        this.dataPagamento = LocalDateTime.now();
        this.status = StatusPagamento.APROVADO;
        return true;
    }

    @Override
    public StatusPagamento getStatus() {
        return this.status;
    }

    // Getters e setters
    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
}
