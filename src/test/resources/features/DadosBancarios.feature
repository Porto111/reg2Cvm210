Feature: Company Account.Feature

@CT01
Scenario: CT01- Validar tela "1.Empresa e conta" fluxo "Trazer investimentos"
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente entra no fluxo "Trazer investimentos" a tela "1.Empresa e conta" deve ser apresenada conforme protótipo
  And o drop-down "Empresa/CNPJ-" está habilitado e funcional
  And o drop-down "Agência/Conta-" está habilitado e funcional
  Then os botão "Continuar" e "Voltar" são apresentados em tela

@CT02
Scenario: CT02- Validar botão "Continuar" da tela "1.Empresa e conta"
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente entra no fluxo "Trazer investimentos" a tela "1.Empresa e conta" deve ser apresenada conforme protótipo
  And o drop-down "Empresa/CNPJ-" está habilitado e funcional
  And o drop-down "Agência/Conta-" está habilitado e funcional
  Then ao clicar no botão "Continuar" será apresentada a lista com parte dos bancos cadastrados

@CT03
Scenario: CT03- Validar botão "Voltar" da tela "1.Empresa e conta"
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente entra no fluxo "Trazer investimentos" a tela "1.Empresa e conta" deve ser apresenada conforme protótipo
  And o drop-down "Empresa/CNPJ-" está habilitado e funcional
  And o drop-down "Agência/Conta-" está habilitado e funcional
  Then ao clicar no botão "Voltar" será direcionado a tela inicial do menu investimentos

@CT04
Scenario: CT04- Apresentação da lista completa de bancos
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o campo	"Buscar instituição" está habilitado
  Then deverá ser apresentada a lista com parte dos bancos cadastrados

@CT05
Scenario: CT05- Busca inteligente por nome do banco
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o cliente digitar "BRA" no campo de busca
  Then deverá ser apresentada a opção "Bradesco" e outras opções que comecem com "BRA"

@CT06
Scenario: CT06- Busca inteligente por iniciais do banco
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o cliente digitar "san" no campo de busca
  Then deverá ser apresentada a opção "Santander" e outras opções que contenham o termo inserido na busca

@CT07
Scenario: CT07- Apresentação da listagem em ordem alfabética
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  Then a listagem dos bancos deverá ser apresentada em ordem alfabética

@CT08
Scenario: CT08- Busca por código de banco com duas letras
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o cliente digitar "itau" no campo de busca
  Then deverá ser apresentada a opção "Itaú" na busca

@CT09
Scenario: CT09- Busca por nome de banco com três letras
  Given que o cliente possui perfil de gestor Net Empresa
  When o cliente está na tela "Lista de Instituições"
  And o cliente digitar "BRA" no campo de busca
  Then deverá ser apresentada todas as opções iniciadas em "BRA" na busca
