package br.com.bradesco.login;

import br.com.bradesco.chavedeacesso.ChaveDeAcessoPage;
import com.bradesco.core.sdk.BradMobileDriver;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.internal.ReporterHelper;
import com.bradesco.selenium.sdk.BradWebDriver;
import com.bradesco.selenium.sdk.page_object.PageObject;
import com.bradesco.selenium.sdk.page_object.PageObjectInitializer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.TestAbortedException;

import java.time.Duration;

public class LoginPage implements PageObject {

    // ----- INÍCIO: VARIAVEIS -----

    BradWebDriver webdriver;
    BradMobileDriver mobiledriver;
    private final ChaveDeAcessoPage chaveDeAcessoPage;
    private static final int MAX_RETRY_ATTEMPTS = 5;
    String iframeAutenticador = "iframeAutenticador";
    String loginURL = "https://www.ne12.bradesconetempresa.tu.teste.internet/ibpjlogin/login.jsf";


    // ----- FIM: VARIAVEIS -----


    // ----- INÍCIO: CONSTRUTOR -----

    public LoginPage(BradWebDriver web, BradMobileDriver mobiledriver) throws Exception {
        this.webdriver = web;
        PageObjectInitializer.initializeAllWebFields(web, Duration.ofSeconds(15), this);

        this.mobiledriver = mobiledriver;
        chaveDeAcessoPage = new ChaveDeAcessoPage(mobiledriver);
    }

    public LoginPage(BradWebDriver web) throws Exception {
        this.webdriver = web;
        PageObjectInitializer.initializeAllWebFields(web, Duration.ofSeconds(15), this);
        chaveDeAcessoPage = null;
    }

    // ----- FIM: CONSTRUTOR -----

    // ----- INICIO: ELEMENTOS DA PÁGINA -----
    By chaveDeSegurancaMaster = By.name("_id23:_id137");
    By usuarioLocator = By.id("identificationForm:txtUsuario");
    By senhaLocator = By.id("identificationForm:txtSenha");
    By btnLoginLocator = By.cssSelector("input[id='identificationForm:botaoAvancar']");
    By chaveSegurancaLocator = By.cssSelector("#chaveseguranca input[type='password']");
    By btnAcessarLocator = By.id("botaoAcessar");
    By btnFecharModalLocator = By.id("btnFecharModal");
    By msgSistemaIndisponivelLocator = By.id("titleErroUsua");
    By ctrlIndisponivel = By.id("mensagemLogoff");

    // ----- FIM: ELEMENTOS DA PÁGINA -----


    // ----- INÍCIO: MÉTODOS -----

    public void login(String username, String password, String perfil) throws BradescoException {
        login(username, password, perfil ,0);
    }

    /*  Metodo de login construído com Selenium para fazer uso do método .until (WebDriverWait)
     * Este método pede como parâmetro USUARIO, SENHA, PERFIL e NUMERO DE TENTATIVAS
     * PERFIL pode ser "Master" ou "N2"
     * Os fluxos do login são diferentes para cada perfil
     * NUMERO DE TENTATIVAS (attempts) é uma variável aqui da page*/

    private boolean login(String username, String password, String perfil, int attempts) throws BradescoException {
        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(40));

        if (attempts >= MAX_RETRY_ATTEMPTS) {
            System.out.println("Sistema indisponível. Falha ao logar após " + MAX_RETRY_ATTEMPTS + " tentativas.");
            ReporterHelper.report("Sistema indisponível. Falha ao logar após " + MAX_RETRY_ATTEMPTS + " tentativas.", webdriver.getDriver());
            throw new TestAbortedException("Sistema indisponível. Falha ao logar após " + MAX_RETRY_ATTEMPTS + " tentativas.");
        }

