package com.filmestore.service;

import com.filmestore.model.Produto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProdutoService {


    private final List<Produto> produtos = new ArrayList<>();


    public ProdutoService() {
        carregarFilmesDoCSV();
    }


    private void carregarFilmesDoCSV() {
        try {

            InputStream is = getClass().getResourceAsStream("/filmes.csv");

            if (is == null) {
                System.out.println("❌ ERRO: Arquivo filmes.csv não encontrado na pasta resources!");
                criarDadosTeste();
                return;
            }


            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(";");


                if (partes.length < 5) {
                    partes = linha.split(",");
                }


                if (partes.length >= 5) {
                    Produto p = new Produto();
                    p.setId(partes[0].trim());
                    p.setTitulo(partes[1].trim());
                    p.setAno(partes[2].trim());
                    p.setGenero(partes[3].trim());


                    String precoStr = partes[4].trim().replace("R$", "").replace(",", ".").trim();
                    try {
                        p.setPreco(Double.parseDouble(precoStr));
                    } catch (NumberFormatException e) {
                        p.setPreco(29.90);
                    }


                    if (partes.length > 5) p.setImagemUrl(partes[5].trim());
                    if (partes.length > 6) p.setSinopse(partes[6].trim());
                    if (partes.length > 7) p.setTrailerUrl(partes[7].trim());
                    if (partes.length > 8) p.setElenco(partes[8].trim());

                    produtos.add(p);
                }
            }
            System.out.println("✅ Sucesso! Carregados " + produtos.size() + " filmes do arquivo CSV.");

        } catch (Exception e) {
            e.printStackTrace();
            criarDadosTeste();
        }
    }

    private void criarDadosTeste() {
        Produto p1 = new Produto();
        p1.setId("1");
        p1.setTitulo("Erro ao Ler CSV");
        p1.setImagemUrl("https://via.placeholder.com/300?text=Erro+Leitura");
        produtos.add(p1);
    }


    public List<Produto> listarTodos() {
        return produtos;
    }


    public Produto buscarPorId(String id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}