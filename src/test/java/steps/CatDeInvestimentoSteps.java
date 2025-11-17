package steps;

import br.com.bradesco.cvm210.hooks.Hooks;
import br.com.bradesco.cvm210.page.trazerInvestimentosPage;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CatDeInvestimentoSteps {
    private final trazerInvestimentosPage trazerPage;
    public CatDeInvestimentoSteps() throws Exception {
        BradWebDriver driver = Hooks.driver;
        this.trazerPage = new trazerInvestimentosPage(driver);
    }
    @Given("que o usuário está na tela de seleção de categorias do fluxo trazer investimentos")
    public void usuario_na_tela_categorias() {

    }
    @And("selecionar a opção {string}")
    public void selecionar_opcao(String opcao) {

        trazerPage.selecionarPorCategoria();
    }
    @When("selecionar a categoria {string}")
    public void selecionar_categoria(String categoria) {
        trazerPage.selecionarRendaFixa(); }
    @And("desmarcar a categoria {string}")
    public void desmarcar_categoria(String categoria) {
        trazerPage.selecionarRendaFixa(); }
    @Then("a categoria {string} deve ficar desmarcada")
    public void categoria_desmarcada(String categoria) throws BradescoException {
        Assert.assertEquals(false, trazerPage.isCategoriaSelecionada(categoria));
    }
    @When("selecionar uma das categorias apresentadas em tela \"Fundos\"")
    public void selecionar_categoria_tela() {
        trazerPage.selecionarFundos("Fundos"); }
    @Then("o botão Continuar deve ficar habilitado")
    public void botao_continuar_habilitado() {
        Assert.assertTrue(trazerPage.isContinuarHabilitado("Fundos")); }
    @And("clicar no botão Continuar")
    public void clicar_continuar() {
        trazerPage.clicarContinuar(); }
}
