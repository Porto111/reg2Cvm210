package br.com.bradesco.aplicarfundo;

import br.com.bradesco.login.LoginPage;
import br.com.bradesco.utils.PDFReader;
import com.bradesco.core.sdk.BradMobileDriver;
import com.bradesco.selenium.configuration.BrowserOptions;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.factory.WebEngine;
import com.bradesco.selenium.internal.ReporterHelper;
import com.bradesco.selenium.sdk.BradWebDriver;
import com.bradesco.selenium.util.GlobalDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import static com.bradesco.selenium.util.dataTable.DataTableHelper.getDt_;

public class AplicarFundoStep extends RemoteWebDriver {

    // ----- INÍCIO: VARIÁVEIS -----
    private BradWebDriver webdriver;
    private BradMobileDriver mobiledriver;
    private AplicarFundoPage aplicarFundoPage;
    private LoginPage loginPage;
    private PDFReader pdfReader;

    // ----- FIM: VARIÁVEIS -----


    // ----- INÍCIO: CONSTRUTOR -----
    public AplicarFundoStep() throws Exception {

    }
    // ----- FIM: CONSTRUTOR -----


    // ----- INÍCIO: BEFORE -----
    @Before
    public void iniciar(Scenario scenario) throws Exception {

        // carregando dados das Massas de Teste
        GlobalDriver.carregarDados(scenario); // web
        com.bradesco.core.util.GlobalDriver.carregarDados(scenario); // mobile

        // Descomentar o GlobalDriver dependendo do navegador em que for executar os testes
        GlobalDriver.getWeb(WebEngine.SELENIUM_WEBDRIVER, BrowserOptions.forChrome(new ChromeOptions()));
        //GlobalDriver.getWeb(WebEngine.SELENIUM_WEBDRIVER, BrowserOptions.forFirefox(new FirefoxOptions()));

        // inicializando os drivers web e mobile
        // mobile serve para buscar a chave de seguranca no Mobile Center
        webdriver = GlobalDriver.get(); // iniciando sessão web
        mobiledriver = com.bradesco.core.util.GlobalDriver.get(); // iniciando sessão mobile
        mobiledriver.launchApp();

        aplicarFundoPage = new AplicarFundoPage(webdriver);
        loginPage = new LoginPage(webdriver, mobiledriver);
        pdfReader = new PDFReader();

        webdriver.getDriver().manage().deleteAllCookies();
    }
    // ----- FIM: BEFORE -----


    // ----- INÍCIO: AFTER -----
    @After
    public void fechar() throws IOException, com.bradesco.core.exception.BradescoException {

        GlobalDriver.encerrarMassa(); // web
        com.bradesco.core.util.GlobalDriver.encerrarMassa(); // mobile
        GlobalDriver.closeWeb();
        com.bradesco.core.util.GlobalDriver.close();
    }
    // ----- FIM: AFTER -----


    // ----- INÍCIO: STEPS -----

    /* =============== INÍCIO ============
     CT001 - Desktop - Verificar exibicao do campo Taxa Maxima de Distribuicao_712949 */

    @Given("que o investidor esta na etapa de confirmacao da transacao em um Fundo de Investimento")
    public void que_o_investidor_esta_na_etapa_de_confirmacao_da_transacao_em_um_Fundo_de_Investimento() throws BradescoException, InterruptedException {

        // acessa a tela de confirmação
        aplicarFundoPage.inserirSaldo("1217", "1582"); // insere saldo na conta corrente

        loginPage.login(
                getDt_().getStringOf("USUARIO"),
                getDt_().getStringOf("SENHA"),
                getDt_().getStringOf("PERFIL"));

            aplicarFundoPage.acessarMenuInvestimentos();
            aplicarFundoPage.acessarMenuAplicarAgendarFundo();
            aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodSafira2); // Seleciona o produto Bradesco FIC Referenciado DI Safira
            aplicarFundoPage.inserirValor("100,00");
            aplicarFundoPage.clicarAplicar();

