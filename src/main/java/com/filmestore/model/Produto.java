package com.filmestore.model;

/**
 * Representa um produto (um filme digital) vendido na loja.
 * Nada aqui depende de banco de dados — os dados vêm de um CSV.
 */
public class Produto {

    // ID do produto, vindo do CSV (ex: "1")
    private String id;

    // Nome/título do filme (ex: "Duna: Parte 2")
    private String titulo;

    // --- NOVOS CAMPOS ADICIONADOS PARA O SITE FICAR COMPLETO ---

    // Ano de lançamento (ex: "2024")
    private String ano;

    // Gênero principal (ex: "Ficção Científica", "Ação")
    private String genero;

    // Preço do filme (ex: 29.90)
    private double preco;

    // Link da imagem da capa/poster (ex: "https://...")
    private String imagemUrl;

    // Sinopse/História do filme (substitui a antiga 'descricao')
    private String sinopse;

    // Link do trailer do YouTube
    private String trailerUrl;

    // Nomes dos atores principais
    private String elenco;

    // Construtor vazio (necessário para o Spring Boot criar o objeto)
    public Produto() {}

    /**
     * Construtor completo.
     * Usado quando criamos produtos a partir do arquivo CSV.
     */
    public Produto(String id, String titulo, String ano, String genero, double preco, String imagemUrl, String sinopse, String trailerUrl, String elenco) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
        this.sinopse = sinopse;
        this.trailerUrl = trailerUrl;
        this.elenco = elenco;
    }

    // --- GETTERS E SETTERS (Permitem ler e gravar os dados) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAno() { return ano; }
    public void setAno(String ano) { this.ano = ano; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }

    public String getElenco() { return elenco; }
    public void setElenco(String elenco) { this.elenco = elenco; }
}