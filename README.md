# 🌾 Agroforte API

API desenvolvida em Spring Boot para o gerenciamento de pessoas físicas, operações financeiras e parcelas no contexto de agronegócio.

---

## 🚀 Como executar

Certifique-se de ter o Java 21+ e o Maven instalados.

Execute o seguinte comando para subir a aplicação localmente:

```bash
./mvnw spring-boot:run
```

A API estará acessível via:
```
http://localhost:8080
```

---

## 🗃️ Banco de Dados - H2

Esta aplicação utiliza o banco de dados H2 em memória, ideal para desenvolvimento e testes rápidos.

- Console Web H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:agroforte`
- Username: `sa`
- Senha: _(em branco)_

> Configurações completas estão no arquivo `src/main/resources/application.properties`.

---

## 🧱 Arquitetura

A aplicação segue a arquitetura MVC (Model-View-Controller) com separação em pacotes:

- `controller`: Camada de entrada da API (REST Controllers)
- `service`: Lógica de negócio
- `repository`: Interface com o banco de dados (Spring Data JPA)
- `entity`: Mapeamento JPA das entidades persistidas
- `dto`: Objetos de transferência de dados
- `mapper`: Conversores entre entidades e DTOs (MapStruct)

---

## ✉️ DTOs

Os DTOs (`Data Transfer Objects`) são usados para separar a API do modelo de persistência. Por exemplo:

- `PessoaFisicaRequest` — usado para receber dados via POST/PUT.
- `PessoaFisicaResponse` — usado para enviar dados de resposta com segurança e clareza.

---

## 🧪 Testes

A aplicação conta com **testes integrados** usando Spring Boot Test, com suporte a:

- MockMvc para simulação de requisições HTTP
- Base H2 em memória para testes com repositórios
- Testes de controllers com dependências mockadas
- Cobertura de endpoints REST

> Os testes estão localizados em `src/test/java/com/agroforte`.

---

## 📈 Pontos de evolução

Aqui estão algumas melhorias e incrementos planejados:

- ✅ Adição de logs com injeção via `LoggerFactoryComponent`
- 🔄 Validação de CPF (ex. via biblioteca específica)
- 📦 Swagger/OpenAPI para documentação automática
- 🔐 Implementar segurança com JWT
- 🧪 Aumentar cobertura de testes com cenários negativos
- 📊 Integração futura com base de dados PostgreSQL para ambientes de produção

---

## 📌 Tecnologias utilizadas

- Java 21+
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Lombok
- Jakarta Validation
- JUnit 5 e Mockito
- Maven

---

## 🔗 Repositório

O código-fonte está disponível no GitHub:  
[github.com/marcoseduardoqueiroz/agroforte-api](https://github.com/marcoseduardoqueiroz/agroforte-api)

---

## 👨‍💻 Autor

Marcos Queiroz — desenvolvimento e arquitetura da Agroforte API