package br.com.bradesco.aplicarfundo;

import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.internal.ReporterHelper;
import com.bradesco.selenium.sdk.BradElement;
import com.bradesco.selenium.sdk.BradWebDriver;
import com.bradesco.selenium.sdk.page_object.PageObject;
import com.bradesco.selenium.sdk.page_object.PageObjectElement;
import com.bradesco.selenium.sdk.page_object.PageObjectInitializer;
import com.bradesco.selenium.sdk.page_object.annotations.WebCCSFindBy;
import com.bradesco.tdm.core.cards.SaldoExtratoInforme.ManutencaoSaldos;
import com.bradesco.tdm.core.configuration.model.TDM.Results;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AplicarFundoPage implements PageObject {

    // ----- INÍCIO: VARIAVEIS -----
    private final BradWebDriver webdriver;
    String urlRegIbovespaIndexado =
            "https://www.ib12.bradesco.com.br/shopinvest/gestaoconteudo/arquivos/FIN.INFORMATIVO.30.9.14.pdf";
    String urlRegHiperfundo =
            "https://www.ib12.bradesco.com.br/shopinvest/gestaoconteudo/arquivos/FIN.INFORMATIVO.3.9.7.pdf";
    String tituloIbovespaIndexado = "INVESTIMENTO EM AÇÕES IBOVESPA INDEXADO";
    String tituloHiperfundo = "HIPERFUNDO";
    String nomePagInvestimentos = "Investimentos I";
    String iframeAutenticador = "iframeAutenticador";
    String iframePaginaCentral = "paginaCentral";
    String iframeComprovante = "iframeComprovante";

    By campanhas = By.className("jqmOverlay");
    By menuInvestimentos = By.id("_id74_8:_id76");
    By menuAplicarAgendarFundo = By.linkText("Aplicar / Agendar");
    By modalCampanhaEntretela2 = By.id("_id75");
    By dropdownConta = By.id("cdConta__sexyCombo");
    By contaCorrente = By.cssSelector(".visible:nth-child(1)");
    By dropdownProdutos = By.id("cbProduto");
    By prodSafira2 = By.cssSelector("#cbProduto option[value='117|Bradesco FIC Referenciado DI Safira|/br/pf/fundos_faqsafira.asp']");
    By prodIbovespaIndexado = By.cssSelector("#cbProduto option[value='899|Bradesco FIC de FIA Ibovespa Indexado|/br/pf/fundos_faqrendbec.asp']");
    By prodBrilhante2 = By.cssSelector("#cbProduto option[value='116|Bradesco FIC Referenciado DI Brilhante|/br/pf/fundos_faqbrilhante.asp']");
    By valorField2 = By.id("vlOperacao");
    By btnAplicar = By.id("btnAplicar");
    By chaveDeSeguranca2 = By.name("_id23:_id279");
    By btnConfirmar = By.id("btnAutenticador1");
    By btnOutrasInformacoes = By.xpath("//b[@class='expansivel-fundos-label' and text()='Outras informações']");
    By btnRegulamentoFundo = By.xpath("//a[@class='tabindex' and text()='Regulamento e demais documentos']");
    By prodHiperfundo = By.cssSelector("#cbProduto option[value='495|Bradesco FIC Referenciado Hiperfundo|/br/pf/fundos_faqhiperfundodi.asp']");
    By prodSafira = By.cssSelector("#cbProduto option[value='117|Bradesco FIC Referenciado DI Safira|/br/pf/fundos_faqsafira.asp']");
    By btnVoltarDadosAplicacao = By.cssSelector(".lnkVoltar.tabindex");
    By btnEfetuarNovaOperacao = By.id ("links:linkNovaOperacao");
    By btnVoltarConclusao = By.id("links:linkMenu");
    By tooltipTaxaMaxima = By.id ("caixa-duvida");
    By topoPaginaInvestimentos = By.id ("UIBreadCrumb");

    By msgErroInformeValor = By.id("msgErroCampoOperacao");


    // ----- FIM: VARIAVEIS -----

    // ----- INÍCIO: CONSTRUTOR -----
    public AplicarFundoPage(BradWebDriver web) throws Exception {
        this.webdriver = web;
        PageObjectInitializer.initializeAllWebFields(web, Duration.ofSeconds(10), this);
    }
    // ----- FIM: CONSTRUTOR -----


    // ----- INÍCIO: ELEMENTOS DA PÁGINA -----

    @WebCCSFindBy (css = ".HtmlPanelGroupBradesco > h2")
    PageObjectElement tituloInvestimentos;

//    @WebCCSFindBy (id = "msgErroCampoOperacao")
//    PageObjectElement msgErroInformeValor;

    @WebCCSFindBy (css = ".info:nth-child(8)")
    PageObjectElement produtoConfirmacao;

    @WebCCSFindBy (css = ".info:nth-child(11)")
    PageObjectElement operacao;

    @WebCCSFindBy (css = ".item:nth-child(17)")
    PageObjectElement taxaMaximaDistribuicaoField;

    @WebCCSFindBy (css = "#id-expand-info > p")
    PageObjectElement msgInfoTaxaMaxima;

    @WebCCSFindBy (css = ".cabecalho > .info > h2")
    PageObjectElement operacaoResultado;

    // ----- FIM: ELEMENTOS DA PÁGINA -----



    /* ======== INÍCIO: MÉTODOS UTILITARIOS =========== */

    public boolean clickButton(By locator) {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(10));

        try {
            // espera até que o botão esteja clicável
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            beforeClickOn(locator);
            button.click();
            System.out.println("Clique no botão " + button);
            return true;

        } catch (TimeoutException e) {
            System.out.println("Tempo excedido: O botão não ficou clicável dentro do tempo especificado");
            ReporterHelper.report("Tempo excedido: O botão não ficou clicável dentro do tempo especificado", webdriver.getDriver());
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElement: O botão não foi encontrado na página");
            ReporterHelper.report("O botão não foi encontrado na página", webdriver.getDriver());
        } catch (ElementClickInterceptedException e) {
            System.out.println("ElementClickIntercepted: O clique no botão foi interceptado por outro elemento. " + e);
            ReporterHelper.report("O clique no botão foi interceptado por outro elemento", webdriver.getDriver());
        } catch (Exception e) {
            System.out.println("Erro desconhecido ao clicar no botão: " + e.getMessage());
            ReporterHelper.report("Erro desconhecido ao clicar no botão", webdriver.getDriver());
        }
        return false;
    }

    public boolean isElementPresent(By locator){

        // metodo para checar a presença de um elemento com espera

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(7));

        try {
            // espera até que o elemento esteja presente no DOM
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("Elemento (" + locator + ") está presente");
            return element != null;

        } catch (TimeoutException e) {
            System.out.println("Tempo excedido: O elemento não apareceu dentro do tempo especificado");
        } catch (Exception e) {
        System.out.println("Erro desconhecido ao clicar no botão: " + e.getMessage());
        }
        return false;
    }

    public void beforeClickOn(By element){

        // metodo para checar se existe algum modal aberto (CAMPANHAS e CAMBIO)
        // para que o metodo de click no elemento n seja interceptado por um modal

        try {
            if (isElementPresent(campanhas)){
                WebElement modalCampanha = webdriver.getDriver().findElement(campanhas);
                modalCampanha.click();
                System.out.println("Modal campanhas foi fechado: " + element);
            }

            if (isElementPresent(modalCampanhaEntretela2)){
                WebElement modalEntretelas = webdriver.getDriver().findElement(modalCampanhaEntretela2);
                modalEntretelas.click();
                System.out.println("Modal entretela foi fechado: " + modalEntretelas);
            }
        } catch (NoSuchElementException e){
            // modal nao foi apresentado
        }
    }

    /* ======== FIM: MÉTODOS UTILITARIOS =========== */


    /* ======== INÍCIO: MÉTODOS DE ACESSO A MENU =========== */

    public void acessarMenuInvestimentos() {

        // acessa o menu Investimentos da homepage
        clickButton(menuInvestimentos);

        System.out.println("Acessando o Menu de Investimentos");
        ReporterHelper.report("Acessando o Menu de Investimentos", webdriver.getDriver());
    }

    public void acessarMenuAplicarAgendarFundo() {

        // acessa o iframe da pagina central
        webdriver.getDriver().switchTo().frame(iframePaginaCentral);

        // acessa o menu aplicar / agendar
        clickButton(menuAplicarAgendarFundo);

        System.out.println("Acessando o submenu (Aplicar / Agendar)");
        ReporterHelper.report("Acessando o submenu (Aplicar / Agendar)", webdriver.getDriver());
    }

    /* ======== FIM: MÉTODOS DE ACESSO A MENU =========== */


    /* ======== INÍCIO: MÉTODOS DE INSERÇÃO =========== */

    public void inserirValor (String valor) {

        // insere valor a ser aplicado
        if (isElementPresent(valorField2)){
            WebElement valorOperacaoField = webdriver.getDriver().findElement(valorField2);
            clickButton(valorField2);
            valorOperacaoField.sendKeys(valor);

            System.out.println("Inserindo valor a ser aplicado");
            ReporterHelper.report("Inserindo valor a ser aplicado", webdriver.getDriver());
        }
    }

    public void inserirChaveSeguranca (String chave) {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(15));

        // acessa o iframe do autenticador (OTP, chave de segurança)
        webdriver.getDriver().switchTo().frame(iframeAutenticador);

        // aguarda localizar o campo da chave de segurança
        WebElement chaveSegurancaField = wait.until(ExpectedConditions.visibilityOfElementLocated(chaveDeSeguranca2));

        // scroll até o campo da chave de segurança
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", chaveSegurancaField);

        //digita a chave de segurança
        chaveSegurancaField.sendKeys(chave);

        // volta para o iframe da paginaCentral
        webdriver.getDriver().switchTo().parentFrame();

        System.out.println("Digitando a Chave de Segurança");
        ReporterHelper.report("Digitando a Chave de Segurança", webdriver.getDriver());
    }

    /* ======== FIM: MÉTODOS DE INSERÇÃO =========== */


    /* ======== INÍCIO: MÉTODOS DE CLICK EM BOTÃO =========== */

    public void clicarAplicar() throws InterruptedException {

        // clica no botão aplicar da tela de Dados da Aplicação
        Thread.sleep(2000);
        clickButton(btnAplicar);
        Thread.sleep(2000);

        System.out.println("Clicando no botao Aplicar");
        ReporterHelper.report("Clicando no botao Aplicar", webdriver.getDriver());
    }

    public void clicarConfirmar() {

        // botão aplicar da tela de Confirmação
        clickButton(btnConfirmar);

        System.out.println("Clicando no botao Confirmar");
        ReporterHelper.report("Clicando no botao Confirmar", webdriver.getDriver());
    }

    public void clicarVoltarDadosAplicacao() {

        // clica no botão voltar da tela de Dados da Aplicação
        clickButton(btnVoltarDadosAplicacao);

        System.out.println("Clicando no botao Voltar (Dados da Aplicacao");
        ReporterHelper.report("Clicando no botao Voltar (Dados da Aplicacao)", webdriver.getDriver());
    }

    public void clicarEfetuarNovaOperacao() {

        // botão efetuar nova operação da tela de Conclusão
        clickButton(btnEfetuarNovaOperacao);

        System.out.println("Clicando no botao Efetuar Nova Operacao");
        ReporterHelper.report("Clicando no botao Efetuar Nova Operacao", webdriver.getDriver());
    }

    public void clicarVoltarConclusao() {

        // clica no botão voltar da tela de Conclusão
        clickButton(btnVoltarConclusao);
    }

    /* ======== FIM: MÉTODOS DE CLICK EM BOTÃO =========== */


    /* ======== INÍCIO: MÉTODOS DE RESULTADO DA OPERAÇÃO =========== */

    public String resultadoOperacaoPendente() throws BradescoException {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(7));

        // sobe a página para visualizar comprovante
        WebElement topoPagina = wait.until(ExpectedConditions.presenceOfElementLocated(topoPaginaInvestimentos));
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", topoPagina);
        clickButton(topoPaginaInvestimentos);

        // retorna o título "Operação Pendente"
        System.out.println("Resultado da operacao: " + operacaoResultado.getText());
        return operacaoResultado.getText();
    }

    public String resultadoOperacaoEfetivada() throws BradescoException {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(7));

        // sobe a página para visualizar comprovante
        WebElement topoPagina = wait.until(ExpectedConditions.presenceOfElementLocated(topoPaginaInvestimentos));
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", topoPagina);
        clickButton(topoPaginaInvestimentos);

        // entra no iframe do comprovante
        BradElement comprovante = webdriver.findElementById(iframeComprovante);
        webdriver.enterFrame(comprovante);

        // retorna o título "Operação Pendente"
        System.out.println("Resultado da operacao: " + operacaoResultado.getText());
        return operacaoResultado.getText();
    }

    /* ======== FIM: MÉTODOS DE RESULTADO DA OPERAÇÃO =========== */


    /* ======== INÍCIO: OUTROS MÉTODOS =========== */

    public void visualizarMsgTooltipTaxaMaxima() {

        // coloca o mouse em cima do tooltip para visualizar a mensagem informativa
        Actions actions = new Actions(webdriver.getDriver());
        WebElement element = webdriver.getDriver().findElement(tooltipTaxaMaxima);
        actions.moveToElement(element).perform();
        element.click();

        System.out.println("Visualizando a mensagem do tooltip do campo (Taxa maxima de distribuicao)");
    }

    public void selecionarProduto (By produto) throws InterruptedException {

        // abre o dropdown de produtos de investimento
        clickButton(dropdownProdutos);

        // seleciona o produto
        clickButton(produto);
        Thread.sleep(4000);

        System.out.println("Selecionando o Produto de Investimento");
        ReporterHelper.report("Selecionando o Produto de Investimento", webdriver.getDriver());
    }

    public String getUrlRegulamento() throws BradescoException {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(6));

        // scroll até o expansível de Outras Informações e abre
        WebElement btnOutrasInfos = wait.until(ExpectedConditions.presenceOfElementLocated(btnOutrasInformacoes));
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnOutrasInfos);
        clickButton(btnOutrasInformacoes);

        // scroll até o regulamento e abre
        WebElement btnRegulamento = wait.until(ExpectedConditions.presenceOfElementLocated(btnRegulamentoFundo));
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnRegulamento);
        clickButton(btnRegulamentoFundo);

        // muda para a aba do Regulamento que foi aberta
        webdriver.switchToTab(1);

        // obtém a URL do PDF atualmente aberto na guia
        String urlRegulamento =  webdriver.getUrl();
        System.out.println("URL do Regulamento: " + urlRegulamento);

        // retorna a URL do regulamento
        return urlRegulamento;
    }

    public void inserirSaldo(String agencia, String conta){

        List<Results> results = new ManutencaoSaldos().execute(
                agencia,
                conta,
                "C - CONTA CORRENTE",
                "10000000",
                "P - POSITIVO",
                "0 - SALDO OK",
                "0 - SALDO HABILITADO",
                "0 - SALDO OK",
                "0 - SALDO HABILITADO",
                "NETEPREG-NETEMPRESA | Regulatórios",
                "MANUTENÇÃO SALDO E BLOQUEIOS - TU"
        );
    }

    /* ======== FIM: OUTROS MÉTODOS =========== */







