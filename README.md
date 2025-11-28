# desafio-hackathon-zup-byte

> Repositório base para o Desafio Hackathon Zup Byte: Modernização de Código com StackSpot AI

## Visão Geral

Este projeto simula um sistema legado em Java (monolito, Spring 4, Hibernate 4, Java 7) e serve como ponto de partida para experimentos de modernização automatizada. O objetivo é transformar este sistema, utilizando StackSpot AI, em um código alinhado com práticas modernas de desenvolvimento e arquitetura.

**Por quê?**  
Empresas enfrentam dificuldades para escalar, manter e evoluir sistemas monolíticos devido ao acúmulo de tecnologia defasada. Este repositório é um laboratório para testar soluções reais para esse problema de mercado.

## Como Rodar

### Pré-requisitos

- Java 7 (exatamente)
- Maven 3.x

### Passos

1. Clone o projeto:
   ```bash
   git clone https://github.com/seu-usuario/empresa-legacy-modernization.git
   cd empresa-legacy-modernization
   ```

2. Configure o Java 7:
   ```bash
   export JAVA_HOME=/caminho/para/java7
   export PATH=$JAVA_HOME/bin:$PATH
   java -version # Deve mostrar 1.7.x
   ```

3. Compile:
   ```bash
   mvn clean compile
   ```

4. Execute:
   ```bash
   mvn exec:java -Dexec.mainClass="com.legacy.Main"
   ```
   *(Certifique-se de ter o exec-maven-plugin configurado no pom.xml)*

### Observações

- Banco de dados H2 em memória: dados não persistem após o encerramento da aplicação.
- Para modernizar, siga o fluxo detalhado em [CHALLENGE.md](CHALLENGE.md).

## Contribua

Documente, questione e sugira melhorias via Pull Requests ou Issues.

---

## Referências

- [StackSpot AI Documentation](https://docs.stackspot.com/)
- [Exec Maven Plugin](https://www.mojohaus.org/exec-maven-plugin/)
