
# Investimentos - Projeto de Automação de Testes Regressivos


Projeto de **automação web** de testes regressivos para as funcionalidades de Investimentos. Este projeto utiliza alguns frameworks de automação do Bradesco, Selenium, Java e Cucumber.



## Funcionalidades

Fundos de Investimento

- Aplicar / Agendar
- Resgatar / Agendar

## Pré-requisitos

- Java 11
- Maven 3.9.5
- Git
- Acesso ao Nexus do Bradesco
- Acesso ao CERTS (Centro de Certificação de Sistemas)
- Configurar os arquivos settings.xml e settings-security.xml na pasta .m2

Para mais informações: [(Manual) Configuração pacote de automação](https://confluence.bradesco.com.br:8443/pages/viewpage.action?pageId=873694124 )
## Rodando localmente

Clone o projeto

```bash
  git clone https://bitbucket.bradesco.com.br:8443/scm/netea/ofpj-aut-fundos-web.git
```

Entre no diretório do projeto

```bash
  cd <pasta_local_do_seu_projeto>
```

Instale as dependências

```bash
  mvn install
```

Rode os testes

```bash
  mvn test
```


## Bamboo

Link: https://bamboo.bradesco.com.br:8443/browse/NETEA-OFFUNDS

## Autores

Nayra de Oliveira Guandalini    
Analista de Testes / QA

    nayra.guandalini@solutis.com.br

    nayra.guandalini@bradesco.com.br


