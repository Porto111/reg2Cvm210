Feature: Categories.Feature

@CT21
Scenario: CT21- Desmarcar uma categoria previamente selecionada
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  And selecionar a opção "Por Categoria"
  When selecionar a categoria "Fundos"
  And desmarcar a categoria "Fundos"
  Then a categoria "Fundos" deve ficar desmarcada

@CT22
Scenario: CT22- Habilitar botão "Continuar" após seleção
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  And selecionar a opção "Por Categoria"
  When selecionar uma das categorias apresentadas em tela "Fundos"
  Then o botão "Continuar" deve ser habilitado

@CT23
Scenario: CT23- Não habilitar botão "Continuar" sem seleção
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  And selecionar a opção "Por Categoria"
  When não clicar em nenhuma das categorias apresenadas em tela
  Then o botão "Continuar" deve permanecer desabilitado

@CT24
Scenario: CT24- Clicar no botão "Continuar" com categoria selecionada fluxo trazer investimentos
  Given que o usuário selecionou a categoria "Renda Fixa"
  When clicar no botão "Continuar"
  Then deve ser direcionado para a tela "6.confirmação"

@CT25
Scenario: CT25- Fluxo trazer investimentos clicar no botão "Cancelar" e retornar ao menu investimentos 
  Given que o usuário está na tela "4.Tipo de solicitação"
  When clicar no botão "Cancelar"
  Then deve ser direcionado para o menu principal de "Investimentos"

@CT26
Scenario: CT26- Clicar no botão "Voltar" e retornar para a tela anterior fluxo trazer investimentos
  Given que o usuário está na tela "4.Tipo de solicitação"
  When clicar no botão "Voltar"
  Then deve ser direcionado para a tela "3.Dados da conta"

@CT27
Scenario: CT27- Garantir que todos os produtos selecionados sejam incluídos na solicitação fluxo trazer investimentos
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Categoria"
  And marcar a opção "Selecionar todos"
  And prosseguir com a portabilidade
  Then todos os produtos da categoria selecionada devem ser incluídos na solicitação

@CT28
Scenario: CT28- Garantir que todas as categorias marcadas são refletidas na solicitação final
  Given que o usuário selecionou múltiplas categorias "Por ativo" e "Por Categoria"
  And seguir para tela de confirmação
  When todas as categorias selecionadas devem estar refletidas
  Then inserir o token e clicar no botão "Continuar" é concluída a solicitação de portabilidade

@CT29
Scenario: CT29- Exibir opção "Por Ativo"
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And a tela "Escolha como quer fazer a portabilidade"
  Then devem ser exibidas as opções "Por Ativo" e "Por Categoria" com o botão "Selecionar" habilitado

@CT30
Scenario: CT30- Selecionar a opção "Por Ativo"
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And a tela "Escolha como quer fazer a portabilidade" clicar no botão "Selecionar" da opção "Por Ativo"
  Then deve ser direcionado para a tela "Escolha a categoria para trazer os ativos"

@CT31
Scenario: CT31- Exibir todas as categorias disponíveis
  Given que o usuário está na tela de seleção de categorias
  When visualizar as opções
  Then deve ver as categorias "Renda Fixa" e "Fundos" listadas

@CT32
Scenario: CT32- Selecionar uma das categorias disponíveis
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por ativo"
  And deve selecionar "Renda fixa" ou "Fundos" através do botão selecionar habilitado
  Then o popup para inclusão do ativo deve ser apresentado

@CT33
Scenario: CT33- Selecionar uma categoria específica
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Ativo"
  And selecionar a categoria "Renda Fixa"
  Then a popup "Informe os dados do ativo para portabilidade" deve ser apresentado

@CT34
Scenario: CT34- Selecionar a categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Ativo"
  And selecionar a categoria "Renda Fixa"
  Then o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  And os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-" estão habilitados

@CT35
Scenario: CT35- Validar campos para preenchimento no popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-" estão habilitados e funcionais

@CT36
Scenario: CT36- Validar funcionamento dos campos para preenchimento no popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-" estão habilitados e funcionais
  And abaixo dos campos "Código do ativo-" e "Quantidade" deve trazer os exemplos de como preencher cada campo

@CT37
Scenario: CT37- Validar funcionamento do campo para preenchimento "Nome do ativo" no popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then o campo "Nome do ativo-" está habilitado
  And permitie a inserção de letras e números

@CT38
Scenario: CT38- Validar funcionamento do campo para preenchimento "Código do ativo" no popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then o campo "Código do ativo-" está habilitado
  And permitie a inserção de até 14 algarismos numéricos

@CT39
Scenario: CT39- Validar funcionamento do campo para preenchimento "Quantidade" no popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then o campo "Quantidade-" está habilitado
  And permitie a inserção de até 4 algarismos numéricos

@CT40
Scenario: CT40- Validar o popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-" estão habilitados e funcionais
  And abaixo dos campos "Código do ativo" e "Quantidade" deve trazer os exemplos de como preencher cada campo
  And os botões "Incluir na solicitação" e "X(fehcar)" estão disponíveis na tela
