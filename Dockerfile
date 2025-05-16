# Etapa 1: Imagem de Testes
FROM gradle:8.4.0-jdk17 AS test

WORKDIR /home/spinning-back

COPY build.gradle .
COPY settings.gradle .
COPY src /home/spinning-back/src

# Definir a variável de ambiente ENVIRONMENT para o perfil de teste
ENV ENVIRONMENT=${ENVIRONMENT}

# Executar os testes com o perfil "test"
RUN gradle test -Dspring.profiles.active=test

# Etapa 2: Imagem de Build
FROM gradle:8.4.0-jdk17 AS build

WORKDIR /home/spinning-back

COPY build.gradle .
COPY settings.gradle .
COPY src /home/spinning-back/src
COPY config/checkstyle/checkstyle.xml /home/spinning-back/config/checkstyle/checkstyle.xml

# Definir a variável de ambiente ENVIRONMENT
ENV ENVIRONMENT=${ENVIRONMENT}

# Construir o projeto
RUN gradle clean build 

# Etapa 3: Imagem Final
FROM openjdk:17

WORKDIR /home/spinning-back

# Copiar o JAR construído da etapa de build
COPY --from=build /home/spinning-back/build/libs/spinningback-0.0.1-SNAPSHOT.jar .

# Definir o ponto de entrada do contêiner
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=$ENVIRONMENT spinningback-0.0.1-SNAPSHOT.jar"]