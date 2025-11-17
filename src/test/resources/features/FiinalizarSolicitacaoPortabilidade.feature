Feature: Finalization.Feature

@CT71
Scenario: CT71- Validar o botão "X(fehcar)" no popup "Saiba onde encontrar os dados de fundos" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then um novo popup com o título "Saiba onde encontrar os dados de fundos"
  And clicar no botão "X(fehcar)" disponível em tela então o popup deve ser fechado

@CT72
Scenario: CT72- Exibir tela de confirmação para categoria "Por ativo" tipo Renda fixa
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And preencher os dados de investimento tipo renda fixa
  And clicar no botão "Incluir na solicitação" em seguida clicar no botão "Continuar"
  Then deve ser direcionado para a tela Agora é só confirmar, onde os detalhes da solicitação são apresentados

@CT73
Scenario: CT73- Exibir tela de confirmação para categoria "Por ativo" tipo Fundos
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And preencher os dados de investimento tipo Fundos
  And clicar no botão "Incluir na solicitação" em seguida clicar no botão "Continuar"
  Then deve ser direcionado para a tela Agora é só confirmar, onde os detalhes da solicitação são apresentados

@CT74
Scenario: CT74- Exibir tela de confirmação para categoria "Por ativo" tipo Fundos e Renda fixa
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos"
  And preencher os dados dos investimentos tipo Fundos e Renda fixa
  And clicar no botão "Incluir na solicitação" em seguida clicar no botão "Continuar"
  Then deve ser direcionado para a tela Agora é só confirmar, onde os detalhes da solicitação são apresentados

@CT75
Scenario: CT75- Validar tela de confirmação para categoria "Por ativo" tipo Fundos e Renda fixa
  Given que o usuário está no fluxo "Trazer investimentos"
  When estiver na tela de confirmação da categoria "Por ativo" tipo "Renda fixa" e "Fundos" onde os detalhes da solicitação são apresentados
  And a mensagem "Para concluir o pedido de portabilidade, é preciso acessar a outra instituição e confirmar" é apresentada
  Then o botão "Ativos selecionados" está habilitado

@CT76
Scenario: CT76- Validar botão "Ativos selecionados" da tela de confirmação para categoria "Por ativo" tipo Renda fixa
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação da categoria "Por ativo" "Renda fixa"
  When clicar no botão "Ativos selecionados"
  Then a lista de ativos é apresentada e o botão "Alterar ativos" está habilitado

@CT77
Scenario: CT77- Validar botão "Ativos selecionados" da tela de confirmação para categoria "Por ativo" tipo Fundos
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação da categoria "Por ativo" "Fundos"
  When clicar no botão "Ativos selecionados"
  Then a lista de ativos é apresentada e o botão "Alterar ativos" está habilitado

@CT78
Scenario: CT78- Validar recolhimento da lista de ativos pós clique no botão "Ativos selecionados" tela de confirmação categoria "Por ativo" tipo "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação da categoria "Por ativo" "Fundos"
  When clicar no botão "Ativos selecionados"
  Then a lista de ativos é apresentada
  And ao clicar no botão "Ativos selecionados" novamente a lista de ativos deve ser recolhida

@CT79
Scenario: CT79- Validar recolhimento da lista de ativos pós clique no botão "Ativos selecionados" tela de confirmação categoria "Por ativo" tipo "Renda fixa"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação da categoria "Por ativo" "Renda fixa"
  When clicar no botão "Ativos selecionados"
  Then a lista de ativos é apresentada
  And ao clicar no botão "Ativos selecionados" novamente a lista de ativos deve ser recolhida

@CT80
Scenario: CT80- Validar recolhimento da lista de ativos pós clique no botão "Ativos selecionados" tela de confirmação categoria "Por ativo" tipo "Renda fixa" e "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação e incluiu ativos tipo "Renda fixa" e "Fundos"
  When clicar no botão "Ativos selecionados"
  Then a lista de ativos é apresentada
  And ao clicar no botão "Ativos selecionados" novamente a lista de ativos deve ser recolhida

