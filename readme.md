
# API de Gerenciamento de Usuários

API de gerenciamento de usuários. Essa API permite operações CRUD (Create, Read, Update, Delete) para usuários,
além de registro e login.


## Documentação da API

#### Cadastro para autenticar e autorizar

```http
  Post /api/auth/register
```

```json
{
  "email":"email@email.com", //obrigatório chave única
  "firstname":"exemplo", 
  "lastname":"exemplo", 
  "password":"123" //obrigatório

}

```

#### Retorna um token jwt

```http
  POST /api/auth/login
```

```json
{
  "email":"email@email.com", //obrigatório
  "password":"123" //obrigatório
}

```
#### Retorna um token jwt



## Autenticação
Antes de realizar operações que modificam dados (Create, Update, Delete), você precisa se autenticar.
Utilize os endpoints register e login para obter um token de acesso.
Para endpoints que exigem autenticação, adicione o token como um Bearer Token no cabeçalho de autorização `Authorization`.


# Operações de Crud

```http
  Post /api/service/create
```
```json
{
  "cpf": "0", // chave única, ao tentar inserir o mesmo cpf duas vezes ocorrerá um erro
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

<hr>

```http
  GET /api/service/users
```
#### Retorna todos os usuários ativos
<hr>

```http
  Get /api/service/user?cpf=?
```

#### Retorna usuário pelo número de cpf

<hr>

```http
  Post /api/service/update/{cpf}
```
```json
{
   // campos para update
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
####  Retorna 204 Em caso de sucesso

<hr>

```http
  Get /api/service/delete/{cpf}
```

#### Realiza um delete lógico do usuário

<hr>

Caso possua postman o arquivo collections.json possui todas as chamadas disponiveis a api.

## Instalação

* Requerimentos
  * java 17
  * maven
  * docker
  * docker-compose

## Como executar o projeto

```bash
    cd app
```
```bash
    mvn clean install
```
```bash
    docker build -t testeapi .  
```
```bash
    docker compose up  
```
Os serviços incluem um banco de dados MySQL, a aplicação Spring Boot.
A aplicação estará disponível em http://localhost:8080, e o banco estará disponível
em jdbc:mysql://localhost:3306/testdb, a senha de acesso está disponível no docker-compose.
