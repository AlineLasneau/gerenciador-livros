# Gerenciador de Livros

Projeto acadêmico desenvolvido com Java 17, Spring Boot, Spring Data JPA e banco H2. A aplicação oferece uma API REST para organizar uma biblioteca pessoal.

## Funcionalidades

- Cadastrar livro
- Listar livros
- Consultar livro pelo identificador
- Atualizar livro
- Excluir livro

Cada livro contém título, autor, gênero, ano de publicação e status de leitura.

## Requisitos

- JDK 17 ou superior
- IntelliJ IDEA
- Maven (o IntelliJ pode importar o projeto pelo `pom.xml`)

## Como executar no IntelliJ IDEA

1. Extraia o arquivo ZIP.
2. Abra o IntelliJ IDEA e selecione **Open**.
3. Escolha a pasta `gerenciador-livros` (a pasta que contém `pom.xml`).
4. Aguarde o Maven baixar as dependências.
5. Execute `GerenciadorLivrosApplication.java` pelo botão Run.
6. A API ficará disponível em `http://localhost:8080`.

O H2 está configurado em memória: os dados são apagados quando a aplicação é encerrada.

## Endpoints da API

| Método | Endereço | Função |
|---|---|---|
| POST | `/api/livros` | Cadastrar |
| GET | `/api/livros` | Listar todos |
| GET | `/api/livros/{id}` | Consultar por ID |
| PUT | `/api/livros/{id}` | Atualizar |
| DELETE | `/api/livros/{id}` | Excluir |

## Exemplo de cadastro

Envie um `POST` para `http://localhost:8080/api/livros` com o corpo JSON:

```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "genero": "Romance",
  "anoPublicacao": 1899,
  "lido": true
}
```

Use `lido: false` para um livro que ainda não foi lido.

## Testes

No IntelliJ, abra `src/test/java/br/com/biblioteca/livros/LivroControllerTest.java` e execute a classe de testes. Também é possível rodar pela aba Maven, em **Lifecycle > test**, ou no terminal:

```bash
mvn test
```

Os testes verificam cadastro, listagem, consulta, atualização e exclusão.

## Organização do código

- `model/Livro.java`: entidade com atributos e métodos de acesso.
- `repository/LivroRepository.java`: acesso aos dados usando Spring Data JPA.
- `service/LivroService.java`: regras de negócio.
- `controller/LivroController.java`: endpoints HTTP da API.
- `GerenciadorLivrosApplication.java`: ponto de entrada da aplicação.

## Publicar no GitHub

1. Crie um repositório vazio no GitHub chamado `gerenciador-livros`.
2. No terminal do IntelliJ, dentro da pasta do projeto, execute:

```bash
git init
git add .
git commit -m "Projeto inicial: gerenciador de livros"
git branch -M main
git remote add origin https://github.com/SEU-USUARIO/gerenciador-livros.git
git push -u origin main
```

3. Troque `SEU-USUARIO` pelo seu nome de usuário do GitHub.
4. Copie o link real do repositório e envie junto com o código-fonte.

## Observação

Este projeto implementa uma API REST, não uma interface gráfica. As operações podem ser testadas com IntelliJ HTTP Client, Postman ou Insomnia.
