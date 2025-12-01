package com.filmestore.controller;
import com.filmestore.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável pelo gerenciamento de clientes.
 * Todos os dados ficam armazenados apenas em memória
 * (não há banco de dados).
 */
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    // Lista em memória usada como "banco"
    private final List<Cliente> clientes = new ArrayList<>();

    // Contador simples para geração de IDs automáticos
    private long idCounter = 1;

    /**
     * GET /clientes
     * Retorna todos os clientes cadastrados.
     */
    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }

    /**
     * GET /clientes/{id}
     * Busca um cliente específico pelo ID.
     */
    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable long id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * POST /clientes
     * Cadastra um novo cliente na lista.
     * O ID é gerado automaticamente.
     */
    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCounter++);
        clientes.add(cliente);
        return cliente;
    }

    /**
     * POST /clientes/login
     * Endpoint de LOGIN dedicado.
     * Retorna o cliente logado ou erro 401.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente clienteCredenciais) {
        // Busca o cliente pelo e-mail
        Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getEmail().equals(clienteCredenciais.getEmail())
                        && c.getSenha().equals(clienteCredenciais.getSenha())) // <--- Adicione isso
                .findFirst()
                .orElse(null);

        if (clienteEncontrado != null)  {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            // Resposta de erro 401 (Não Autorizado)
            return ResponseEntity.status(401).body("{\"message\": \"E-mail ou senha inválidos.\"}");
        }
    }

    /**
     * PUT /clientes/{id}
     * Atualiza nome, email ou telefone de um cliente existente.
     */
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable long id, @RequestBody Cliente clienteAtualizado) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                c.setNome(clienteAtualizado.getNome());
                c.setEmail(clienteAtualizado.getEmail());
                c.setTelefone(clienteAtualizado.getTelefone());
                return c;
            }
        }
        return null; // não encontrado
    }

    /**
     * DELETE /clientes/{id}
     * Remove cliente da lista.
     */
    @DeleteMapping("/{id}")
    public boolean deletarCliente(@PathVariable long id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
