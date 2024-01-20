
# API de Gerenciamento de Usuários

Bem-vindo à documentação da API de gerenciamento de usuários. Essa API permite operações CRUD (Create, Read, Update, Delete) para usuários, além de registro e login.



## Documentação da API

#### Cadastro para autenticar e autorizar

```http
  Post /api/auth/register
```

```json
{
  "email":"email@email.com",
  "firstname":"exemplo",
  "lastname":"exemplo",
  "password":"123"

}

```

#### Retorna um token jwt

```http
  POST /api/auth/login
```

```json
{
  "email":"email@email.com",
  "password":"123"
}

```
#### Retorna um token jwt




## Autenticação
Antes de realizar operações que modificam dados (Create, Update, Delete), você precisa se autenticar. Utilize os endpoints register e login para obter um token de acesso. Para endpoints que exigem autenticação, adicione o token como um Bearer Token no cabeçalho de autorização `Authorization`.


# Operações de Crud

```http
  Post /api/service/create
```
```json
{
  "cpf": "0",
  "nome": "nome exemplo",
  "dataNascimento": "1000-01-01",
  "endereco": {
    "rua": "Rua exemplo",
    "numero": "555",
    "complemento": "Apto 45",
    "bairro": "Bairro Exemplo",
    "cidade": "Cidade Exemplo",
    "estado": "Estado Exemplo",
    "cep": "12345-678"
  }
}
```

#### Cria um novo usuário



## Instalação

* Requerimentos
    * java 17 
    * maven
    * docker
    * docker-compose

## Como executar o projeto

```bash
    cd app // acessar pasta raiz
```
```bash
    mvn clean install // instalar dependências e gerar jar
```
```bash
    docker build -t testeapi . // buildar imagem da aplicação
```
```bash
    docker compose up // buildar imagem da aplicação
```
Os serviços incluem um banco de dados MySQL, a aplicação Spring Boot.

A aplicação estará disponível em http://localhost:8080, e o banco estará disponível em jdbc:mysql://localhost:3306/testdb, a senha de acesso esta disponível no docker-compose.