        try {
            // navega para a pagina de login
            webdriver.navigate(loginURL);

            // procura pelos campos usuario, senha e botao avancar
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usuarioLocator));
            System.out.println("Encontrou usuario");
            WebElement passwordField = webdriver.getDriver().findElement(senhaLocator);
            System.out.println("Encontrou senha");
            WebElement loginButton = webdriver.getDriver().findElement(btnLoginLocator);
            System.out.println("Encontrou botao");

            // insere usuario e senha
            usernameField.clear();
            usernameField.sendKeys(username);
            passwordField.clear();
            passwordField.sendKeys(password);
            ReporterHelper.report("Usuário (" + username + ") e senha (" + password + ") preenchidos", webdriver.getDriver());

            // click no botao avancar
            // mantenha os thread.sleep
            Thread.sleep(3000);
            loginButton.click();
            Thread.sleep(3000);
            System.out.println("Clicou no botao");

            // checa modal de sessao encerrada
            if (handleSessionExpiredModal()) {
                System.out.println("Modal de Sessão Encerrada encontrado. Tentativas restantes: " + (MAX_RETRY_ATTEMPTS - attempts - 1) + " Autenticando novamente... ");
                return login(username, password, perfil, attempts + 1);
            }

            // checa se apareceu o erro de CTRL nao encontrada
            if (handleCtrlExpired()){
                System.out.println("Não foi possível encontrar a CTRL da sessão. Tentativas restantes: " + (MAX_RETRY_ATTEMPTS - attempts - 1) + " Autenticando novamente... ");
                return login(username, password, perfil, attempts + 1);
            }

            // checa mensagem de erro de sistema indisponivel
            if (handleSystemUnavailableError()) {
                System.out.println("Sistema indisponível. Tentativas restantes: " + (MAX_RETRY_ATTEMPTS - attempts - 1) + " Autenticando novamente... ");
                return login(username, password, perfil, attempts + 1);
            }

            // fluxos diferentes dependendo do perfil de usuario
            // serve para usuarios N1 tambem
            if (perfil.equalsIgnoreCase("Master")) {

                System.out.println("Entrou Master");

                // aguarda pelo campo da chave de segurança e insere a chave
                WebElement securityKeyField = wait.until(ExpectedConditions.visibilityOfElementLocated(chaveSegurancaLocator));
                System.out.println("Tela da chave de segurança");
                ReporterHelper.report("Tela da chave de segurança", webdriver.getDriver());
                securityKeyField.clear();
                securityKeyField.sendKeys(chaveDeAcessoPage.obterChaveDeAcesso());
                System.out.println("Chave de seguranca preenchida");
                ReporterHelper.report("Chave de segurança preenchida", webdriver.getDriver());
                WebElement submitKeyButton = webdriver.getDriver().findElement(btnAcessarLocator);
                submitKeyButton.click();

                // aguarda pela homepage após inserir a chave de segurança
                wait.until(ExpectedConditions.urlContains("/ibpjtelainicial/paginaInicial"));
                Thread.sleep(3000);

                // checa se apareceu o erro de CTRL nao encontrada
                if (handleCtrlExpired()){
                    System.out.println("Não foi possível encontrar a CTRL da sessão. Autenticando novamente...");
                    return login(username, password, perfil, attempts + 1);
                }

            } else if (perfil.equalsIgnoreCase("N2")) {

                System.out.println("Entrou N2");

                // aguarda pela homepage
                wait.until(ExpectedConditions.urlContains("/ibpjtelainicial/paginaInicial"));
                Thread.sleep(3000);
                // checa se apareceu o erro de CTRL nao encontrada
                if (handleCtrlExpired()){
                    System.out.println("Não foi possível encontrar a CTRL da sessão. Autenticando novamente...");
                    return login(username, password, perfil, attempts + 1);
                }
                System.out.println("Link da pagina: " + webdriver.getDriver().getCurrentUrl());
            }

            System.out.println("Homepage logada");
            ReporterHelper.report("Homepage logada", webdriver.getDriver());
            return true;

        } catch (TimeoutException e) {
            System.out.println("Tempo limite excedido durante a tentativa de login " + (attempts + 1) + ". Autenticando...");
            ReporterHelper.report("Tempo limite excedido durante a tentativa de login " + (attempts + 1) + ". Autenticando...", webdriver.getDriver());
            return login(username, password, perfil, attempts + 1);

        } catch (NoSuchElementException e) {
            System.out.println("Um elemento esperado não foi encontrado durante a tentativa de login " + (attempts + 1) + ". Autenticando...");
            ReporterHelper.report("Um elemento esperado não foi encontrado durante a tentativa de login " + (attempts + 1) + ". Autenticando...", webdriver.getDriver());
            return login(username, password, perfil, attempts + 1);

        } catch (BradescoException e) {
            System.out.println("Tempo limite excedido ao tentar acessar a URL: " + loginURL);
            ReporterHelper.report("Tempo limite excedido ao tentar acessar a URL: " + loginURL, webdriver.getDriver());
            return login(username, password, perfil, attempts + 1);

        } catch (com.bradesco.core.exception.BradescoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean handleSessionExpiredModal() {
        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(btnFecharModalLocator));
            System.out.println("Modal de Sessao Encerrada encontrado.");
            ReporterHelper.report("Modal de Sessao Encerrada encontrado.", webdriver.getDriver());
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            // modal nao esta presente, sem acao necessaria
            return false;
        }
    }

    private boolean handleCtrlExpired() {
        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ctrlIndisponivel));
            System.out.println("Não foi possível encontrar a CTRL da sessão.");
            ReporterHelper.report("Não foi possível encontrar a CTRL da sessão.", webdriver.getDriver());
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            // sem acao necessaria
            return false;
        }
    }

    private boolean handleSystemUnavailableError() {
        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(msgSistemaIndisponivelLocator));
            System.out.println("Sistema indisponível.");
            ReporterHelper.report("Sistema indisponível.", webdriver.getDriver());
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            // Mensagem de  erro nao esta presente, sem acao necessaria
            return false;
        }
    }


    /*  Metodo para inserir a Chave de Segurança da tela de Confirmação
     * da Jornada de "Aplicar / Agendar" fundos de investimento */

    public void confirmarChaveDeSeguranca() throws com.bradesco.core.exception.BradescoException {

        WebDriverWait wait = new WebDriverWait(webdriver.getDriver(), Duration.ofSeconds(10));

        // acessa o iframe do autenticador (OTP, chave de segurança)
        webdriver.getDriver().switchTo().frame(iframeAutenticador);

        // aguarda localizar o campo da chave de segurança
        WebElement chaveSegurancaField = wait.until(ExpectedConditions.visibilityOfElementLocated(chaveDeSegurancaMaster));

        // scroll até o campo da chave de segurança
        ((JavascriptExecutor) webdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", chaveSegurancaField);

        // digita a chave de segurança na tela de Confirmação
        chaveSegurancaField.clear();
        chaveSegurancaField.sendKeys(chaveDeAcessoPage.getOtp());

        // volta para o iframe da paginaCentral
        webdriver.getDriver().switchTo().parentFrame();
    }

    // ----- FIM: MÉTODOS -----

}
