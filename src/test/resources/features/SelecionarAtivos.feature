Feature: Request Inclusion.Feature

@CT41
Scenario: CT41- Validar textos explicativos do popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And apresentar o popup com o título "Informe os dados do ativo para portabilidade" e o text-link em vermelho "Saiba onde encontrar os dados"
  Then os identificadores "Nome do ativo-", "Código do ativo-" e "Quantidade-" estão alinhados de forma centralizada em seus respectivos campos
  And abaixo dos campos "Código do ativo" e "Quantidade" deve trazer os textos explicativos de como preencher cada campo
  And no rodapé da janela o texto "Mais detalhes" escrito em negrito trazendo abaixo as mensagens "Ativos da categoria renda fixa só podem ser transferidos por quantidade."
  And "Caso exista algum saldo bloqueado, ele não será enviado na portabilidade. Para saber mais, acesse Perguntas Frequentes."

@CT42
Scenario: CT42- Validar funcionamento do botão "Incluir na solicitação" após preenchimento de todos os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And preencher os campos corretamente "Nome do ativo-", "Código do ativo-" e "Quantidade-"
  Then ao clicar no botão "Incluir na solicitação" o popup é fechado
  And a mensagen "Ativo incluído na solicitação" é apresentada

@CT43
Scenario: CT43- Validar funcionamento do botão "X(fehcar)" após preenchimento de todos os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And preencher os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-"
  Then ao clicar no botão "X(fehcar)" o popup é finalizado

@CT44
Scenario: CT44- Validar funcionamento do botão "X(fehcar)" quando não preencher os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Renda Fixa" do menu "Por Ativo"
  And não preencher os campos "Nome do ativo-", "Código do ativo-" e "Quantidade-"
  Then ao clicar no botão "X(fehcar)" o popup é finalizado

@CT45
Scenario: CT45- Selecionar a categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When visualizar as opções disponíveis da opção "Por Ativo"
  And selecionar a categoria "Fundos"
  Then o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  And os campos "Nome do ativo-" e "CNPJ-" estão habilitados

@CT46
Scenario: CT46- Validar campos para preenchimento no popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-" e "CNPJ-" nomenclaturados conforme o protótipo

@CT47
Scenario: CT47- Validar apresentação dos campos para preenchimento no popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-" e "CNPJ-" estão habilitados e funcionais
  And abaixo do campo "CNPJ-" deve trazer o texto "Apenas números"

@CT48
Scenario: CT48- Validar preenchimento do campo "Nome do ativo" no popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then o campo "Nome do ativo-" está habilitado
  And permitie a inserção de letras e números

@CT49
Scenario: CT49- Validar preenchimento do campo "CNPJ" no popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then o campo "CNPJ-" está habilitado
  And permitie a inserção de até 14 algarismos numéricos

@CT50
Scenario: CT50- Validar o popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And o popup "Informe os dados do ativo para portabilidade" deve ser apresentado
  Then os campos "Nome do ativo-" e "CNPJ-" estão habilitados e funcionais
  And abaixo do campo "CNPJ" deve trazer texto informativo de preenchimento
  And os botões "Incluir na solicitação" e "X(fehcar)" são apresentados na tela

@CT51
Scenario: CT51- Validar textos explicativos do popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And apresentar o popup com o título "Informe os dados do ativo para portabilidade" e o text-link em vermelho "Saiba onde encontrar os dados"
  Then os identificadores "Nome do ativo-" e "CNPJ-" estão alinhados de forma centralizada em seus respectivos campos
  And abaixo do campo "CNPJ" deve trazer texto informativo de preenchimento
  And no rodapé da janela o texto "Mais detalhes" escrito em negrito trazendo abaixo as mensagens "Ativos da categoria fundos só podem ser transferidos com o valor total."
  And "Caso exista algum saldo bloqueado, ele também será enviado na portabilidade. Para saber mais, acesse Perguntas Frequentes."

@CT52
Scenario: CT52- Validar funcionamento do botão "Incluir na solicitação" após preenchimento de todos os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And preencher os campos corretamente "Nome do ativo-" e "CNPJ-"
  Then ao clicar no botão "Incluir na solicitação" habilitado o popup é fechado
  And a mensagen "Ativo incluído na solicitação" é apresentada

@CT53
Scenario: CT53- Validar funcionamento do botão "X(fehcar)" após preenchimento de todos os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And preencher os campos corretamente "Nome do ativo-" e "CNPJ-"
  Then ao clicar no botão "X(fehcar)" o popup é finalizado

@CT54
Scenario: CT54- Validar funcionamento do botão "X(fehcar)" quando não preencher os campos habilitados
  Given que o usuário está na tela de seleção de categorias do fluxo trazer investimentos
  When selecionar a categoria "Fundos" do menu "Por Ativo"
  And não preencher os campos corretamente "Nome do ativo-" e "CNPJ-"
  Then ao clicar no botão "X(fehcar)" o popup é finalizado

@CT55
Scenario: CT55- Usuário tenta enviar investimentos "Renda fixa" com quantidade inferior ao mínimo permitido
  Given que o usuário informou o nome e código do ativo corretamente
  When o usuário tentar enviar uma quantidade inferior a 1
  Then o sistema deve exibir a mensagem "Informe uma quantidade para avançar. Mínima- 1"

