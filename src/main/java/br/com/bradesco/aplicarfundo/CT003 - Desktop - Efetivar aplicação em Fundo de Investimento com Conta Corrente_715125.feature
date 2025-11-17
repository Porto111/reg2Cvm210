#Auto generated Octane revision tag
@TID715125REV0.10.0
Feature: Implementar a apresentação da taxa máxima de distribuição em atendimento a resolução CVM 179

	Scenario: CT003 - Desktop - Efetivar aplicação em Fundo de Investimento com Conta Corrente
		Given que o usuario esta logado e na tela de Aplicar em Fundo de Investimento
        When o usuario escolhe a Conta Corrente para debitar os recursos
       	 And seleciona o produto
         And informa o valor desejado a ser aplicado
         And clica em aplicar
         And confirma a transacao na tela de Confirmacao
        Then o sistema exibe um comprovante com o resumo da aplicacao bem sucedida
           
           
        

    
    