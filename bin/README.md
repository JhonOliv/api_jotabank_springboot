# API JotaBank

API REST desenvolvida em Java com Spring Boot, criada para simular operações básicas de um ambiente bancário.

O projeto tem como objetivo consolidar conhecimentos em desenvolvimento backend, arquitetura em camadas, persistência de dados, regras de negócio e boas práticas na construção de APIs.

> Projeto em desenvolvimento contínuo, utilizado como parte da minha evolução prática como desenvolvedor backend Java.

---

## Sobre o Projeto

A **API JotaBank** é uma aplicação backend que simula funcionalidades de um sistema bancário, permitindo o gerenciamento de contas, titulares e transações financeiras.

O projeto foi desenvolvido com foco em aprendizado prático, aplicando conceitos importantes do ecossistema Java e Spring, como criação de endpoints REST, uso de DTOs, persistência com JPA/Hibernate, validações de regras de negócio e organização do código em camadas.

Durante o desenvolvimento, o principal objetivo foi compreender melhor como uma API real pode ser estruturada, separando responsabilidades entre controllers, services, repositories, entities e DTOs.

---

## Objetivos do Projeto

- Praticar o desenvolvimento de APIs REST com Java e Spring Boot.
- Simular regras de negócio de um ambiente bancário.
- Aplicar conceitos de Programação Orientada a Objetos.
- Trabalhar com persistência de dados utilizando JPA e Hibernate.
- Organizar o projeto em uma arquitetura em camadas.
- Utilizar DTOs para entrada e saída de dados.
- Criar validações para operações financeiras.
- Consolidar conhecimentos em backend por meio de um projeto prático.
- Evoluir o projeto gradualmente com novas funcionalidades e melhorias técnicas.

---

## Tecnologias Utilizadas

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

### Banco de Dados

- H2 Database

### Controle de Versão

- Git
- GitHub

### Conceitos Aplicados

- API REST
- Programação Orientada a Objetos
- Arquitetura em camadas
- DTOs
- Services
- Repositories
- Controllers
- Entities
- Exceptions personalizadas
- Validações de regras de negócio
- Relacionamento entre entidades
- Consultas com JPA/JPQL
- Persistência de dados
- Separação de responsabilidades
- Boas práticas de organização de código

---

## Funcionalidades Desenvolvidas

- Cadastro de contas bancárias.
- Consulta de contas.
- Atualização de dados da conta.
- Cadastro e associação de titular à conta.
- Realização de transações entre contas.
- Validação de conta de origem.
- Validação de conta de destino.
- Validação de saldo disponível.
- Operação de débito na conta de origem.
- Operação de crédito na conta de destino.
- Registro das transações realizadas.
- Consulta de dados por CPF do titular.
- Tratamento de exceções para cenários inválidos.

---

## Regras de Negócio Implementadas

A API conta com regras de negócio voltadas para a simulação de operações bancárias, como:

- Uma transferência só pode ocorrer se a conta de origem existir.
- Uma transferência só pode ocorrer se a conta de destino existir.
- A conta de origem precisa possuir saldo suficiente para realizar a operação.
- Valores negativos ou inválidos não devem ser aceitos.
- Ao realizar uma transferência, o valor é debitado da conta de origem.
- Após o débito, o valor é creditado na conta de destino.
- Toda transação deve ser registrada para controle e rastreabilidade.
- As responsabilidades devem ficar separadas entre as camadas da aplicação.

---

## Estrutura do Projeto

A estrutura do projeto segue uma organização em camadas, buscando separar responsabilidades e facilitar a manutenção do código.

```bash
src
 └── main
     └── java
         └── com.jotabank.api
             ├── controllers
             ├── dtos
             ├── models
             ├── repositories
             ├── services
             └── exceptions
```

### Controllers

Camada responsável por receber as requisições HTTP, direcionar os dados para os serviços e retornar as respostas da API.

### Services

Camada onde ficam concentradas as regras de negócio da aplicação, como validações, transferências, débitos, créditos e controle das operações.

### Repositories

Camada responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

### Models / Entities

Representam as entidades principais do sistema, como conta, titular e transação.

### DTOs

