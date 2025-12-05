package com.filmestore.model;

import com.filmestore.enums.StatusPedido;
import com.filmestore.model.pagamento.IPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Pedido {


    private int id;


    private Cliente cliente;


    private List<ItemPedido> itens = new ArrayList<>();


    private LocalDate data = LocalDate.now();


    private IPagamento metodoPagamento;


    private StatusPedido statusPedido = StatusPedido.PENDENTE;

    public Pedido() {}

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

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


    public boolean adicionarProduto(Produto produto) {
        boolean jaTem = itens.stream()
                .anyMatch(i -> i.getProduto().getId().equals(produto.getId()));

        if (jaTem) return false;

        itens.add(new ItemPedido(produto));
        return true;
    }


    public boolean removerProduto(String produtoId) {
        return itens.removeIf(i -> i.getProduto().getId().equals(produtoId));
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    public boolean processarPagamento() {
        if (metodoPagamento == null) return false;

        boolean ok = metodoPagamento.processarPagamento(calcularTotal());

        if (ok) statusPedido = StatusPedido.PAGO;
        else statusPedido = StatusPedido.PENDENTE;

        return ok;
    }
}
