package br.com.bradesco.cvm210.elements;

import org.openqa.selenium.By;

public class Cvm210BaseElements {

/*  Aqui estão mapeados os sub-menus que fazem parte da CVM210, agrupados dentro do menu Investimentos do aplicativo web Bradesco net empresas, além,
    dos botões e campos de uso comum entre as features, trazer investimentos, enviar investimentos e status da portabilidade.*/


    String iframePaginaCentral = "paginaCentral";

    public static final By homePage = By.xpath("//a[normalize-space()='Página Inicial']");
    public static final By menuInvestimento = By.xpath("//a[@id='_id69_8:_id71']");

    // Sub-menu Portabilidade de investimentos

    public static final By trazerInvestimentos = By.cssSelector("a[title='Trazer Investimentos']");
    public static final By enviarInvestimentos = By.cssSelector("a[title='Enviar Investimentos']");
    public static final By statusPortabilidade = By.cssSelector("a[title='Status de Portabilidade']");

    // Botões e Check-box

    public static final By btnAvancar = By.xpath("//input[@id='formGrupoEconomico:_id86']");
    public static final By btnVoltar = By.xpath("//a[@id='formMotivo:_id51']");
    public static final By btnCancelar = By.xpath("//a[@id='formAcesso:_id72']");
    public static final By btnOKBuscarInstituicao = By.xpath("//a[@id='lnkPesquisar']");
    public static final By btnSelecionarPorCategoria = By.xpath("//a[@id='formTipoSolicitacao:dtInstituicoes:0:_id49']//div[@class='seleciona-tipoSolicitacao'][normalize-space()='Selecionar']");
    public static final By btnSelecionarPorAtivo = By.xpath("//a[@id='formTipoSolicitacao:dtInstituicoes:1:_id49']//div[@class='seleciona-tipoSolicitacao'][normalize-space()='Selecionar']");
    public static final By checkCategoriaSelecionarTodos = By.cssSelector("input[id='formTipoSolicitacaoCategoria:chackAll']");
    public static final By checkCategoriaRendaFixa = By.cssSelector("input[value='true'][name='formTipoSolicitacaoCategoria:dtCategoria:0:_id56']");
    public static final By checkCategoriaFundos = By.cssSelector("input[value='true'][name='formTipoSolicitacaoCategoria:dtCategoria:1:_id56']");
    public static final By btnContinuar = By.cssSelector("input[id='formTipoSolicitacaoCategoria:continuar']");
    public static final By btnSelectRendaFixa = By.xpath("//a[@id='formTipoSolicitacao:_id64']//div[@class='seleciona-tipoSolicitacao'][normalize-space()='Selecionar']");
    public static final By btnSelectFundos = By.xpath("//a[@id='formTipoSolicitacao:_id70']//div[@class='seleciona-tipoSolicitacao'][normalize-space()='Selecionar']");
    public static final By btnIncluirSolicitacaoRdFixa = By.cssSelector("a[id='formRendaFixaModal:incluirSolicitacao']");
    public static final By btnIncluirSolicitacaoFundos = By.cssSelector("a[id='formFundosModal:incluirSolicitacao']");
    public static final By btnConsultar = By.xpath("//a[@id='formTipoSolicitacao:_id77']");
    public static final By btnAtivosIncluidos = By.xpath("//th[@class='tabelaAtivos-header']");
    public static final By btnAlterarAtivos = By.xpath("//th[@class='tabelaAtivos-header']");
    public static final By checkBoxInformativo = By.xpath("#confirmarPortabilidade");
    public static final By btnSolicitarPortabilidade = By.xpath("//input[@id='botaoConfirmar']");
    public static final By btnEnviarPoremail = By.xpath("//div[@id='_id46']//a[@class='tabindex'][normalize-space()='Enviar por e-mail']");
    public static final By btnImprimir = By.xpath("//div[@id='_id46']//a[@class='tabindex'][normalize-space()='Imprimir']");
    public static final By btnSalvarComo = By.xpath("//div[@id='_id46']//a[@class='tabindex'][normalize-space()='Salvar como arquivo']");


    // Campos para inclusão de dados

    public static final By campoAgencia = By.cssSelector("input[id='formAcesso:txtAgencia']");
    public static final By campoContaCorrente = By.cssSelector("input[id='formAcesso:txtConta']");
    public static final By campoBusarInstituicao = By.cssSelector("#buscarInstituicoesId");
    public static final By campoNomeDoAtivoRdFixa = By.cssSelector("input[id='formRendaFixaModal:nome']");
    public static final By campoCodigoAtivoRdFixa = By.cssSelector("input[id='formRendaFixaModal:codigo']");
    public static final By campoQuantidadeRdFixa = By.cssSelector("input[id='formRendaFixaModal:quantidade']");
    public static final By campoNomeDoAtivoFundos = By.cssSelector("input[id='formFundosModal:nome']");
    public static final By campoCnpjFundos = By.cssSelector("input[id='formFundosModal:cnpj']");


    //Popup consultar Renda Fixa
    public static final By btnRemoverDaSolicitacao = By.cssSelector("a[id='formRendaFixaModal:dtInstituicoes:0:_id67']");
    public static final By btnAlterar = By.cssSelector("a[id='formRendaFixaModal:dtInstituicoes:0:_id65']");
    public static final By btnFechar = By.cssSelector("a[id='formExcluirAtivoModal:_id52']");
    public static final By btnSimRemover = By.cssSelector("a[id='formExcluirAtivoModal:_id53']");

    //Popup consultar Fundos
    public static final By btnRemoverDaSolicitacaoFundos = By.cssSelector("a[id='formRendaFixaModal:dtInstituicoes:1:_id67']");
    public static final By btnAlterarFundos = By.cssSelector("a[id='formRendaFixaModal:dtInstituicoes:1:_id66']");
    public static final By btnFecharFundos = By.cssSelector("a[id='formExcluirAtivoModal:_id52']");
    public static final By btnSimRemoverFundos = By.cssSelector("a[id='formRendaFixaModal:dtInstituicoes:1:_id67']");





}