//    public void clicarEfetuarNovaOperacao() throws BradescoException {
//        // botão efetuar nova operação da tela de Conclusão
//        clickIfExists(btnEfetuarNovaOperacao);
//    }
//
//    public void clicarVoltarConclusao() throws BradescoException {
//        // clica no botão voltar da tela de Conclusão
//        clickIfExists(btnVoltarConclusao);
//    }

//    protected void clickIfExists(PageObjectElement element) throws BradescoException {
//        if (element.exists(Duration.ofSeconds(7)) && element.isVisible() && element.isClickable())
//            element.click();
//    }
//
//    protected void typeIfExists(PageObjectElement element, String conteudo) throws BradescoException {
//        if (element.exists(Duration.ofSeconds(7)) && element.isVisible() && element.isClickable()) {
//            element.clear();
//            element.type(conteudo);
//        }
//    }
//
//    public void aguardaCarregamento() throws InterruptedException {
//        Thread.sleep(6000);
//    }

//    public void acessarMenuInvestimentos() throws BradescoException, InterruptedException {
//
//        // fecha o modal campanhas se ele ficar visível
//        aguardaCarregamento();
//        clickIfExists(campanhas);
//
//        clickIfExists(menuInvestimentos);
//        ReporterHelper.report("Menu de Investimentos", webdriver.getDriver());
//    }
//
//    public void acessarMenuAplicarAgendarFundo() throws BradescoException, InterruptedException {
//
//        // acessa o iframe da pagina central
//        BradElement paginaCentral = webdriver.findElementById(iframePaginaCentral);
//        webdriver.enterFrame(paginaCentral);
//
//        // acessa o menu aplicar / agendar
//        clickIfExists(menuAplicarAgendarFundo);
//
//        // fecha o modal de campanhas Entretela se ficar visível
//        clickIfExists(modalCampanhaEntretela);
//
//        //
//        if (servicoIndisponivel.getText().contains("Lembrando")) {
//            ReporterHelper.report("Tela 'Aplicar / Agendar' fundo de investimento",
//                    webdriver.getDriver());
//
//        } else {
//            ReporterHelper.report("Sistema indisponivel", webdriver.getDriver());
//            acessarMenuInvestimentos();
//            this.acessarMenuAplicarAgendarFundo();
//        }
//    }
//
//    public void selecionarContaCorrente() throws BradescoException {
//        // fecha o modal de campanhas Entretela se ficar visível
//        clickIfExists(modalCampanhaEntretela);
//
//        clickIfExists(dropdownConta);
//        clickIfExists(contaCorrente);
//    }
//
//    public void selecionarProduto (PageObjectElement produto) throws BradescoException, InterruptedException {
//        // fecha o modal de campanhas Entretela se ficar visível
//        clickIfExists(modalCampanhaEntretela);
//
//        clickIfExists(dropdownProdutos);
//        webdriver.scrollToElement(produto);
//        produto.click();
//        valorField.exists(Duration.ofSeconds(10));
//    }