Utilizados para transportar dados entre as camadas da aplicação, evitando expor diretamente as entidades e deixando a comunicação da API mais organizada.

### Exceptions

Camada utilizada para tratar erros e cenários inválidos de forma mais clara e controlada.

---

## Principais Entidades

### Conta

Representa uma conta bancária dentro da aplicação.

Pode conter informações como identificador, saldo, titular e relacionamento com transações.

### Titular

Representa o cliente responsável por uma conta bancária.

Pode conter dados pessoais, como nome e CPF.

### Transação

Representa uma movimentação financeira realizada entre contas.

É utilizada para registrar operações como transferências, débito e crédito.

---

## Exemplos de Operações

### Buscar conta por ID

```http
GET /contas/{id}
```

### Buscar conta por CPF do titular

```http
GET /contas/cpf/{cpf}
```

### Atualizar dados da conta

```http
PATCH /contas/{id}
```

### Realizar transferência

```http
POST /transacoes
```

> Os endpoints podem ser ajustados conforme a evolução do projeto.

---

## Como Executar o Projeto

### Pré-requisitos

Antes de iniciar, é necessário ter instalado em sua máquina:

- Java
- Maven
- Git
- Uma IDE de sua preferência, como IntelliJ IDEA, Eclipse ou VS Code

---

### Clonando o Repositório

```bash
git clone https://github.com/JhonOliv/api_jotabank_springboot.git
```

### Acessando a Pasta do Projeto

```bash
cd api_jotabank_springboot
```

### Executando a Aplicação

```bash
mvn spring-boot:run
```

Após iniciar o projeto, a aplicação estará disponível em:

```bash
http://localhost:8080
```

---

## Banco de Dados H2

O projeto utiliza o banco de dados H2 para facilitar o desenvolvimento e os testes locais.

A interface web do H2 pode ser acessada em:

```bash
http://localhost:8080/h2-console
```

Configurações como URL, usuário e senha podem ser verificadas no arquivo:

```bash
src/main/resources/application.properties
```

---

## Conhecimentos Consolidados

Durante o desenvolvimento da API JotaBank, foram praticados e consolidados conhecimentos importantes para o desenvolvimento backend, como:

- Criação de APIs REST.
- Organização de projeto com Spring Boot.
- Mapeamento de entidades com JPA.
- Relacionamento entre entidades.
- Uso do Hibernate para persistência.
- Criação de repositories com Spring Data JPA.
- Desenvolvimento de services com regras de negócio.
- Criação de controllers para exposição dos endpoints.
- Uso de DTOs para entrada e saída de dados.
- Validações de dados e regras financeiras.
- Criação de exceptions personalizadas.
- Separação de responsabilidades entre camadas.
- Manipulação de dados com H2 Database.
- Uso de Git e GitHub para versionamento.
- Construção gradual de uma aplicação backend mais próxima de cenários reais.

---

## Melhorias Futuras

Algumas melhorias planejadas para as próximas versões do projeto:

- Implementar autenticação e autorização com Spring Security.
- Adicionar autenticação com JWT.
- Criar testes unitários e testes de integração.
- Documentar a API com Swagger/OpenAPI.
- Migrar o banco de dados de H2 para PostgreSQL.
- Adicionar Docker ao projeto.
- Melhorar o tratamento global de exceções.
- Criar respostas padronizadas para a API.
- Implementar paginação e filtros nas consultas.
- Melhorar a cobertura de validações.
- Evoluir a estrutura para um padrão mais robusto e escalável.

---

## Status do Projeto

Em desenvolvimento.

O projeto está sendo construído de forma gradual, com foco em aprendizado, prática e evolução contínua como desenvolvedor backend Java.

---

## Autor

Desenvolvido por **Jhonatan Silva**.

GitHub: [JhonOliv](https://github.com/JhonOliv)

LinkedIn: [Jhonatan Silva](https://www.linkedin.com/in/jhonatanosilva)

---

## Considerações

Este projeto representa uma etapa importante na minha jornada de evolução como desenvolvedor backend.

A API JotaBank tem sido uma oportunidade prática para aplicar conceitos fundamentais, enfrentar desafios reais de lógica e arquitetura, além de melhorar minha capacidade de construir soluções backend mais organizadas, seguras e escaláveis.