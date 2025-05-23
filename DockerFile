FROM azul/zulu-openjdk:21.0.6

WORKDIR /app

COPY build/libs/desafio-transacao-itau-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
