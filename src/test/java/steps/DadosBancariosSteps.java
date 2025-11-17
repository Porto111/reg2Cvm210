package steps;

import br.com.bradesco.cvm210.hooks.Hooks;
import br.com.bradesco.cvm210.page.trazerInvestimentosPage;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.sdk.BradWebDriver;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.en.And;

public class DadosBancariosSteps {
    private final trazerInvestimentosPage trazerPage;
    public DadosBancariosSteps() throws Exception {
        BradWebDriver driver = TestContext.getWebDriver();
        this.trazerPage = new trazerInvestimentosPage(driver);
    }
    @And("preencher o campo agencia {string} e conta {string}")
    public void preencher_campos(String agencia, String conta) {
        trazerPage.preencherAgencia(agencia);
        trazerPage.preencherContaCorrente(conta);
    }
    @And("clicar no botão Avançar")
    public void clicar_avancar() { trazerPage.clicarAvancar(); }
    @And("clicar no botão Voltar")
    public void clicar_voltar() { trazerPage.clicarVoltar(); }
    @And("clicar no botão Cancelar")
    public void clicar_cancelar() { trazerPage.clicarCancelar(); }
}
