package com.filmestore.controller;
import com.filmestore.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final List<Cliente> clientes = new ArrayList<>();

    private long idCounter = 1;


    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }



    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable long id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCounter++);
        clientes.add(cliente);
        return cliente;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente clienteCredenciais) {
        Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getEmail().equals(clienteCredenciais.getEmail())
                        && c.getSenha().equals(clienteCredenciais.getSenha())) // <--- Adicione isso
                .findFirst()
                .orElse(null);

        if (clienteEncontrado != null)  {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.status(401).body("{\"message\": \"E-mail ou senha inválidos.\"}");
        }
    }



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
        return null;
    }



    @DeleteMapping("/{id}")
    public boolean deletarCliente(@PathVariable long id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
