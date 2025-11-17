package br.com.bradesco.cvm210.page;

import br.com.bradesco.cvm210.elements.Cvm210BaseElements;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.sdk.BradWebDriver;
import com.bradesco.selenium.sdk.page_object.PageObject;
import com.bradesco.selenium.sdk.page_object.PageObjectInitializer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.com.bradesco.cvm210.hooks.Hooks.driver;

public class trazerInvestimentosPage implements PageObject {

    private final BradWebDriver webdriver;
    private static WebDriverWait wait;

    public trazerInvestimentosPage(BradWebDriver web) throws Exception {
        this.webdriver = web;
        this.wait = new WebDriverWait(web.getDriver(), Duration.ofSeconds(15));
        PageObjectInitializer.initializeAllWebFields(web, Duration.ofSeconds(10), this);
    }

    // Método genérico para clicar em um botão
    public void clicar(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Método genérico para preencher campos de texto
    public static void preencherCampo(By locator, String texto) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        campo.clear();
        campo.sendKeys(texto);
    }

    // Método genérico para alternar iframe
    public void trocarParaIframe(String nomeIframe) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nomeIframe));
    }

    // Método para voltar ao frame principal
    public void voltarParaFramePrincipal() {
        webdriver.getDriver().switchTo().defaultContent();
    }

    // Ações específicas da tela "Trazer Investimentos"
    public void acessarMenuInvestimentos() {
        clicar(Cvm210BaseElements.menuInvestimento);
    }

    public void acessarSubmenuTrazerInvestimentos() {
        clicar(Cvm210BaseElements.trazerInvestimentos);
    }

    public static void preencherAgencia(String agencia) {
        preencherCampo(Cvm210BaseElements.campoAgencia, agencia);
    }

    public static void preencherContaCorrente(String conta) {
        preencherCampo(Cvm210BaseElements.campoContaCorrente, conta);
    }

    public void clicarBotaoAvancar() {
        clicar(Cvm210BaseElements.btnAvancar);
    }

    public void clicarBotaoCancelar() {
        clicar(Cvm210BaseElements.btnCancelar);
    }

    public void clicarBotaoVoltar() {
        clicar(Cvm210BaseElements.btnVoltar);
    }

    public void buscarInstituicao(String nomeInstituicao) {
        preencherCampo(Cvm210BaseElements.campoBusarInstituicao, nomeInstituicao);
        clicar(Cvm210BaseElements.btnOKBuscarInstituicao);
    }

    public void selecionarPorCategoria() {
        clicar(Cvm210BaseElements.btnSelecionarPorCategoria);
    }

    public void selecionarPorAtivo() {
        clicar(Cvm210BaseElements.btnSelecionarPorAtivo);
    }

    public void selecionarRendaFixa() {
        clicar(Cvm210BaseElements.checkCategoriaRendaFixa);
    }

    public boolean isCategoriaSelecionada(String categoria) throws BradescoException {
        WebElement elemento = driver.findElement(By.xpath("//label[contains(text(), '" + categoria + "')]/preceding-sibling::input")).getBoxedElement();
        return elemento.isSelected();
    }

    public boolean isContinuarHabilitado(String categoria) {
        WebElement elemento = null;
        try {
            elemento = driver.findElement(By.xpath("//label[contains(text(), '" + categoria + "')]/preceding-sibling::input")).getBoxedElement();
        } catch (BradescoException e) {
            throw new RuntimeException(e);
        }
        return elemento.isSelected();
    }

    public void selecionarFundos(String fundos) {
        clicar(Cvm210BaseElements.checkCategoriaFundos);
    }

    public void clicarContinuar() {
        clicar(Cvm210BaseElements.btnContinuar);
    }

    public void incluirSolicitacaoRendaFixa(String nome, String codigo, String quantidade) {
        preencherCampo(Cvm210BaseElements.campoNomeDoAtivoRdFixa, nome);
        preencherCampo(Cvm210BaseElements.campoCodigoAtivoRdFixa, codigo);
        preencherCampo(Cvm210BaseElements.campoQuantidadeRdFixa, quantidade);
        clicar(Cvm210BaseElements.btnIncluirSolicitacaoRdFixa);
    }

    public void incluirSolicitacaoFundos(String nome, String cnpj) {
        preencherCampo(Cvm210BaseElements.campoNomeDoAtivoFundos, nome);
        preencherCampo(Cvm210BaseElements.campoCnpjFundos, cnpj);
        clicar(Cvm210BaseElements.btnIncluirSolicitacaoFundos);
    }

    public void solicitarPortabilidade() {
        clicar(Cvm210BaseElements.btnSolicitarPortabilidade);
    }

    public void enviarPorEmail() {
        clicar(Cvm210BaseElements.btnEnviarPoremail);
    }

    public void imprimir() {
        clicar(Cvm210BaseElements.btnImprimir);
    }

    public void salvarComoArquivo() {
        clicar(Cvm210BaseElements.btnSalvarComo);
    }
}