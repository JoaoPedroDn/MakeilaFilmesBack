package com.filmestore.controller;

import com.filmestore.model.Cliente;
import com.filmestore.model.Pedido;
import com.filmestore.model.Produto;
import com.filmestore.service.PedidoService;
import com.filmestore.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <--- O ERRO ESTAVA AQUI (FALTAVA ESSE IMPORT)

/**
 * Controller responsável pelo fluxo principal do sistema:
 * - Criar pedidos
 * - Adicionar filmes aos pedidos
 * - Consultar pedidos
 *
 * OBS: Os endpoints de pagamento foram removidos daqui
 * (agora estão centralizados em PagamentoController).
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    /**
     * Injeção automática dos serviços via construtor.
     */
    public PedidoController(PedidoService pedidoService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
    }

    // 4) LISTAR TODOS OS PEDIDOS
    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        // Lembre-se: Você precisa ter criado o método listarTodos() no PedidoService
        return pedidoService.listarTodos();
    }

    /**
     * POST /api/pedidos
     * Cria um novo pedido para um cliente (body = Cliente JSON).
     */
    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Cliente cliente) {
        Pedido p = new Pedido();
        p.setCliente(cliente);
        p = pedidoService.criarPedido(p);
        return ResponseEntity.ok(p);
    }

    /**
     * POST /api/pedidos/{id}/add
     * Adiciona um filme ao pedido (body = Produto com id).
     * Regra: não permite adicionar o mesmo filme duas vezes.
     */
    @PostMapping("/pedidos/{id}/add")
    public ResponseEntity<?> adicionarProduto(
            @PathVariable int id,
            @RequestBody Produto produtoBody
    ) {
        // busca produto real pelo ID (do CSV)
        Produto encontrado = produtoService.buscarPorId(produtoBody.getId());

        if (encontrado == null)
            return ResponseEntity.badRequest().body("Produto não encontrado");

        // tenta adicionar ao pedido (retorna false se já existe ou pedido não existe)
        boolean ok = pedidoService.adicionarProduto(id, encontrado);

        if (!ok)
            return ResponseEntity.badRequest().body("Produto já está no pedido ou pedido inexistente");

        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    /**
     * GET /api/pedidos/{id}
     * Retorna informações completas do pedido (itens, total, status).
     */
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int id) {
        Pedido p = pedidoService.buscarPorId(id);

        if (p == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p);
    }
}