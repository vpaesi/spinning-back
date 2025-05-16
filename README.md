<section align="center">

# Spinning Joias - Documentação do Backend

Este documento descreve o passo a passo para a configuração local do backend da Spinning Joias.

</section>

## 📌 Sobre o projeto
Spinning Joias é um catálogo de produtos que guarda dados de produtos em carrinho para facilitar a comunicação entre cliente e loja.

---

## ⚙️ Configuração do Ambiente Local

<img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="40" height="40" alt="Logo do Java"/> Certifique-se de ter instalado (no mínimo) a versão 17 do [Java JDK](https://www.oracle.com/br/java/technologies/downloads/).


1. Clone o Repositório
```
git clone https://github.com/vpaesi/spinning-back/
cd spinning-back
```

2. Crie um arquivo `.env` na raíz do repositório, utilizando como modelo o `env.example`, e insira seus respectivos valores.
Observação: Para rodar localmente, deixe a variável `"WEB_API"` com valor `*`.


#### ⚠️ A partir destas ações, é possível seguir dois caminhos para realizar a configuração localmente:
- [Com Docker](#️-passo-a-passo-para-configurar-com-docker): _Nesse caso, não é necessária a instalação de um banco de dados, como o postgreSQL, mas certifique-se de ter instalada o Docker Desktop em seu computador._
- [Com Gradlew](#️-passo-a-passo-para-configurar-com-gradlew): _Nesse caso, não é necessária a instalação do Docker Desktop, bem como, não é necessário instalar o Gradle para computador._

### <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg" width="40" height="40" alt="Logo do Docker"/> Passo a passo para configurar com Docker 

3. Abra o [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/). 

4. Builde e rode o projeto
```
docker compose -f docker-compose.yml build
docker compose -f docker-compose.yml up -d
```
### <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg" width="40" height="40" alt="Logo do Gradlew"/> Passo a passo para configurar com Gradlew

3. Configure o banco de dados da sua preferência com as configurações do `.env`.

4. Builde e rode o projeto com os comandos abaixo:
```
./gradlew clean build
./gradlew bootRun
```
