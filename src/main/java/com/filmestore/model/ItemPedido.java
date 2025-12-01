package com.filmestore.model;

/**
 * Representa um item individual dentro de um pedido.
 * Como é um filme digital, não existe quantidade — cada item vale 1.
 */
public class ItemPedido {

    // Produto associado a este item
    private Produto produto;

    // Preço registrado no momento da compra
    private double preco;

    // Construtor vazio para conversões JSON
    public ItemPedido() {}

    /**
     * Construtor que cria um item automaticamente a partir de um produto.
     */
    public ItemPedido(Produto produto) {
        this.produto = produto;
        this.preco = produto.getPreco(); // preço capturado no momento
    }

    // Getters e setters
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    /**
     * Subtotal desse item.
     * Como não tem quantidade, sempre retorna o preço.
     */
    public double getSubtotal() {
        return preco;
    }
}
