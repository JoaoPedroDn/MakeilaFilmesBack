package com.filmestore.controller;

import com.filmestore.model.Produto;
import com.filmestore.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService service;


    public ProdutoController(ProdutoService service) {
        this.service = service;
    }


    @GetMapping("/produtos")
    public List<Produto> listar() {
        return service.listarTodos();
    }


    @GetMapping("/produtos/{id}")
    public Produto buscar(@PathVariable String id) {
        return service.buscarPorId(id);
    }
}
