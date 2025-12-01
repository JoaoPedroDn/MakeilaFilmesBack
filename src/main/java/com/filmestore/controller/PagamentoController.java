package com.filmestore.controller;

import com.filmestore.model.Pedido;
import com.filmestore.model.pagamento.PagamentoCartaoCredito;
import com.filmestore.model.pagamento.PagamentoCartaoDebito;
import com.filmestore.model.pagamento.PagamentoPix;
import com.filmestore.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PedidoService pedidoService;

    public PagamentoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 8) PAGAR VIA PIX
    @PostMapping("/{id}/pagar/pix")
    public ResponseEntity<?> pagarPix(@PathVariable int id, @RequestBody Map<String, String> body) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) return ResponseEntity.notFound().build();

        String chavePix = body.get("chavePix");
        pedido.setMetodoPagamento(new PagamentoPix(chavePix));

        if (pedido.processarPagamento()) {
            return ResponseEntity.ok("Pagamento PIX realizado! Status: " + pedido.getStatusPedido());
        }
        return ResponseEntity.badRequest().body("Falha no pagamento PIX.");
    }

    // 9) CARTÃO DE CRÉDITO
    @PostMapping("/{id}/pagar/cartao/credito")
    public ResponseEntity<?> pagarCredito(@PathVariable int id, @RequestParam String numero) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) return ResponseEntity.notFound().build();

        pedido.setMetodoPagamento(new PagamentoCartaoCredito(numero));
        if (pedido.processarPagamento()) {
            return ResponseEntity.ok("Crédito Aprovado! Status: " + pedido.getStatusPedido());
        }
        return ResponseEntity.badRequest().body("Crédito Recusado.");
    }

    // 10) CARTÃO DE DÉBITO
    @PostMapping("/{id}/pagar/cartao/debito")
    public ResponseEntity<?> pagarDebito(@PathVariable int id, @RequestParam String numero) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) return ResponseEntity.notFound().build();

        pedido.setMetodoPagamento(new PagamentoCartaoDebito(numero));
        if (pedido.processarPagamento()) {
            return ResponseEntity.ok("Débito Aprovado! Status: " + pedido.getStatusPedido());
        }
        return ResponseEntity.badRequest().body("Falha no Débito.");
    }
}