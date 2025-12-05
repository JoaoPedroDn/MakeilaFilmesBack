package com.filmestore.model;


public class ItemPedido {


    private Produto produto;


    private double preco;


    public ItemPedido() {}

    public ItemPedido(Produto produto) {
        this.produto = produto;
        this.preco = produto.getPreco(); // preço capturado no momento
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }


    public double getSubtotal() {
        return preco;
    }
}
