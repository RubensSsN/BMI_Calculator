# Calculadora de IMC

Este projeto é uma calculadora de Índice de Massa Corporal (IMC) desenvolvida em Java com uma interface gráfica utilizando Swing. A aplicação salva os registros de IMC em um banco de dados em memória H2.

## Funcionalidades

- Calcular o IMC a partir da altura e peso fornecidos.
- Exibir o resultado do cálculo do IMC.
- Salvar os registros de cálculo de IMC em um banco de dados.
- Exibir todos os registros de cálculos de IMC.

## Tecnologias Utilizadas

- Java
- Swing para a interface gráfica
- H2 Database para armazenamento em memória
- Maven para gerenciamento de dependências

## Como Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/RubensSsN/BMI_Calculator.git
    cd BMI_Calculator
    ```

2. Compile o projeto utilizando Maven:
    ```bash
    mvn clean install
    ```

3. Execute a aplicação:
    ```bash
    mvn exec:java -Dexec.mainClass="com.example.BMICalculator"
    ```

## Estrutura do Projeto

- `BMICalculator.java`: Interface gráfica e lógica de cálculo de IMC.
- `DatabaseConnection.java`: Configuração da conexão com o banco de dados H2.
- `BMIRecord.java`: Modelo de dados para os registros de IMC.
- `BMIRecordDAO.java`: Acesso aos dados (DAO) para manipulação dos registros de IMC.
- `DataAccessException.java`: Exceção personalizada para erros de acesso a dados.
- `InvalidInputException.java`: Exceção personalizada para entradas inválidas.

## Exceções

- `InvalidInputException`: Lançada quando a entrada do usuário é inválida (por exemplo, altura ou peso não positivos).
- `DataAccessException`: Lançada quando ocorre um erro ao acessar o banco de dados.