@CT56
Scenario: CT56- Usuário tenta enviar investimentos "Fundos" com quantidade inferior ao mínimo permitido
  Given que o usuário informou o nome do ativo corretamente
  When o usuário informar um CNPJ invalido
  Then o sistema deve exibir a mensagem "CNPJ inválido"

@CT57
Scenario: CT57- Usuário tenta enviar investimentos e busca por instituição inválida ou inexistente
  Given que o usuário informou uma instituição inválida ou inexistente no campo de busca
  When o usuário clicar no botão "Ok"
  Then o sistema deve exibir a mensagem "Nenhum resultado encontrado. Tente fazer outra busca."

@CT58
Scenario: CT58- Usuário com acesso não autorizado tenta acessar os da portabilidade de investimentos
  Given que o usuário está logado com um perfil que não é master, n1 ou aprovador
  When o usuário tentar acessar o menu investimentos
  Then os botões "Enviar investimentos", "Trazer investimentos" e "Status portabilidade" não estão habilitados
  And ao posicionar o cursor em qualauer um dos botões o sistema deve exibir a mensagem "Informe ao usuário master para ter acesso a esse serviço."

@CT59
Scenario: CT59- Validar o text-link em vermelho "Saiba onde encontrar os dados" do popup da categoria "Renda fixa" do menu "Por Ativo"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then um novo popup com o título "Saiba onde encontrar os dados de renda fixa"
  And os tópicos "Detalhes da aplicação", "Extrato ou comprovante da aplicação", "informe mensal de rendimentos"
  And "Nota de negociação", "Canais de suporte" são apresentados
  And o botão "X(fehcar)" está disponível e funcional em tela

@CT60
Scenario: CT60- Validar texto do tópico "Detalhes da aplicação" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de renda fixa" deve apresentar o tópico "Detalhes da aplicação"
  And logo abaixo o texto "Costuma estar na área de investimentos do aplicativo ou site."

@CT61
Scenario: CT61- Validar texto do tópico "Extrato ou comprovante da aplicação" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de renda fixa" deve apresentar o tópico "Extrato ou comprovante da aplicação"
  And logo abaixo o texto "Pode estar disponível na àrea de investimentos, em comprovantes ou no histórico de aplicações."

@CT62
Scenario: CT62- Validar texto do tópico "informe mensal de rendimentos" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de renda fixa" deve apresentar o tópico "informe mensal de rendimentos"
  And logo abaixo o texto "Normalmente é enviado por e-mail e também pode ser acessado pelo aplicativo ou site."

@CT63
Scenario: CT63- Validar texto do tópico "Nota de negociação" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de renda fixa" deve apresentar o tópico "Nota de negociação"
  And logo abaixo o texto "É gerada no momento da aplicação e pode estar em comprovantes ou no e-mail de confirmação."

@CT64
Scenario: CT64- Validar texto do tópico "Canais de suporte" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de renda fixa" deve apresentar o tópico "Canais de suporte"
  And logo abaixo o texto "Cada instituição pode disponibilizar os dados em locais diferentes. Se não encontrar, fale com o atendimento."

@CT65
Scenario: CT65- Validar o botão "X(fehcar)" no popup "Saiba onde encontrar os dados de renda fixa" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Renda fixa" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then um novo popup com o título "Saiba onde encontrar os dados de renda fixa"
  And clicar no botão "X(fehcar)" disponível em tela então o popup deve ser fechado

@CT66
Scenario: CT66- Validar o text-link em vermelho "Saiba onde encontrar os dados" do popup da categoria "Fundos" do menu "Por Ativo"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then um novo popup com o título "Saiba onde encontrar os dados de fundos"
  And os tópicos "Detalhes da aplicação", "Extrato ou comprovante da aplicação", "informe mensal de rendimentos"
  And "Canais de suporte" são apresentados
  And o botão "X(fehcar)" está disponível e funcional em tela

@CT67
Scenario: CT67- Validar textos do tópico "Detalhes da aplicação" no popup "Saiba onde encontrar os dados de fundos" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de fundos" deve apresentar o tópico "Detalhes da aplicação"
  And logo abaixo o texto "Costuma estar na área de investimentos do aplicativo ou site."

@CT68
Scenario: CT68- Validar texto do tópico "Extrato ou comprovante da aplicação" no popup "Saiba onde encontrar os dados de fundos" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de fundos" deve apresentar o tópico "Extrato ou comprovante da aplicação"
  And logo abaixo o texto "Pode estar disponível na àrea de investimentos, em comprovantes ou no histórico de aplicações."

@CT69
Scenario: CT69- Validar texto do tópico "informe mensal de rendimentos" no popup "Saiba onde encontrar os dados de fundos" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de fundos" deve apresentar o tópico "informe mensal de rendimentos"
  And logo abaixo o texto "Normalmente é enviado por e-mail e também pode ser acessado pelo aplicativo ou site."

@CT70
Scenario: CT70- Validar texto do tópico "Canais de suporte" no popup "Saiba onde encontrar os dados de fundos" do text-link "Saiba onde encontrar os dados"
  Given que o usuário está no popup para inclusão de ativos da categoria "Fundos" do menu "Por Ativo"
  When clicar no text-link em vermelho "Saiba onde encontrar os dados"
  Then na janela "Saiba onde encontrar os dados de fundos" deve apresentar o tópico "Canais de suporte"
  And logo abaixo o texto "Cada instituição pode disponibilizar os dados em locais diferentes. Se não encontrar, fale com o atendimento."
