package com.filmestore.model;

public class Produto {


    private String id;

    private String titulo;


    private String ano;


    private String genero;

    private double preco;

    private String imagemUrl;

    private String sinopse;

    private String trailerUrl;

    private String elenco;

    public Produto() {}

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