package steps;

import br.com.bradesco.cvm210.page.RequestInclusionPage;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class SelecionarAtivosSteps {
    private final RequestInclusionPage requestPage;
    public SelecionarAtivosSteps() throws Exception {
        BradWebDriver driver = TestContext.getWebDriver();
        this.requestPage = new RequestInclusionPage(driver);
    }
    @When("selecionar a opção {string} de inclusão")
    public void selecionar_inclusao(String opcao) { requestPage.selecionarPorAtivo(); }
    @And("preencher os dados de renda fixa {string} codigo {string} quantidade {string}")
    public void preencher_renda_fixa(String nome, String codigo, String qtd) { requestPage.adicionarRendaFixa(nome, codigo, qtd); }
    @And("preencher os dados do fundo {string} cnpj {string}")
    public void preencher_fundo(String nome, String cnpj) { requestPage.adicionarFundo(nome, cnpj); }
    @And("clicar no link Saiba onde encontrar os dados")
    public void clicar_link_saiba() { requestPage.abrirPopupSaibaOndeEncontrarDados(); }
}
