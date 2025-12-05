package com.filmestore.service;

import com.filmestore.model.Pedido;
import com.filmestore.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {


    private final List<Pedido> pedidos = new ArrayList<>();


    private int contador = 1;


    public Pedido criarPedido(Pedido pedido) {
        pedido.setId(contador++);
        pedidos.add(pedido);
        return pedido;
    }


    public Pedido buscarPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public boolean adicionarProduto(int pedidoId, Produto produto) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null) return false;

        return pedido.adicionarProduto(produto);
    }

    public List<Pedido> listarTodos() {
        return pedidos;
    }

}
