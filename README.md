# desafio-hackathon-zup-byte

> Repositório base para o Desafio Hackathon Zup Byte: Modernização de Código com StackSpot AI

## Visão Geral

Este projeto simula um sistema legado em Java (monolito, Spring 4, Hibernate 4, Java 7) e serve como ponto de partida para experimentos de modernização automatizada. O objetivo é transformar este sistema, utilizando StackSpot AI, em um código alinhado com práticas modernas de desenvolvimento e arquitetura.

**Por quê?**  
Empresas enfrentam dificuldades para escalar, manter e evoluir sistemas monolíticos devido ao acúmulo de tecnologia defasada. Este repositório é um laboratório para testar soluções reais para esse problema de mercado.

## Como Rodar

### Pré-requisitos

- Java 8 ou superior
- Maven 3.x

### Passos

1. Clone o projeto:
   ```bash
   git clone https://github.com/seu-usuario/desafio-hackathon-zup-byte.git
   cd desafio-hackathon-zup-byte
   ```

2. Compile:
   ```bash
   mvn clean compile
   ```

3. Execute:
   ```bash
   MAVEN_OPTS="--add-opens java.base/java.lang=ALL-UNNAMED" mvn exec:java
   ```

4. Rode os testes (nota: alguns testes têm problemas de compatibilidade que fazem parte do desafio):
   ```bash
   mvn clean test
   ```

### Nota sobre Java 9+

O flag `--add-opens java.base/java.lang=ALL-UNNAMED` é **obrigatório** ao usar Java 9 ou superior, pois Spring 4.x com CGLIB precisa acessar métodos protegidos do Java, o que é bloqueado pelo sistema de módulos introduzido no Java 9+.

### Observações

- **Problemas de compatibilidade**: Este código foi escrito para Java 7 e com dependências antigas. Alguns testes falham em versões modernas de Java (problemas com Mockito e CGLIB).
- **Banco de dados**: H2 em memória - dados não persistem após o encerramento da aplicação.
- **Objetivos do desafio**: Modernizar este código legado, atualizando:
  - Java 8 → Java 21+
  - Spring 4 → Spring 6+
  - Hibernate 4 → Hibernate 6+
  - JUnit 4 → JUnit 5
  - Mockito antigo → Mockito moderno
  - E outras bibliotecas
- Para modernizar, siga o fluxo detalhado em [CHALLENGE.md](CHALLENGE.md).

## Contribua

Documente, questione e sugira melhorias via Pull Requests ou Issues.

---

## Referências

- [StackSpot AI Documentation](https://docs.stackspot.com/)
- [Exec Maven Plugin](https://www.mojohaus.org/exec-maven-plugin/)
