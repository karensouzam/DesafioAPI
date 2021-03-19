# Desafio testes de API - RestAssured
- Arquitetura Projeto
    - Linguagem - Java
    - Orquestrador de testes - [Maven 3.6.3](https://maven.apache.org/download.cgi)
    - Relatório de testes automatizados - [ExtentReports 4.1.3](https://mvnrepository.com/artifact/com.aventstack/extentreports/4.1.3)
    - Framework interação com API - [Rest Assured 4.3.0](https://github.com/rest-assured/rest-assured/wiki/Downloads)
    - Denpendências do projeto (pom.xml) - [Maven Repository](https://mvnrepository.com/)

Metas

- [X] 1.Implementar 50 scripts de testes que manipulem uma aplicação cuja interface é uma API REST. 
Todos os testes podem ser vistos na pasta testes e nas respectivas subpastas:
```python
Issues
Projects
Users
```

- [X] 2.Alguns scripts devem ler dados de uma planilha Excel para implementar Data-Driven.
Foi implementado na classe ```PostProjectsTests.java``` com o método ```deveIncluirProjetoComSucessoComDDT()```. 

- [X] 3.O projeto deve tratar autenticação. Exemplo: OAuth2.
A aplicação de API do Mantis, exige autenticação via API Token. O token (gerado pela interface do sistema) foi utilizado em todos os testes, sendo fornecido no request como atributo Header da solicitação.

- [X] 4.Pelo menos um teste deve fazer a validação usando REGEX (Expressões Regulares). 
Foi utilizado método de asserção ```.matches()``` no método ```CriarUsuarioComSucesso()``` da classe ```PostUsuarioTests.java```.

- [X] 5.O projeto deverá gerar um relatório de testes automaticamente. 
O relatório é gerado a cada execução dos testes e armazenado na pasta reports do projeto.

- [X] 6.Implementar pelo menos dois ambientes (desenvolvimento / homologação). 
As configurações dos ambientes encontram-se no arquivo ```globalParameters.properties``` na raiz do projeto.

- [X] 7.A massa de testes deve ser preparada neste projeto, seja com scripts carregando massa nova no BD ou com restore de banco de dados. 
Os dados são apagados ao final da execução de todos os testes e são inseridos gradualmente nas classes de testes conforme necessidade.

- [X] 8.Executar testes em paralelo. Pelo menos duas threads (25 testes cada). 
A configuração das Threads foi realizada na suíte de testes ```SuiteTests.xml```.

- [X] 9.Testes deverão ser agendados pelo Jenkins, CircleCI, TFS ou outra ferramenta de CI que preferir. 
Os testes foram agendados pelo Azure Devops, as configurações encontram-se no arquivo ```azure-pipelines.yml``` na raiz do projeto.