package com.filmestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 *
 * É o ponto de entrada. Quando você executa,
 * o Spring inicializa todo o projeto:
 *
 *  - Configura controllers
 *  - Cria services
 *  - Carrega repositórios
 *  - Inicia o servidor embutido Tomcat
 *
 * Depois disso, a API fica disponível em:
 * http://localhost:8080 ddihdkyi
 */
@SpringBootApplication
public class FilmeStoreApplication {

    public static void main(String[] args) {
        // Inicializa a aplicação
        SpringApplication.run(FilmeStoreApplication.class, args);
    }
}