@CT81
Scenario: CT81- Validar lista de ativos "Renda fixa" apresentada
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação para ativos tipo "Renda fixa"
  When clicar no botão "Ativos selecionados"
  Then lista de ativos "Renda fixa" incluídos é apresentada trazendo os campos "Renda fixa", "Código" e "Quantidade"
  And o botão "Alterar" está habilitado
  And a quantidade de ativos incluidos também é apresenada à esquerda do botão "Alterar"

@CT82
Scenario: CT82- Validar lista de ativos "Fundos" apresentada
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação para ativos tipo "Fundos"
  When clicar no botão "Ativos selecionados"
  Then lista de ativos tipo "Fundos" incluídos é apresentada trazendo os campos "Fundos" e "CNPJ"
  And o botão "Alterar" está habilitado
  And a quantidade de ativos incluidos também é apresenada à esquerda do botão "Alterar"

@CT83
Scenario: CT83- Validar lista de ativos "Fundos" e "Renda fixa" apresentada
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Fundos" e "Renda fixa"
  When clicar no botão "Ativos selecionados"
  Then os ativos de tipo "Fundos" incluídos é apresentada trazendo os campos "Fundos" e "CNPJ"
  And os ativos "Renda fixa" incluídos é apresentada trazendo os campos "Renda fixa", "Código" e "Quantidade"
  And o botão "Alterar" está habilitado
  And a quantidade de ativos incluidos também é apresenada à esquerda do botão "Alterar"

@CT84
Scenario: CT84- Validar botão "Alterar" fluxo de entrada
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  Then o usuário é direcionado para a tela de seleção de ativos onde o botão "Consultar" está habilitado

@CT85
Scenario: CT85- Validar botão "Alterar" fluxo de entrada ativo tipo "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  Then o usuário é direcionado para a tela de seleção de ativos onde o botão "Consultar" está habilitado

@CT86
Scenario: CT86- Validar botão "Alterar" fluxo de entrada ativo tipo "Fundos" e "Renda fixa" 
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa" e "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  Then o usuário é direcionado para a tela de seleção de ativos onde o botão "Consultar" está habilitado

@CT87
Scenario: CT87- Validar botão "Alterar" fluxo de entrada ativo tipo "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  Then o popup com título "Investimentos" e sub-titulo "Portabilidade de entrada" os campos fundo e o CNPJ são apresentados
  And os botões "Alterar" e "Remover da solicitação" estão habilitados

@CT88
Scenario: CT88- Validar botão "Alterar" fluxo de entrada ativo tipo "Renda fixa"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  Then o popup com título "Investimentos" e sub-titulo "Portabilidade de entrada" os campos Renda fixa e o Código são apresentados
  And os botões "Alterar" e "Remover da solicitação" estão habilitados

@CT89
Scenario: CT89- Validar botão "Remover da solicitação" fluxo de entrada ativo tipo "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  Then clicar no botão "Remover da solicitação" do popup "Investimentos"
  And o ativo é removido da solicitação

@CT90
Scenario: CT90- Validar botão "Remover da solicitação" fluxo de entrada ativo tipo "Renda fixa"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  Then clicar no botão "Remover da solicitação" do popup "Investimentos"
  And o ativo é removido da solicitação

@CT91
Scenario: CT91- Validar botão "Remover da solicitação" fluxo de entrada ativo tipo "Renda fixa" e "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa" e "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  And visualizar o popup "Investimentos" os campos Renda fixa e o Código são apresentados para "Renda fixa" e os campos fundo e o CNPJ são apresentados para "Fundos"
  Then clicar no botão "Remover da solicitação" do popup "Investimentos"
  And os ativos são removidos da solicitação

@CT92
Scenario: CT92- Validar botão "Alterar" fluxo de entrada ativo tipo "Fundos"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  Then o popup com título "Investimentos" e sub-titulo "Portabilidade de entrada" o nome do fundo e o CNPJ são apresentados
  And clicar no botão "Alterar" o popup "Informe os dados do ativo para a portabilidade" é apresentado

