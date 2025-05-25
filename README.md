<section align="center">

# Spinning Joias - Documentação do Backend

Este documento descreve o passo a passo para a configuração local do backend da Spinning Joias, o passo a passo para rodar testes e seus relatórios, a configuração do Lint, e, soluções para possíveis dificuldades ao rodar localmente.

</section>

## 📌 Sobre o projeto
Spinning Joias é um catálogo de produtos que guarda dados de produtos em carrinho para facilitar a comunicação entre cliente e loja.

---

### ⚙️ Configuração do Ambiente Local

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

#### <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg" width="40" height="40" alt="Logo do Docker"/> Passo a passo para configurar com Docker 

3. Abra o [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/). 

4. Builde e rode o projeto
```
docker compose -f docker-compose.yml build
docker compose -f docker-compose.yml up -d
```
#### <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg" width="40" height="40" alt="Logo do Gradlew"/> Passo a passo para configurar com Gradlew

3. Configure o banco de dados da sua preferência com as configurações do `.env`.

4. Builde e rode o projeto com os comandos abaixo:
```
./gradlew clean build
./gradlew bootRun
```
---
## 📌 Sobre os Testes Automatizados

### 🧪 Biblioteca Utilizada 
- **[JUnit](https://junit.org/junit5/docs/current/user-guide/) 5
 (Jupiter)**: Para execução de testes unitários e de integração. Para rodar os testes, execute:
	```sh
	gradle test
	```

	Se não tiver gradle instalado ou com as variáveis de ambiente configuradas, tente:

	```sh
	./gradlew test
	```

- **[JaCoCo](https://www.baeldung.com/jacoco)** : Para gerar relatórios de cobertura de testes. Para executá-la nesta aplicação, utilize o seguinte comando:
	```sh
	gradle test jacocoTestReport
	```
	Se não tiver gradle instalado ou com as variáveis de ambiente configuradas, tente:
	```sh
	./gradlew test jacocoTestReport
	```


### 📊 Cobertura dos Testes
O relatório será gerado no seguinte caminho: 📂 `./build/reports/jacoco/test/html/index.html`

#### 🔍 O que o JaCoCo analisa?

- **Porcentagem total do código coberto**
- **Instruções cobertas**: Mede quantas instruções individuais do código foram executadas pelos testes.
- **Funções cobertas**: Indica quantas funções foram chamadas e testadas.
- **Ramificações cobertas**: Representa os caminhos de decisão no código (como if/else), garantindo que todas as opções foram testadas.

---

## 📌 Sobre o Lint

### ✏️ Checkstyle no Java

Checkstyle é uma ferramenta de análise estática de código usada em projetos Java para garantir a conformidade com padrões de codificação predefinidos. Ele auxilia na manutenção de um código limpo, consistente e mais fácil de entender, além de prevenir problemas futuros de manutenção.

### Funcionalidades Gerais:
- Verificação de regras de estilo e boas práticas.
- Garantia de conformidade com padrões de codificação.
- Identificação de erros de formatação, complexidade de métodos e uso inconsistente de tabulações.

#### Passo a passo do **.githooks**

-   **1️⃣ Configurar o caminho dos hooks** — Execute o seguinte comando para usar o diretório `.githooks` como o caminho dos hooks.

```sh
    git config core.hooksPath .githooks
```

-   **2️⃣ Verificar permissões de execução** — Caso o script não configure as permissões automaticamente, o desenvolvedor pode fazer isso manualmente com o comando `chmod +x .githooks/*`.

```sh
    chmod +x .githooks/*
```

---

### ✏️ Configuração do Checkstyle

No arquivo 📂 `config/checkstyle/checkstyle.xml`:

#### Configuração do Checkstyle
```xml
<?xml version="1.0"?> // Define a versão do documento XML
<!DOCTYPE module PUBLIC
    "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN"
    "https://checkstyle.org/dtds/configuration_1_3.dtd"> // Referencia a DTD usada para validar a configuração
<module name="Checker"> // Define a configuração principal do Checkstyle
```

#### Propriedades Gerais
```xml
<property name="severity" value="warning"/> // Define o nível de severidade como `warning`, sinalizando avisos
<property name="charset" value="UTF-8"/> // Define a codificação como `UTF-8`
```

#### TreeWalker: Análise Sintática
```xml
<module name="TreeWalker"> // Esse módulo percorre a árvore de sintaxe do código para aplicar verificações específicas
```

#### WhitespaceAfter
```xml
<module name="WhitespaceAfter"/> // Garante que haja um espaço após certos elementos de código, como vírgulas
```

#### MethodParamPad
```xml
<module name="MethodParamPad"/> // Verifica se há espaçamento correto nos parâmetros de métodos
```

#### AvoidStarImport
```xml
<module name="AvoidStarImport"/> // Evita a prática de importar tudo com `import *`, incentivando importações específicas
```

#### Indentation
```xml
<module name="Indentation"> // Configura a largura da tabulação e alinhamentos básicos
    <property name="tabWidth" value="4"/> // Define a largura de cada tabulação como 4 espaços
    <property name="basicOffset" value="4"/> // Configura o deslocamento básico como 4 espaços
    <property name="braceAdjustment" value="4"/> // Ajusta o alinhamento de chaves com 4 espaços
    <property name="arrayInitIndent" value="8"/> // Configura a indentação para inicializações de arrays com 8 espaços
</module>
```

#### MethodLength
```xml
<module name="MethodLength"> // Limita o número máximo de linhas em um método
    <property name="max" value="20"/> // Define o limite para 20 linhas
</module>
```

## 🐘 Configuração Gradle 
No arquivo 📂 `build.gradle`:

```gradle
id 'checkstyle' // Declara a aplicação do plugin Checkstyle no Gradle
```

#### Propriedades Checkstyle
```gradle
checkstyle {
    toolVersion = '10.12.5' // Define a versão da ferramenta Checkstyle
    configFile = rootProject.file("config/checkstyle/checkstyle.xml") // Localiza o arquivo de configuração XML do Checkstyle
    ignoreFailures = false // Configura para não ignorar falhas detectadas
    showViolations = true // Mostra as violações encontradas
}
```

#### Relatórios
```gradle
tasks.withType(Checkstyle).configureEach {
    reports {
        html.required = true // Gera um relatório em formato HTML
        html.outputLocation = file("config/checkstyle/main.html") // Define o local de saída para o relatório HTML
    }
}
```

---

## 📌 Sobre possíveis problemas para rodar localmente

### 1. Erro: Porta 8080 já está em uso
#### Solução 1:
Se já houver um processo ocupando a porta 8080 e ele não for necessário para a aplicação, é possível localizar seu PID através do seguinte comando:
```sh
netstat -ano | findstr :8080
```
O PID do processo será o último número a aparecer na linha retornada. Após isso, utilizamos o seguinte comando para matar o processo, onde `<PID>` deve ser substituído pelo ID obtido no comando anterior:


```sh
taskkill /PID <PID> /F
```

Depois, inicie a aplicação novamente.


#### Solução 2:

Se a porta padrão (8080) estiver ocupada por um processo essencial, é possível alterar a porta padrão da aplicação no arquivo `application.properties`:

```properties
spring.application.name=spinningback
server.port=9090
```
Depois, inicie a aplicação novamente.

_Obs.: Utilizando a porta do exemplo acima, a aplicação estará disponível em `http://localhost:9090/`_


### 2. Erro: "Connection Refused" ao conectar no PostgreSQL

#### Solução:
Verifique se o banco de dados está em execução:

```sh
docker ps
```
Se o container não estiver rodando, inicie-o com:

```sh
docker start spinningback-db
```

### 3. Erro: Falta de execução de pre-commit e commit-msg (hooks)

#### Solução:

-   **1️⃣ Configurar o caminho dos hooks** — Execute o comando abaixo para usar o diretório `.githooks` como o caminho dos hooks.

	```sh
		git config core.hooksPath .githooks
	```

-   **2️⃣ Verificar permissões de execução** — Caso o script não configure as permissões automaticamente, deve ser feito manualmente com o comando abaixo.

	```sh
		chmod +x .githooks/*
	```
