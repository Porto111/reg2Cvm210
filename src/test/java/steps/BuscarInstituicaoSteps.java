package steps;

import br.com.bradesco.cvm210.page.InstitutionSearchPage;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BuscarInstituicaoSteps {
    private final InstitutionSearchPage institutionPage;
    public BuscarInstituicaoSteps() throws Exception {
        BradWebDriver driver = TestContext.getWebDriver();
        this.institutionPage = new InstitutionSearchPage(driver);
    }
    @And("buscar a instituição {string}")
    public void buscar_instituicao(String nome) { institutionPage.buscarInstituicaoPorNome(nome); }
    @And("selecionar a opção {string} de instituição")
    public void selecionar_opcao_instituicao(String opcao) { institutionPage.selecionarInstituicaoPorAtivo(); }
    @And("clicar no link Saiba onde encontrar os dados na categoria {string}")
    public void clicar_link_saiba(String categoria) { institutionPage.abrirSaibaOndeEncontrarDados(categoria); }
    @Then("será exibido popup com as informações de onde encontrar os dados")
    public void popup_exibido() { Assert.assertTrue(institutionPage.isPopupSaibaOndeAberto()); }
}