@CT93
Scenario: CT93- Validar botão "Alterar" fluxo de entrada ativo tipo "Renda fixa"
  Given que o usuário está no fluxo "Trazer investimentos"
  And estiver na tela de confirmação com ativos tipo "Renda fixa" e "Fundos"
  When clicar no botão "Ativos selecionados" em seguida clicar no botão "Alterar ativos"
  And o usuário é direcionado para a tela de seleção de ativos clicar no botão "Consultar"
  And visualizar o popup "Investimentos" os campos Renda fixa e o Código são apresentados para "Renda fixa"
  And clicar no botão "Alterar" o popup "Informe os dados do ativo para a portabilidade" é apresentado

@CT94
Scenario: CT94- Validar fluxo "Trazer Investimentos"
  Given que o usuário acessou o menu "Investimentos"
  When estiver no fluxo "Trazer investimentos" nos ativos "Por cartegoria" selecionar todos
  And nos investimento "Por ativo" preencher os dados do ativo "Renda fixa" clicar no botão "Incluir na solicitação"
  And nos investimento "Por ativo" preencher os dados do ativo clicar no botão "Incluir na solicitação"
  And clicar no botão "Continuar" será direcionado para a tela Agora é só confirmar onde o texto "Mais detalhes" é apresentado conforme o protótipo
  And clicar no check-box "Li as informações e autorizo a portabilidade dos investimentos selecionados" habilitar o campoo de incerção do token
  Then preencher o campo token com uma chave válida e clicar no botão "Solicitar portabilidade" o comprovante será direcionado para a tela "7.Conclusão"

@CT95
Scenario: CT95- Validar check-box "Li as informações e autorizo a portabilidade dos investimentos selecionados"
  Given que o usuário acessou o menu "Investimentos"
  And está na tela de confirmação fluxo "Trazer investimentos"
  When clicar no check-box "Li as informações e autorizo a portabilidade dos investimentos selecionados"
  Then o campo para preenchimento do token é habilitado

@CT96
Scenario: CT96- Validar mascaramento do token inserido fluxo "Trazer investimentos" tipo "Renda fixa"
  Given que o usuário acessou o menu "Investimentos"
  And está na tela de confirmação fluxo "Trazer investimentos" por categoria "Renda fixa" e por ativo "Renda fixa"
  When clicar no check-box "Li as informações e autorizo a portabilidade dos investimentos selecionados"
  And preencher o campo token habilitado
  Then o numero inserido deve ser ocultado

@CT97
Scenario: CT97- Validar mascaramento do token inserido fluxo "Trazer investimentos" tipo "Fundos"
  Given que o usuário acessou o menu "Investimentos"
  And está na tela de confirmação fluxo "Trazer investimentos" por categoria "Fundos" e por ativo "Fundos"
  When clicar no check-box "Li as informações e autorizo a portabilidade dos investimentos selecionados"
  And preencher o campo token habilitado
  Then o numero inserido deve ser ocultado

@CT98
Scenario: CT98- Validar recusa para portabilidade de entrada após inserção do token
  Given que o usuário acessou o menu "Investimentos"
  And está na tela de confirmação fluxo "Trazer investimentos" renda fixa
  When clicar no check-box " Li as informações e autorizo a portabilidade dos investimentos selecionados"
  And preencher o campo token habilitado e clicar no botão "Cancelar"
  Then será direcionado para a tela inicial do menu "Investimentos"

@CT99
Scenario: CT99- Validar "Solicitar portabilidade" do fluxo "Trazer investimentos" após inserção do token
  Given que o usuário acessou o menu "Investimentos"
  And está na tela de confirmação fluxo "Trazer investimentos" renda fixa
  When clicar no check-box " Li as informações e autorizo a portabilidade dos investimentos selecionados"
  And preencher o campo token habilitado e clicar no botão "Solicitar portabilidade"
  Then será direcionado para a tela "7.Conclusão"
