package com.filmestore.service;

import com.filmestore.model.Produto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Camada de serviço responsável por regras de negócio relacionadas a produtos.
 *
 * Ela funciona como um intermediário entre o ProdutoController e os dados.
 * * Nesta versão simplificada, o próprio Service carrega o arquivo CSV
 * para a memória quando a aplicação inicia.
 */
@Service
public class ProdutoService {

    // Lista em memória que armazena os produtos lidos do arquivo
    private final List<Produto> produtos = new ArrayList<>();

    /**
     * Construtor da classe.
     * O Spring Boot chama isso automaticamente quando inicia o servidor.
     * Aqui nós aproveitamos para carregar os filmes do arquivo imediatamente.
     */
    public ProdutoService() {
        carregarFilmesDoCSV();
    }

    /**
     * Método auxiliar (privado) que lê o arquivo "filmes.csv" da pasta resources
     * e transforma as linhas de texto em objetos do tipo Produto.
     */
    private void carregarFilmesDoCSV() {
        try {
            // Tenta ler o arquivo filmes.csv da pasta resources
            InputStream is = getClass().getResourceAsStream("/filmes.csv");

            if (is == null) {
                System.out.println("❌ ERRO: Arquivo filmes.csv não encontrado na pasta resources!");
                criarDadosTeste(); // Se falhar, cria dados falsos para não travar
                return;
            }

            // Prepara o leitor para ler linha por linha
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String linha;

            while ((linha = reader.readLine()) != null) {
                // Tenta separar os dados usando ponto e vírgula (;)
                String[] partes = linha.split(";");

                // Se não der certo, tenta separar por vírgula (,)
                if (partes.length < 5) {
                    partes = linha.split(",");
                }

                // Se tiver dados suficientes, cria o Produto
                if (partes.length >= 5) {
                    Produto p = new Produto();
                    p.setId(partes[0].trim());
                    p.setTitulo(partes[1].trim());
                    p.setAno(partes[2].trim());
                    p.setGenero(partes[3].trim());

                    // Tratamento do preço: remove "R$" e troca vírgula por ponto
                    String precoStr = partes[4].trim().replace("R$", "").replace(",", ".").trim();
                    try {
                        p.setPreco(Double.parseDouble(precoStr));
                    } catch (NumberFormatException e) {
                        p.setPreco(29.90); // Preço padrão caso dê erro na conversão
                    }

                    // Colunas opcionais (Imagem, Sinopse, Trailer, Elenco)
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

    /**
     * Cria um produto de teste caso o arquivo CSV não seja lido corretamente.
     * Isso evita que o site fique vazio ou dê erro.
     */
    private void criarDadosTeste() {
        Produto p1 = new Produto();
        p1.setId("1");
        p1.setTitulo("Erro ao Ler CSV");
        p1.setImagemUrl("https://via.placeholder.com/300?text=Erro+Leitura");
        produtos.add(p1);
    }

    /**
     * Retorna a lista completa de produtos disponíveis.
     * É este método que o Controller chama quando o React pede os filmes.
     */
    public List<Produto> listarTodos() {
        return produtos;
    }

    /**
     * Busca produto específico pelo ID.
     * Usado quando clicamos em um filme para ver os detalhes.
     * Retorna null se não encontrar.
     */
    public Produto buscarPorId(String id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}