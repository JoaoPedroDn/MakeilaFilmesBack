📂 README_Back.md (Makeila - FilmeStore Backend)
markdown
# 🎬 Makeila - FilmeStore Backend

---

## 📋 Sobre o Projeto

O **Makeila (FilmeStore)** é uma aplicação web desenvolvida como projeto da disciplina de **Programação Orientada a Objetos (POO)** na Universidade Federal de Goiás.  
O sistema simula um **e-commerce de filmes digitais**, permitindo que usuários naveguem por um catálogo, criem pedidos, escolham métodos de pagamento e realizem compras de forma simulada.  

---

## 🚀 Funcionalidades Principais

- 👤 **Usuários**: cadastro e gerenciamento de clientes.  
- 🎬 **Catálogo de Filmes**: exibição de título, ano, gênero, preço, sinopse, elenco e trailer.  
- 🛒 **Pedidos**: criação de pedidos, cálculo automático do total e status (pendente/pago).  
- 💳 **Pagamentos**: suporte a Pix, Cartão de Crédito e Débito com polimorfismo.  

---

## 🏗️ Arquitetura e Tecnologias

- **Arquitetura em Camadas**: Controller → Service → Repository → Model  
- **Tecnologias**: Java 17, Spring Boot, Maven, UML, GitHub  

---

## 📂 Estrutura de Classes

- **Produto** → Filme no catálogo  
- **Cliente** → Usuário registrado  
- **ItemPedido** → Filme dentro de um pedido  
- **Pedido** → Conjunto de itens e informações da compra  
- **IPagamento** → Interface para métodos de pagamento  
- **PagamentoPix / Cartão Crédito / Cartão Débito**  
- **Enums** → `StatusPagamento`, `StatusPedido`  

---

## ⚡ Como Executar

### Pré-requisitos
- Java 17+
- Maven
- IDE (VS Code ou IntelliJ)

### Passos
git clone https://github.com/JoaoPedroDn/makeila.git
cd makeila
mvn spring-boot:run
Acesse em: http://localhost:8080

## 📖 **Endpoints Principais**

### 🎞️ **Filmes**

| Método  | Rota               | Descrição              |
| ------- | ------------------ | ---------------------- |
| **GET** | `/api/filmes`      | Listar todos os filmes |
| **GET** | `/api/filmes/{id}` | Buscar filme por ID    |

---

### 👤 **Clientes**

| Método     | Rota              | Descrição         |
| ---------- | ----------------- | ----------------- |
| **GET**    | `/clientes`       | Listar clientes   |
| **POST**   | `/clientes`       | Cadastrar cliente |
| **POST**   | `/clientes/login` | Login do cliente  |
| **PUT**    | `/clientes/{id}`  | Atualizar cliente |
| **DELETE** | `/clientes/{id}`  | Remover cliente   |

---

### 🛒 **Pedidos**

| Método     | Rota                       | Descrição                 |
| ---------- | -------------------------- | ------------------------- |
| **POST**   | `/api/pedidos`             | Criar novo pedido         |
| **GET**    | `/api/pedidos/{id}`        | Consultar pedido por ID   |
| **POST**   | `/api/pedidos/{id}/add`    | Adicionar filme ao pedido |
| **DELETE** | `/api/pedidos/{id}/remove` | Remover filme do pedido   |

---

### 💳 **Pagamentos**

| Método   | Rota                                     | Descrição                   |
| -------- | ---------------------------------------- | --------------------------- |
| **POST** | `/api/pedidos/{id}/pagar/pix`            | Pagar com Pix               |
| **POST** | `/api/pedidos/{id}/pagar/cartao/credito` | Pagar com cartão de crédito |
| **POST** | `/api/pedidos/{id}/pagar/cartao/debito`  | Pagar com cartão de débito  |

---





## 📖 **Fluxo de Compra**

* Cliente escolhe um filme.
* O sistema cria um pedido e adiciona o item correspondente.
* O total do pedido é calculado automaticamente.
* O cliente escolhe a forma de pagamento.
* O pagamento é processado e o status do pedido é atualizado.

---

## 👩‍💻 **Autores**

* **Hevellyn Karinne Ribeiro Castro**
* **João Pedro Dourado do Nascimento**
* **Julia Rocha Celestino**

---


