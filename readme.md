
# API de Gerenciamento de Usuários

Bem-vindo à documentação da API de gerenciamento de usuários. Essa API permite operações CRUD (Create, Read, Update, Delete) para usuários, além de registro e login.



## Documentação da API

#### Retorna todos os itens

```http
  Post /api/auth/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

#### add(num1, num2)

Parâmetro	Tipo	Descrição
email	string	Obrigatório. E-mail do usuário
password	string	Obrigatório. Senha do usuário
Recebe dois números e retorna a sua soma.


## Autenticação
Antes de realizar operações que modificam dados (Create, Update, Delete), você precisa se autenticar. Utilize os endpoints register e login para obter um token de acesso. Para endpoints que exigem autenticação, adicione o token como um Bearer Token no cabeçalho de autorização.
## Instalação

Requerimentos

1 - Docker

2 - docker-ompose

```bash
    docker compose up // na pasta raíz do projeto
```
Os serviços incluem um banco de dados MySQL, a aplicação Spring Boot e o PhpMyAdmin para gerenciamento do banco de dados.

A aplicação estará disponível em http://localhost:8080, e o PhpMyAdmin estará disponível em http://localhost:8090, a senha de acesso e todas estão no docker-compose