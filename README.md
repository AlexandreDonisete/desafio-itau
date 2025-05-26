# Teste - Backend Itaú

## Sobre o projeto
Este projeto foi desenvolvido como parte de um desafio técnico de Backend proposto pelo Itaú. O sistema é responsável por gerenciar transações financeiras e gerar estatísticas em tempo real, utilizando uma API REST.

O principal objetivo é servir como um estudo prático, promovendo o aprendizado e a experimentação com novas tecnologias no ecossistema Java e Spring Boot.

O desenvolvimento foi realizado acompanhando, analisando e estudando os vídeos do canal Javanauta, que serviram como base conceitual e prática para a implementação da solução.

## Tecnologias utilizadas
- Java
- Spring Boot
- Swagger (OpenAPI)
- Spring Boot Actuator
- Gradle
- Docker
- JUnit
  
## Swagger
- Swagger disponivel na url ```http://localhost:8080/swagger-ui/index.html#/```
![Swagger](https://github.com/AlexandreDonisete/desafio-itau/blob/main/src/assets/swagger.png)

## Actuator
- Actuator disponivel na url ```http://localhost:8080/actuator```
![Swagger](https://github.com/AlexandreDonisete/desafio-itau/blob/main/src/assets/actuator.png)

## Como executar o projeto

```bash
# Clonando repositório
git clone https://github.com/AlexandreDonisete/desafio-itau.git
cd desafio-itau

Executando com Docker:
./gradlew build
docker build -t desafio-itau .
docker run -p 8080:8080 desafio-itau

Executando pela IDE (sem Docker)
./gradlew bootRun


```

## Autor

Alexandre Donisete Bezerra Filho

https://www.linkedin.com/in/alexandre-donisete/