//    public void inserirValor(String valor) throws BradescoException {
//    // insere valor a ser aplicado
//    typeIfExists(valorField, valor);
//}
//
//    public void clicarAplicar() throws BradescoException, InterruptedException {
//        // fecha modal de campanhas caso apareça
//        clickIfExists(campanhas);
//
//        // botão aplicar da tela de Dados da Aplicação
//        clickIfExists(btnAplicar);
//
//        // aguarda aparecer o tooltip da próxima tela
//        tooltipTaxaMaxima.exists(Duration.ofSeconds(10));
//    }
//
//    public void inserirChaveSeguranca(String chave) throws BradescoException {
//        // acessa o iframe do autenticador (OTP, chave de segurança)
//        BradElement autenticador = webdriver.findElementById(iframeAutenticador);
//        webdriver.enterFrame(autenticador);
//
//        // digita a chave de segurança na tela de Confirmação
//        chaveDeSeguranca.exists( Duration.ofSeconds(10) );
//        webdriver.scrollToElement(chaveDeSeguranca);
//        chaveDeSeguranca.click();
//        chaveDeSeguranca.type(chave);
//
//        // volta para o iframe da paginaCentral
//        webdriver.getDriver().switchTo().parentFrame();
//    }
//
//    public void clicarConfirmar() throws BradescoException, InterruptedException {
//        // botão aplicar da tela de Confirmação
//        clickIfExists(btnConfirmar);
//
//        // aguarda o elemento da próxima página ficar visível
//        operacaoResultado.exists(Duration.ofSeconds(10));
//    }



    // ----- FIM: MÉTODOS -----

}


