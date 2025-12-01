package com.filmestore.model;

import com.filmestore.enums.StatusPedido;
import com.filmestore.model.pagamento.IPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um pedido feito por um cliente.
 * Aqui ficam os itens, o cliente e o método de pagamento.
 * Tudo é armazenado em listas, sem banco de dados.
 */
public class Pedido {

    // ID único do pedido (gerado no PedidoService)
    private int id;

    // Cliente que está fazendo o pedido
    private Cliente cliente;

    // Lista de itens do pedido (cada item é 1 filme)
    private List<ItemPedido> itens = new ArrayList<>();

    // Data em que o pedido foi criado
    private LocalDate data = LocalDate.now();

    // Estratégia de pagamento (PIX, Cartão de crédito, etc.)
    private IPagamento metodoPagamento;

    // Status atual do pedido: PENDENTE, PROCESSANDO, PAGO...
    private StatusPedido statusPedido = StatusPedido.PENDENTE;

    public Pedido() {}

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    // Getters e setters usuais
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItens() { return itens; }

    public LocalDate getData() { return data; }

    public StatusPedido getStatusPedido() { return statusPedido; }
    public void setStatusPedido(StatusPedido statusPedido) { this.statusPedido = statusPedido; }

    public void setMetodoPagamento(IPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    /**
     * Regra de negócio:
     * Um filme não pode ser adicionado duas vezes no mesmo pedido.
     */
    public boolean adicionarProduto(Produto produto) {
        boolean jaTem = itens.stream()
                .anyMatch(i -> i.getProduto().getId().equals(produto.getId()));

        if (jaTem) return false;

        itens.add(new ItemPedido(produto));
        return true;
    }

    /**
     * Remove um produto do pedido.
     */
    public boolean removerProduto(String produtoId) {
        return itens.removeIf(i -> i.getProduto().getId().equals(produtoId));
    }

    /**
     * Soma o preço total do pedido.
     */
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    /**
     * Processa o pagamento usando a estratégia selecionada.
     */
    public boolean processarPagamento() {
        if (metodoPagamento == null) return false;

        // Executa o pagamento conforme o tipo de pagamento
        boolean ok = metodoPagamento.processarPagamento(calcularTotal());

        // Atualiza o status
        if (ok) statusPedido = StatusPedido.PAGO;
        else statusPedido = StatusPedido.PENDENTE;

        return ok;
    }
}
