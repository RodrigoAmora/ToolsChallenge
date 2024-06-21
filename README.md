# ToolsChallenge
Descrição:
----------
Projeto de uma API em Java e Spring Boot para simular pagamentos.

Endpoints:
----------
A documentação dos endpoints da API pode ser vista através do Swagger e do Redoc.<br>

Para consultar a documentação dos endpoints através do Swagger: `<URL_DO_PORJETO>/swagger-ui.html` <br>
Para consultar a documentação dos endpoints através do Redoc: `<URL_DO_PORJETO>/redoc.html` <br>

Caso prefira usar o Postman, na pasta collections tem o arquivo com a collection para usar os endpoints via Postman.


Dependências:
-------------
Este projeto usa o Java 21 e as seguintes dependências:
* Spring Boot 3.2.4
* Spring Data JPA
* Spring Security
* Swagger
* OpenAPI
* Devtools
* jUnit
* Rest-Assured
* MySQL
* H2

Banco de dados:
---------------
O projeto usa o MySQL para ambiente de desenvolvimento e o H2 para o ambiente de teste.<br>
Para acessar o painel do H2: `<URL_DO_PORJETO>/h2-console`

Testes:
-------
O projeto possui testes de API.

Rodando o projeto:
------------------
Para iniciar a aplicação via IDE, execute a classe `EventostiApplication`

Para iniciar a aplicação via terminal, execute o comando no terminal na raiz do projeto:

```shell script
mvn spring-boot:run
```

Autor:
------
<b>Rodrigo Amora</b>

LinkedIn: https://linkedin.com/in/rodrigoamora <br>
E-mail: rodrigo.amora.freitas@gmail.com

<hr>
