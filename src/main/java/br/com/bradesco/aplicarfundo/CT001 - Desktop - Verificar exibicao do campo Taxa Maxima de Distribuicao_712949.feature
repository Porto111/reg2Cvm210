#Auto generated Octane revision tag
@TID712949REV0.8.0
Feature: Implementar a apresentação da taxa máxima de distribuição em atendimento a resolução CVM 179

	Scenario: CT001 - Desktop - Verificar exibicao do campo Taxa Maxima de Distribuicao
		Given que o investidor esta na etapa de confirmacao da transacao em um Fundo de Investimento
		When o investidor visualiza os detalhes da transacao
		Then a tela de confirmacao deve exibir o campo Taxa Maxima de Distribuicao com o tooltip ao lado

    
    