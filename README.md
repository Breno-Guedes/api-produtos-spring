# API REST de Gestão de Produtos

![Java](https://img.shields.io/badge/Java-21-007396?style=flat-square&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-3-003B57?style=flat-square&logo=sqlite&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=flat-square&logo=hibernate&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache_Maven-3.9+-C71A36?style=flat-square&logo=apache-maven&logoColor=white)

## Descrição do Projeto

Esta aplicação é uma API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de produtos. A solução disponibiliza os endpoints necessários para a realização das operações fundamentais de CRUD (Create, Read, Update e Delete) com persistência de dados em um banco de dados relacional embarcado SQLite, eliminando a necessidade de gerenciadores de bancos de dados externos.

---

## Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.2.5
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Base de Dados:** SQLite JDBC (`org.xerial:sqlite-jdbc`)
* **Dialeto ORM:** Hibernate Community Dialects
* **Gerenciador de Dependências:** Apache Maven

---

## Estrutura do Modelo de Dados

A entidade `Produto` possui a seguinte estrutura de campos:

* **id** (`Long`): Identificador único gerado automaticamente pelo banco de dados.
* **nome** (`String`): Nome do produto.
* **categoria** (`String`): Categoria do produto.
* **preco** (`Double`): Preço unitário do produto.
* **descricao** (`String`): Descrição detalhada do produto.
* **destaque** (`Boolean`): Indica se o produto está em destaque.

---

## Configuração das Dependências do Projeto

As principais dependências utilizadas no projeto são:

* **Spring Web:** desenvolvimento da API REST e disponibilização dos endpoints HTTP.
* **Spring Data JPA:** abstração para persistência e acesso aos dados.
* **Hibernate:** implementação do JPA utilizada pelo Spring Data.
* **SQLite JDBC:** driver responsável pela comunicação com o banco de dados SQLite.
* **Hibernate Community Dialects:** disponibilização do dialeto SQLite para o Hibernate.
* **Spring Boot Maven Plugin:** gerenciamento e execução da aplicação por meio do Maven.

---

## Configuração da Base de Dados

A conexão com o banco de dados SQLite está configurada no arquivo `src/main/resources/application.properties`. O arquivo `produtos.db` é criado e atualizado automaticamente na raiz do projeto durante a inicialização da aplicação:

```properties
spring.datasource.url=jdbc:sqlite:produtos.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

---

## Endpoints da API

A aplicação disponibiliza as seguintes rotas na URL base `http://localhost:8080/produtos`:

| **Método** | **Endpoint** | **Descrição** | **Corpo da Requisição (JSON)** | **Resposta** |
|---|---|---|---|---|
| **GET** | `/produtos` | Lista todos os produtos cadastrados | N/A | `200 OK` (Lista em JSON) |
| **GET** | `/produtos/destaque` | Lista todos os produtos em destaque | N/A | `200 OK` (Lista em JSON) |
| **GET** | `/produtos/{id}` | Busca um produto específico pelo ID | N/A | `200 OK` ou `404 Not Found` |
| **GET** | `/produtos/{id}/descricao` | Retorna a descrição de um produto pelo ID | N/A | `200 OK` ou `404 Not Found` |
| **POST** | `/produtos` | Cadastra um novo produto | `{"nome": "string", "categoria": "string", "preco": 0.0, "descricao": "string", "destaque": true}` | `201 Created` ou `400 Bad Request` |
| **PUT** | `/produtos/{id}` | Atualiza os dados de um produto pelo ID | `{"nome": "string", "categoria": "string", "preco": 0.0, "descricao": "string", "destaque": false}` | `200 OK`, `400 Bad Request` ou `404 Not Found` |
| **DELETE** | `/produtos/{id}` | Remove um produto pelo ID | N/A | `200 OK` ou `404 Not Found` |

---

## Como Executar a Aplicação

No diretório raiz do projeto, execute o comando abaixo utilizando o Maven Wrapper para compilar e iniciar a aplicação:

```bash
./mvnw spring-boot:run
```

Após a inicialização bem-sucedida, a API estará ativa e pronta para receber requisições no endereço `http://localhost:8080/produtos`.

---

## Testes da API com Postman

A API possui uma Collection do Postman disponibilizada junto ao projeto:

`api-produtos-springboot.json`

A Collection contém requisições organizadas de acordo com as operações realizadas pela API:

* **1. Consultas (GET)**
* **2. Cadastro (POST)**
* **3. Atualização (PUT)**
* **4. Exclusão (DELETE)**

A variável `base_url` da Collection já está configurada para:

```text
http://localhost:8080
```

Dessa forma, as requisições podem utilizar `{{base_url}}` como endereço base, evitando a necessidade de informar o endereço completo em cada requisição.

### Consultas (GET)

A pasta **1. Consultas (GET)** contém as operações utilizadas para consultar os produtos cadastrados na API. Atualmente, estão disponíveis duas formas de consulta.

#### Listar todos os produtos

Para consultar todos os produtos cadastrados, utilize:

```http
GET http://localhost:8080/produtos
```

No Postman, basta executar a requisição **Listar todos os produtos**.

Essa requisição não possui corpo (`Body`) e retorna uma lista contendo os produtos cadastrados no banco de dados SQLite.

A URL utilizada pela Collection é:

```text
{{base_url}}/produtos
```

O objetivo dessa consulta é permitir a visualização de todos os registros disponíveis na aplicação.

#### Buscar um produto por ID

Também é possível consultar um produto específico informando seu identificador:

```http
GET http://localhost:8080/produtos/1
```

No Postman, utilize a requisição **Buscar produto por ID**.

O número `1` representa o ID do produto que será consultado. Para consultar outro produto, basta substituir o ID na URL.

Por exemplo:

```http
GET http://localhost:8080/produtos/2
```

ou:

```http
GET http://localhost:8080/produtos/5
```

A URL utilizada atualmente pela Collection é:

```text
{{base_url}}/produtos/1
```

Essa consulta é utilizada quando se deseja obter os dados de um único produto a partir do seu identificador.

### Cadastro de produtos (POST)

A pasta **2. Cadastro (POST)** contém as requisições utilizadas para inserir novos produtos.

#### Cadastro com dados válidos

Para cadastrar um produto, utilize:

```http
POST http://localhost:8080/produtos
```

O corpo da requisição deve ser enviado no formato JSON:

```json
{
  "nome": "Teclado Mecânico",
  "categoria": "Periféricos",
  "preco": 250.00
}
```

A Collection configura automaticamente o cabeçalho:

```text
Content-Type: application/json
```

Também existe uma requisição destinada ao teste de validação, utilizando dados incompletos:

```json
{
  "nome": "Mouse Gamer Sem Preço"
}
```

Essa requisição tem como objetivo verificar o comportamento da API quando são enviados dados insuficientes, esperando-se o retorno de `400 Bad Request`.

### Atualização de produtos (PUT)

A pasta **3. Atualização (PUT)** contém a requisição utilizada para alterar os dados de um produto existente.

Para atualizar o produto de ID `1`, utilize:

```http
PUT http://localhost:8080/produtos/1
```

O corpo da requisição é enviado em JSON:

```json
{
  "nome": "Teclado Mecânico RGB",
  "categoria": "Periféricos",
  "preco": 299.90
}
```

O ID informado na URL determina qual produto será atualizado.

### Exclusão de produtos (DELETE)

A pasta **4. Exclusão (DELETE)** contém a requisição utilizada para remover um produto.

Para excluir o produto de ID `1`, utilize:

```http
DELETE http://localhost:8080/produtos/1
```

Assim como na consulta e na atualização, basta alterar o ID na URL para selecionar outro produto.

### Como utilizar a Collection

1. Inicie a aplicação Spring Boot.
2. Confirme que a API está disponível em `http://localhost:8080`.
3. Abra o **Postman**.
4. Clique em **Import**.
5. Selecione o arquivo `api-produtos-springboot.json`, que está disponível no diretório `collection-postman` do projeto.
6. A Collection **API Produtos - Breno de Souza Guedes** será adicionada ao Postman.
7. Execute as requisições organizadas nas pastas de **Consultas**, **Cadastro**, **Atualização** e **Exclusão**.

A Collection foi organizada para permitir tanto testes manuais dos endpoints quanto a verificação dos principais comportamentos da API.