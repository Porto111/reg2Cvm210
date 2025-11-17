Feature: Institution Search.Feature

@CT10
Scenario: CT10- Busca por nome de corretora
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o cliente digitar "agora" no campo de busca
  And será apresentada a opção "AGORA CTVM S.A." na busca
  And ao clicar no botão "Selecionar" será direcionado para a tela "Dados da conta"
  Then inserir os dados de agencia e conta clicar no botão "Continuar" será direcionado a tela "Escolha como quer fazer a portabilidade"

@CT11
Scenario: CT11- Validar tela "3.Dados da conta" instituição tipo Banco
  Given que o cliente possui perfil de gestor Net Empresa está no fluxo "Trazer investimentos"
  And optou por trazer seu investimento do "Banco do Brasil"
  When o cliente está na tela "3.Dado da conta"
  Then inserir os dados de agencia e conta clicar no botão "Continuar" será direcionado a tela "4.Tipo de solicitação"

@CT12
Scenario: CT12- Validar tela "3.Dados da conta" instituição tipo Corretora
  Given que o cliente possui perfil de gestor Net Empresa está no fluxo "Trazer investimentos"
  And optou por trazer seu investimento do "Banco do Brasil"
  When o cliente está na tela "3.Dado da conta"
  Then inserir os dados da conta clicar no botão "Continuar" será direcionado a tela "4.Tipo de solicitação"

@CT13
Scenario: CT13- Validar botão "Cancelar" da tela "3.Dados da conta"
  Given que o cliente possui perfil de gestor Net Empresa está no fluxo "Trazer investimentos"
  And optou por trazer seu investimento do "Banco do Brasil"
  When o cliente está na tela "3.Dado da conta"
  Then inserir os dados da conta clicar no botão "Cancelar" será direcionado a tela inicial do menu investimentos

@CT14
Scenario: CT14- Validar botão "Voltar" da tela "3.Dados da conta"
  Given que o cliente possui perfil de gestor Net Empresa está no fluxo "Trazer investimentos"
  And optou por trazer seu investimento do "Banco do Brasil"
  When o cliente está na tela "3.Dado da conta"
  Then inserir os dados da conta clicar no botão "Voltar" será direcionado a tela "2.Lista de instituições"

@CT15
Scenario: CT15- Exibir opção "Por Categoria"  e "Por Ativo"
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And a tela "Escolha como quer fazer a portabilidade"
  Then devem ser exibidas as opções "Por Categoria"  e "Por Ativo" com o botão "Selecionar" habilitado

@CT16
Scenario: CT16- Selecionar a opção "Por Categoria"
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And a tela "Escolha como quer fazer a portabilidade" clicar no botão "Selecionar" da opção "Por Categoria"
  Then deve ser direcionado para a tela "5.Seleção"

@CT17
Scenario: CT17- Exibir todas as categorias disponíveis para a tela "5.Seleção"
  Given que o usuário está na tela de seleção de categorias da opção "Por Categoria"
  When visualizar as opções
  Then deve ver as categorias "Renda Fixa" e "Fundos" listadas

@CT18
Scenario: CT18- Exibir a opção "Selecionar todos"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Categoria"
  Then deve ser exibida a opção "Selecionar todos" com o check box habilitado

@CT19
Scenario: CT19- Selecionar todas as categorias
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Categoria"
  And marcar a opção "Selecionar todos"
  Then todas as categorias devem ser marcadas

@CT20
Scenario: CT20- Selecionar uma categoria específica
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Categoria"
  And marcar apenas a categoria "Renda Fixa"
  Then apenas "Renda Fixa" deve ser marcada
