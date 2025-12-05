package com.filmestore.controller;

import com.filmestore.model.Cliente;
import com.filmestore.model.Pedido;
import com.filmestore.model.Produto;
import com.filmestore.service.PedidoService;
import com.filmestore.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <--- O ERRO ESTAVA AQUI (FALTAVA ESSE IMPORT)


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;


    public PedidoController(PedidoService pedidoService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
    }

    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        // Lembre-se: Você precisa ter criado o método listarTodos() no PedidoService
        return pedidoService.listarTodos();
    }


    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Cliente cliente) {
        Pedido p = new Pedido();
        p.setCliente(cliente);
        p = pedidoService.criarPedido(p);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/pedidos/{id}/add")
    public ResponseEntity<?> adicionarProduto(
            @PathVariable int id,
            @RequestBody Produto produtoBody
    ) {
        Produto encontrado = produtoService.buscarPorId(produtoBody.getId());

        if (encontrado == null)
            return ResponseEntity.badRequest().body("Produto não encontrado");

        boolean ok = pedidoService.adicionarProduto(id, encontrado);

        if (!ok)
            return ResponseEntity.badRequest().body("Produto já está no pedido ou pedido inexistente");

        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }



    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int id) {
        Pedido p = pedidoService.buscarPorId(id);

        if (p == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p);
    }
}