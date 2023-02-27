# ecommerce-orchestrator
Orquestrador de e-commerce (case Renner)

## Tecnologias utilizadas no projeto

- Java 11
- Spring Boot 2.5
- Docker
- Postgres
- Kafka
- swagger

Testes
- Cucumber
- Junit4 
- Mockito
- H2

## Build
``` mvn clean install ```

Após buildar a aplicação é necessário subir o Postgres e o Kafka através do docker-compose

``` docker-compose up ```

## Run

Para rodar a aplicação

``` mvn spring-boot:run ```


## Swagger
localhost:8080/swagger-ui.html





