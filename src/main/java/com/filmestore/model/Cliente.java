package com.filmestore.model;

/**
 * Representa um cliente que faz pedidos na loja.
 * Aqui armazenamos apenas dados básicos — não existe banco,
 * tudo fica guardado em listas em memória.
 */
public class Cliente {

    // Identificador único do cliente. Gerado manualmente no controller.
    private long id;

    // Nome do cliente
    private String nome;

    // Email do cliente
    private String email;

    // Telefone do cliente
    private String telefone;

    // senha do cliente
    private String senha;

    public Cliente() {}

    /**
     * Construtor completo.
     * Não usamos banco de dados, então tudo é criado manualmente.
     */
    public Cliente(long id, String nome, String email, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    // Getters e setters simples (acessadores e modificadores)

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
