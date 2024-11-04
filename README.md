# ToolsChallenge
Descrição:
----------
Projeto de uma API em Java e Spring Boot para simular pagamentos.

Endpoints:
----------
A documentação dos endpoints pode ser vista através do Swagger e do Redoc.<br>

<b>Documentação dos endpoints via Swagger:</b>
```shell script
http://localhost:8080/swagger-ui.html
```

<b>Documentação dos endpoints via Redoc:</b>
```shell script
http://localhost:8080/redoc.html
```

##
Na pasta <b>`Postman`</b> contém a collection para usar os endpoints via Postman.

Dependências:
-------------
Este projeto usa o Java 21 e as seguintes dependências:
* Spring Boot 3.2.4
* Spring Data JPA
* Spring Security
* Swagger
* OpenAPI
* Devtools
* MySQL
* H2
* jUnit
* Rest-Assured

Banco de dados:
---------------
O projeto usa o MySQL para ambiente de desenvolvimento e o H2 para o ambiente de teste.

##
Para acessar o painel do H2:
```shell script
http://localhost:8080/h2-console
```

<b>Usuário:</b> sa<br>
<b>Senha:</b>

Rodando os Testes:
------------------
O projeto possui testes de API usando o Rest-Assured.<br><br>
Para rodar os testes, execute o comando no terminal na raiz do projeto com o projeto rodando:
```shell script
mvn test
```

Gerando o arquivo .jar:
-----------------------
Para gerar o arquivo <b>.jar</b>, execute o comando via terminal no diretório raiz do projeto:
```shell script
mvn clean install -P{profile} -DskipTests
```

Rodando o projeto:
------------------
Para iniciar a aplicação via IDE, execute a classe `EventostiApplication`

Para iniciar a aplicação via terminal, execute o comando no terminal na raiz do projeto:

```shell script
mvn spring-boot:run
```

Docker:
-------
Para rodar o projeto em um container Docker, primeiro deve-se gerar o .jar do projeto.<br>
Após isso, deve-se gerar o build e subir os containers do Docker.<br><br>
<b>Fazendo o build dos containers do Docker:</b>
```shell script
docker-compose build

```

<b>Subindo os containers do Docker:</b>
```shell script
docker-compose up -d
```

##
Para automatizar esse processo, basta executar o Shellscript <b>`docker_build_and_run.sh`</b> na raiz do projeto:
```shell script
./docker_build_and_run.sh
```

Autor:
------
<b>Rodrigo Amora</b>

LinkedIn: https://linkedin.com/in/rodrigoamora <br>
E-mail: rodrigo.amora.freitas@gmail.com
