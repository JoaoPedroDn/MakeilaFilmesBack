package com.filmestore.service;

import com.filmestore.model.Pedido;
import com.filmestore.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por gerenciar pedidos.
 *
 * IMPORTANTE:
 *  - Tudo é mantido em memória (lista interna).
 *  - Nada é salvo em banco de dados.
 *  - Ao reiniciar a aplicação, os dados somem (comportamento esperado).
 */
@Service
public class PedidoService {

    // Lista que armazena todos os pedidos criados
    private final List<Pedido> pedidos = new ArrayList<>();

    // Contador simples para gerar IDs sequenciais
    private int contador = 1;

    /**
     * Cria e registra um novo pedido.
     * Define ID automaticamente (1, 2, 3...).
     */
    public Pedido criarPedido(Pedido pedido) {
        pedido.setId(contador++);      // gera ID
        pedidos.add(pedido);           // salva na lista
        return pedido;
    }

    /**
     * Procura um pedido pelo seu ID.
     * Se não encontrar, retorna null.
     */
    public Pedido buscarPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Adiciona um produto ao pedido específico.
     *
     * Regras:
     *  - Se o pedido não existir → false
     *  - Se já tiver o produto → false
     *  - Se puder adicionar → true
     */
    public boolean adicionarProduto(int pedidoId, Produto produto) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null) return false;  // pedido inexistente

        // A lógica de impedir duplicados está dentro do Pedido.adicionarProduto()
        return pedido.adicionarProduto(produto);
    }

    public List<Pedido> listarTodos() {
        return pedidos;
    }

}
