# API de Pedidos

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de clientes, produtos e pedidos. O projeto aplica conceitos de desenvolvimento backend, persistência de dados, validação de informações, tratamento de exceções e testes automatizados.

O objetivo é consolidar conhecimentos em desenvolvimento de APIs REST e boas práticas de programação Java.

## Tecnologias utilizadas

- **Java** — linguagem de programação
- **Spring Boot** — criação da aplicação backend
- **Spring Web** — desenvolvimento de endpoints REST
- **Spring Data JPA** — persistência e acesso a dados
- **H2 Database** — banco de dados utilizado no projeto
- **Bean Validation** — validação de dados de entrada
- **Maven** — gerenciamento de dependências e build
- **JUnit e MockMvc** — testes automatizados da aplicação

## Funcionalidades

- Cadastro e consulta de clientes
- Cadastro e consulta de produtos
- Criação e consulta de pedidos
- Listagem de clientes e pedidos
- Atualização do status de um pedido para pago
- Cálculo do valor total do pedido
- Validação da existência de clientes e produtos
- Verificação de estoque disponível antes de criar um pedido
- Atualização do estoque após a criação do pedido
- Tratamento de exceções para recursos inexistentes e estoque insuficiente
- Testes automatizados dos endpoints e das principais regras de negócio

## Endpoints

### Clientes

| Método | Rota | Descrição |
|---|---|---|
| POST | `/clientes` | Cadastra um cliente |
| GET | `/clientes` | Lista os clientes |
| GET | `/clientes/{id}` | Busca um cliente por ID |

### Produtos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastra um produto |
| GET | `/produtos/{id}` | Busca um produto por ID |

### Pedidos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/pedidos` | Cria um pedido |
| GET | `/pedidos` | Lista os pedidos |
| GET | `/pedidos/{id}` | Busca um pedido por ID |
| PUT | `/pedidos/{id}/pagar` | Atualiza o status do pedido para pago |

**Observação:** os endpoints recebem e retornam dados conforme os DTOs e as regras implementadas na aplicação.

## Regras de negócio

- Um pedido deve estar associado a um cliente existente.
- Os produtos informados no pedido precisam existir.
- A quantidade solicitada deve respeitar o estoque disponível.
- O valor total é calculado a partir dos preços e das quantidades dos itens.
- O estoque é atualizado durante o processamento do pedido.
- Pedidos e outros recursos inexistentes são tratados com exceções específicas.

## Testes automatizados

O projeto possui testes para os controllers, cobrindo operações da API, validações e cenários de erro.

Para executar os testes, utilize o terminal na raiz do projeto:

```bash
./mvnw test
```

## Como executar o projeto

### Pré-requisitos

- Java compatível com a versão configurada no projeto
- Git

### 1. Clone o repositório

```bash
git clone https://github.com/AlonsoFavero/api-pedidos.git
```

### 2. Entre na pasta

```bash
cd api-pedidos
```

### 3. Execute a aplicação

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta padrão configurada pelo Spring Boot, salvo configuração diferente no projeto.

## Estrutura do projeto

```text
src/
├── main/
│   └── java/com/example/apipedidos/
│       ├── controller/
│       ├── exception/
│       ├── model/
│       ├── repository/
│       └── service/
└── test/
    └── java/com/example/apipedidos/
        └── controller/
```

- **controller:** recebe as requisições HTTP e disponibiliza os endpoints.
- **service:** concentra as regras de negócio.
- **repository:** realiza o acesso aos dados.
- **model:** representa as entidades e os conceitos do domínio.
- **exception:** contém exceções específicas e tratamento global de erros.
- **test:** contém os testes automatizados.

## Objetivo de aprendizado

Este projeto faz parte da minha evolução como estudante de Análise e Desenvolvimento de Sistemas, com foco em desenvolvimento backend utilizando Java e Spring Boot.

Durante o desenvolvimento, foram praticados conceitos de APIs REST, orientação a objetos, persistência de dados, validação, tratamento de exceções, regras de negócio e testes automatizados.

## Autor

**Alonso Favero Filho**

Estudante de Análise e Desenvolvimento de Sistemas | Desenvolvedor Backend Java em formação

- GitHub: [Alonso Favero Filho](https://github.com/AlonsoFavero)
- LinkedIn: [Meu perfil profissional](https://www.linkedin.com/in/alonso-favero-filho-00a7163aa/)