            ReporterHelper.report(
                    "Confirmando a aplicação em Fundo de Investimento",
                    webdriver.getDriver());
    }

    @io.cucumber.java.en.When("o investidor visualiza os detalhes da transacao")
    public void o_investidor_visualiza_os_detalhes_da_transacao() throws BradescoException {
        ReporterHelper.report("Acessando a tela de Confirmação", webdriver.getDriver());
    }

    @Then("a tela de confirmacao deve exibir o campo Taxa Maxima de Distribuicao com o tooltip ao lado")
    public void aTelaDeConfirmacaoDeveExibirOCampoTaxaMaximaDeDistribuicaoComOTooltipAoLado() throws BradescoException {

        ReporterHelper.report("Campo (Taxa Maxima de Distribuicao)", webdriver.getDriver());

        // Compara se a Taxa de Distribuição está presente na tela
        Assert.assertTrue(
                "O campo (Taxa Máxima de Distribuição) não está visível",
                aplicarFundoPage.taxaMaximaDistribuicaoField.isVisible());

        // Avalia se o tooltip está visível
        Assert.assertTrue(
                "Tooltip do campo (Taxa Máxima de Distribuição) não está visível",
                aplicarFundoPage.isElementPresent(aplicarFundoPage.tooltipTaxaMaxima));
    }

    /* =============== FIM ============
     CT001 - Desktop - Verificar exibicao do campo Taxa Maxima de Distribuicao_712949 */


    /* =============== INÍCIO ============
     CT002 - Desktop - Verificar mensagem informativa sobre a Taxa Maxima de Distribuicao_712946 */

    @io.cucumber.java.en.When("o investidor visualiza as informacoes relacionadas a taxa de distribuicao")
    public void o_investidor_visualiza_as_informacoes_relacionadas_a_taxa_de_distribuicao() {

        ReporterHelper.report(
                "Tooltip do campo (Taxa Maxima de Distribuiçãcao)",
                webdriver.getDriver());

        // Avalia se o tooltip está visível
        Assert.assertTrue(
                "Tooltip do campo (Taxa Maxima de Distribuiçãcao) não esta visivel",
                aplicarFundoPage.isElementPresent(aplicarFundoPage.tooltipTaxaMaxima));
    }

    @Then("a {string} sobre a taxa maxima de distribuicao deve ser exibida ao passar o mouse em cima do tooltip")
    public void a_sobre_a_taxa_maxima_de_distribuicao_deve_ser_exibida_ao_passar_o_mouse_em_cima(String mensagem, io.cucumber.datatable.DataTable dataTable) throws BradescoException, InterruptedException {

        String msgInformativa = "Essa taxa é um percentual do patrimônio líquido do fundo que representa quanto os distribuidores recebem de remuneração";

        // exibe a mensagem informativa do tooltip do campo Taxa Máxima de Distribuição da tela de Confirmação
        aplicarFundoPage.visualizarMsgTooltipTaxaMaxima();

        // Valida se a mensagem do tooltip está visível ao colocar o mouse em cima
        Assert.assertTrue(
                "Mensagem informativa do tooltip da (Taxa Maxima de Distribuicao) não está visivel",
                aplicarFundoPage.msgInfoTaxaMaxima.isVisible());

        // Valida se a mensagem está correta
        Assert.assertEquals(
                "Mensagem informativa do tooltip esta incorreta",
                aplicarFundoPage.msgInfoTaxaMaxima.getText(),
                msgInformativa);

        ReporterHelper.report(
                "Mensagem informativa do tooltip do campo (Taxa Maxima de Distribuicao)",
                webdriver.getDriver());
    }

    /* =============== FIM ============
     CT002 - Desktop - Verificar mensagem informativa sobre a Taxa Maxima de Distribuicao_712946 */


    /* =============== INÍCIO ============
     CT003 - Desktop - Efetivar aplicação em Fundo de Investimento com Conta Corrente_715125 */
    @Given ("que o usuario esta logado e na tela de Aplicar em Fundo de Investimento")
    public void que_o_usuario_esta_logado_e_na_tela_de_Aplicar_em_Fundo_de_Investimento() throws BradescoException, InterruptedException, com.bradesco.core.exception.BradescoException {

        // acessa a tela de Confirmação de Aplicar/Agendar fundo de Investimento
        loginPage.login(
                getDt_().getStringOf("USUARIO"),
                getDt_().getStringOf("SENHA"),
                getDt_().getStringOf("PERFIL"));

            aplicarFundoPage.acessarMenuInvestimentos();
            aplicarFundoPage.acessarMenuAplicarAgendarFundo();
    }

    @io.cucumber.java.en.When ("o usuario escolhe a Conta Corrente para debitar os recursos")
    public void o_usuario_escolhe_a_Conta_Corrente_para_debitar_os_recursos() {
        ReporterHelper.report("Selecionando a Conta corrente", webdriver.getDriver());
    }

    @io.cucumber.java.en.When ("seleciona o produto")
    public void seleciona_o_produto() throws InterruptedException {
        aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodBrilhante2);
    }

    @io.cucumber.java.en.When ("informa o valor desejado a ser aplicado")
    public void informa_o_valor_desejado_a_ser_aplicado() {
        aplicarFundoPage.inserirValor("100,00");
    }

    @io.cucumber.java.en.When ("clica em aplicar")
    public void clica_em_aplicar() throws InterruptedException {
        aplicarFundoPage.clicarAplicar();
    }

    @io.cucumber.java.en.When ("confirma a transacao na tela de Confirmacao")
    public void confirma_a_transacao_na_tela_de_Confirmacao() throws com.bradesco.core.exception.BradescoException, BradescoException, InterruptedException {
        loginPage.confirmarChaveDeSeguranca();
        aplicarFundoPage.clicarConfirmar();
    }

    @Then("o sistema exibe um comprovante com o resumo da aplicacao bem sucedida")
    public void o_sistema_exibe_um_comprovante_com_o_resumo_da_aplicacao_bem_sucedida() throws BradescoException {

        // compara o texto da tela de Conclusão
        Assert.assertEquals(
                "Erro na operação",
                aplicarFundoPage.resultadoOperacaoEfetivada(),
                "Comprovante de Transação Bancária");

        ReporterHelper.report("Comprovante de Transação Bancária", webdriver.getDriver());
    }
    /* =============== FIM ============
     CT003 - Desktop - Efetivar aplicação em Fundo de Investimento com Conta Corrente_715125 */


    /* =============== INÍCIO ============
     CT005 - Desktop - Gerar transação pendente ao efetuar Aplicação em Fundo com perfil de usuário N2_717092 */

    @Given("que o usuario com perfil N{int} seleciona o Produto de Investimento a ser aplicado")
    public void que_o_usuario_com_perfil_N_seleciona_o_Produto_de_Investimento_a_ser_aplicado(Integer int1) throws BradescoException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {

        // acessa a tela de Confirmação de Aplicar/Agendar fundo de Investimento
        loginPage.login(
                getDt_().getStringOf("USUARIO"),
                getDt_().getStringOf("SENHA"),
                getDt_().getStringOf("PERFIL"));

            aplicarFundoPage.acessarMenuInvestimentos();
            aplicarFundoPage.acessarMenuAplicarAgendarFundo();
            aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodSafira2); // Seleciona o produto Bradesco FIC Referenciado DI Safira
            aplicarFundoPage.inserirValor("100,00");
            aplicarFundoPage.clicarAplicar();
    }

    @io.cucumber.java.en.When("o usuário confirma a aplicação")
    public void o_usuário_confirma_a_aplicação() throws BradescoException, InterruptedException {

        String produto = "Bradesco FIC Referenciado DI Safira";
        String operacao = "Aplicação";

        // Validando o produto selecionado na tela de Confirmação
        Assert.assertEquals("Produto esta incorreto", aplicarFundoPage.produtoConfirmacao.getText(), produto);

        // Validando a operação na tela de Confirmação
        Assert.assertEquals("Operacao esta incorreta", aplicarFundoPage.operacao.getText(), operacao);

        // insere a chave de segurança do usuário N2
        aplicarFundoPage.inserirChaveSeguranca(getDt_().getStringOf("SENHA"));
        aplicarFundoPage.clicarConfirmar();
    }

    @Then("o sistema deve gerar transacao pendente")
    public void o_sistema_deve_gerar_transacao_pendente() throws BradescoException, InterruptedException {

        // compara o texto da tela de Conclusão
        Assert.assertEquals(
                "Resultado da Operacao nao esta Pendente",
                aplicarFundoPage.resultadoOperacaoPendente(),
                "Operação Pendente");

        ReporterHelper.report("Comprovante de Transacao Pendente", webdriver.getDriver());
    }

    /* =============== FIM ============
     CT005 - Desktop - Gerar transação pendente ao efetuar Aplicação em Fundo com perfil de usuário N2_717092 */


    /* =============== INÍCIO ============
     CT008 - Desktop -  Visualizar o Regulamento do Fundo de Investimento Ibovespa Indexado_717213 */

    @Given("o usuario esta na tela de Aplicar em Fundo de Investimento")
    public void o_usuario_esta_na_tela_de_Aplicar_em_Fundo_de_Investimento() throws Exception {

        // acessa a tela de Dados da Aplicação em Aplicar/Agendar fundo de Investimento
        loginPage.login(
                getDt_().getStringOf("USUARIO"),
                getDt_().getStringOf("SENHA"),
                getDt_().getStringOf("PERFIL"));

            aplicarFundoPage.acessarMenuInvestimentos();
            aplicarFundoPage.acessarMenuAplicarAgendarFundo();
    }

    @io.cucumber.java.en.When("o usuario seleciona o Fundo Ibovespa Indexado")
    public void o_usuario_seleciona_o_Fundo_Ibovespa_Indexado() throws BradescoException, InterruptedException {

        aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodIbovespaIndexado);

        ReporterHelper.report("Selecionando o produto Ibovespa Indexado", webdriver.getDriver());
    }

    @Then("o Regulamento do Fundo Ibovespa Indexado deve ser exibido")
    public void o_Regulamento_do_Fundo_Ibovespa_Indexado_deve_ser_exibido() throws BradescoException, IOException, InterruptedException {

        String urlPdf = aplicarFundoPage.getUrlRegulamento();
        System.out.println("URL do Regulamento: " + urlPdf);

        // passa a URL do pdf do regulamento e armazena o texto do pdf inteiro na variável 'pdfTextRegulamento'
        String pdfTextRegulamento = pdfReader.getTextoPDF(urlPdf);
        System.out.println("Conteúdo do PDF: " + pdfTextRegulamento);

        // Compara a URL obtida pelo webdriver.getUrl() com a String da URL do produto
        Assert.assertEquals("URL do Regulamento está incorreta", urlPdf, aplicarFundoPage.urlRegIbovespaIndexado);

        // Procura pelo título do Regulamento no pdf armazenado na variavel 'pdfTextRegulamento'
        Assert.assertTrue("Titulo do Regulamento está incorreto", pdfTextRegulamento.contains(aplicarFundoPage.tituloIbovespaIndexado));

        // Tira screenshot da tela
        ReporterHelper.report("Exibindo o Regulamento do Fundo Ibovespa Indexado", webdriver.getDriver());
    }

    /* =============== FIM ============
     CT008 - Desktop -  Visualizar o Regulamento do Fundo de Investimento Ibovespa Indexado_717213 */

    /* =============== INÍCIO ============
     CT009 - Desktop - Visualizar o Regulamento do Fundo de Investimento Hiperfundo_717221 */

    @io.cucumber.java.en.When("o usuario seleciona o Hiperfundo")
    public void o_usuario_seleciona_o_Hiperfundo() throws InterruptedException {

        aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodHiperfundo);

        ReporterHelper.report("Selecionando o produto Hiperfundo", webdriver.getDriver());
    }

    @Then("o Regulamento do Hiperfundo deve ser exibido")
    public void o_Regulamento_do_Hiperfundo_deve_ser_exibido() throws BradescoException, IOException {

        String urlPdf = aplicarFundoPage.getUrlRegulamento();
        System.out.println("URL do Regulamento: " + urlPdf);

        // passa a URL do pdf do regulamento e armazena o texto do pdf inteiro na variável 'pdfTextRegulamento'
        String pdfTextRegulamento = pdfReader.getTextoPDF(urlPdf); //pdfReader.getTextoPDF(pdfExemplo);
        System.out.println("Conteudo do PDF: " + pdfTextRegulamento);

        // Compara a URL obtida pelo webdriver.getUrl() com a String da URL do produto
        Assert.assertEquals("URL do Regulamento esta incorreta", urlPdf, aplicarFundoPage.urlRegHiperfundo);

        // Procura pelo título do Regulamento no pdf armazenado na variavel 'pdfTextRegulamento'
        Assert.assertTrue("Titulo do Regulamento esta incorreto", pdfTextRegulamento.contains(aplicarFundoPage.tituloHiperfundo));

        // Tira screenshot da tela
        ReporterHelper.report("Exibindo regulamento do Fundo Hiperfundo", webdriver.getDriver());
    }

    /* =============== FIM ============
     CT009 - Desktop - Visualizar o Regulamento do Fundo de Investimento Hiperfundo_717221 */

    /* =============== INÍCIO ============
     CT012 - Desktop - Aplicar em Fundo de Investimento com valor abaixo do limite_715285 */

    @io.cucumber.java.en.When("o usuario insere um valor abaixo do limite minimo de aplicacao")
    public void o_usuario_insere_um_valor_abaixo_do_limite_minimo_de_aplicacao() throws BradescoException, InterruptedException {

        // seleciona o produto de investimento Hiperfundo
        aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodHiperfundo);

        ReporterHelper.report("Inserindo valor abaixo do limite minimo de aplicacao", webdriver.getDriver());
    }

    @Then("o sistema deve exibir uma mensagem informando que o valor minimo de aplicacao deve ser atingido")
    public void o_sistema_deve_exibir_uma_mensagem_informando_que_o_valor_minimo_de_aplicacao_deve_ser_atingido() throws BradescoException, InterruptedException {

        // coloca valor e verifica se a mensagem de erro aparece
        aplicarFundoPage.inserirValor("0,00");
        aplicarFundoPage.clicarAplicar();

        // valida se a msg de erro esta visível
        Assert.assertTrue(
                "Valida mensagem de erro valor abaixo do limite",
                aplicarFundoPage.isElementPresent(aplicarFundoPage.msgErroInformeValor));

        ReporterHelper.report("Mensagem de erro por valor abaixo do limite", webdriver.getDriver());
    }

    /* =============== FIM ============
     CT012 - Desktop - Aplicar em Fundo de Investimento com valor abaixo do limite_715285 */


    /* =============== INÍCIO ============
     CT013 - Desktop - Voltar ao menu de investimentos pelo botão Voltar da tela Dados da Aplicacao_715290 */

    @io.cucumber.java.en.When("o usuario clica no botao Voltar")
    public void o_usuario_clica_no_botao_Voltar() throws BradescoException, InterruptedException {

        // clica no botão voltar da tela de Dados da Aplicação
        aplicarFundoPage.clicarVoltarDadosAplicacao();
    }

    @Then("o sistema deve redirecionar o usuario para o menu de Investimentos")
    public void o_sistema_deve_redirecionar_o_usuario_para_o_menu_de_Investimentos() throws BradescoException {

        // extrai o texto do elemento e compara com o titulo "Investimentos" para garantir que está no menu de Investimentos
        Assert.assertEquals(
                "Voltar ao Menu de Investimentos",
                aplicarFundoPage.tituloInvestimentos.getText(),
                aplicarFundoPage.nomePagInvestimentos);

        ReporterHelper.report("Voltando para o Menu de Investimentos", webdriver.getDriver());
    }
    /* =============== FIM ============
     CT013 - Desktop - Voltar ao menu de investimentos pelo botão Voltar da tela Dados da Aplicacao_715290 */


    /* =============== INÍCIO ============
     CT014 - Desktop - Efetuar nova transação através do botão da tela do Comprovante_717227 */

    @Given("o usuario esta na tela de Comprovante de uma transacao anterior")
    public void o_usuario_esta_na_tela_de_Comprovante_de_uma_transacao_anterior() throws BradescoException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {

        // fluxo da jornada até acessar a tela de Conclusão da transação
        loginPage.login(
                getDt_().getStringOf("USUARIO"),
                getDt_().getStringOf("SENHA"),
                getDt_().getStringOf("PERFIL"));

            aplicarFundoPage.acessarMenuInvestimentos();
            aplicarFundoPage.acessarMenuAplicarAgendarFundo();
            aplicarFundoPage.selecionarProduto(aplicarFundoPage.prodSafira); // Seleciona o produto Bradesco FIC Referenciado DI Safira
            aplicarFundoPage.inserirValor("100,00");
            aplicarFundoPage.clicarAplicar();
            aplicarFundoPage.inserirChaveSeguranca(getDt_().getStringOf("SENHA")); // pega a senha do arquivo aplicarfundo.xls
            aplicarFundoPage.clicarConfirmar();

            // compara o texto da tela de Conclusão
            Assert.assertEquals(
                    "Resultado da Operacao nao esta Pendente",
                    aplicarFundoPage.resultadoOperacaoPendente(),
                    "Operação Pendente");

            ReporterHelper.report("Comprovante de Conclusao da Transacao", webdriver.getDriver());
    }

    @io.cucumber.java.en.When("o usuario clica no botao para efetuar uma nova transacao")
    public void o_usuario_clica_no_botao_para_efetuar_uma_nova_transacao() throws BradescoException, InterruptedException {

        // clica no botão Efetuar Nova Operação na tela de Conclusão
        aplicarFundoPage.clicarEfetuarNovaOperacao();
    }

    @Then("o sistema deve redirecionar o usuário para a tela de Aplicar em Fundo de Investimento")
    public void o_sistema_deve_redirecionar_o_usuário_para_a_tela_de_Aplicar_em_Fundo_de_Investimento() throws BradescoException {

        // valida se está na página de Dados da Aplicação pelo dropdown de Produtos
        Assert.assertTrue("Combo de Produtos nao esta visivel",
                aplicarFundoPage.isElementPresent(aplicarFundoPage.dropdownProdutos));

        ReporterHelper.report("Voltando para a tela de Dados da Aplicacao", webdriver.getDriver());
    }

    /* =============== INÍCIO ============
     CT014 - Desktop - Efetuar nova transação através do botão da tela do Comprovante_717227 */

    /* =============== INÍCIO ============
     CT016 - Desktop - Voltar para o Menu Investimentos_717230 */

    @io.cucumber.java.en.When("o usuario seleciona a opcao para voltar ao Menu Investimentos")
    public void o_usuario_seleciona_a_opcao_para_voltar_ao_Menu_Investimentos() throws BradescoException, InterruptedException {

        // botão voltar da tela de Conclusão
        aplicarFundoPage.clicarVoltarConclusao();
    }

    @Then("o sistema deve redirecionar o usuario para o Menu Investimentos")
    public void o_sistema_deve_redirecionar_o_usuario_para_o_Menu_Investimentos() throws BradescoException {

        // valida se o título da página de investimentos está visível
        Assert.assertTrue("Titulo da pagina de Investimentos nao esta visivel",
                aplicarFundoPage.tituloInvestimentos.isVisible());

        // valida se o título da página está correto
        Assert.assertEquals("Titulo da pagina de Investimentos não esta correto",
                aplicarFundoPage.tituloInvestimentos.getText(),
                aplicarFundoPage.nomePagInvestimentos);

        ReporterHelper.report("Redirecionando para o Menu Investimentos", webdriver.getDriver());
    }

    /* =============== FIM ============
     CT016 - Desktop - Voltar para o Menu Investimentos_717230 */

    // ----- FIM: STEPS -----

}
