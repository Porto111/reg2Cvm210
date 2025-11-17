package steps;

import br.com.bradesco.cvm210.page.FinalizationPage;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FiinalizarSolicitacaoPortabilidadeSteps {
    private final FinalizationPage finalPage;
    public FiinalizarSolicitacaoPortabilidadeSteps() throws Exception {
        BradWebDriver driver = TestContext.getWebDriver();
        this.finalPage = new FinalizationPage(driver);
    }
    @When("clicar no check-box {string}")
    public void clicar_checkbox(String texto) { finalPage.marcarCheckInformativo(); }
    @And("preencher o campo token {string} e clicar no botão Solicitar portabilidade")
    public void preencher_token(String token) { finalPage.preencherTokenENotar(token); finalPage.solicitarPortabilidade(); }
    @Then("será direcionado para a tela {string}")
    public void tela_conclusao(String tela) { Assert.assertTrue(finalPage.isTelaConclusaoAberta()); }
}
