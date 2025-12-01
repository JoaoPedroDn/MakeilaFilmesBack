package com.filmestore.controller;

import com.filmestore.model.Produto;
import com.filmestore.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por disponibilizar endpoints
 * relacionados aos produtos (filmes) da loja.
 *
 * Ele recebe requisições HTTP e chama o ProdutoService
 * para executar as regras de negócio.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProdutoController {

    // Serviço responsável por fornecer dados dos produtos
    private final ProdutoService service;

    /**
     * Injeção automática do ProdutoService pelo Spring Boot.
     */
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    /**
     * GET /api/produtos
     * Retorna a lista completa de filmes disponíveis.
     */
    @GetMapping("/produtos")
    public List<Produto> listar() {
        return service.listarTodos();
    }

    /**
     * GET /api/produtos/{id}
     * Retorna informações de um filme específico.
     */
    @GetMapping("/produtos/{id}")
    public Produto buscar(@PathVariable String id) {
        return service.buscarPorId(id);
    }
}
