#Auto generated Octane revision tag
@TID712946REV0.7.0
Feature: Implementar a apresentação da taxa máxima de distribuição em atendimento a resolução CVM 179

	Scenario: CT002 - Desktop - Verificar mensagem informativa sobre a Taxa Maxima de Distribuicao
		Given que o investidor esta na etapa de confirmacao da transacao em um Fundo de Investimento
           When o investidor visualiza as informacoes relacionadas a taxa de distribuicao
           Then a "<mensagem_informativa>" sobre a taxa maxima de distribuicao deve ser exibida ao passar o mouse em cima do tooltip
        | mensagem_informativa|
        |Essa taxa é um percentual do patrimônio líquido do fundo que representa quanto os distribuidores recebem de remuneração|

    
    