package br.com.bradesco.cvm210.hooks;

import br.com.bradesco.cvm210.page.trazerInvestimentosPage;
import com.bradesco.selenium.exception.BradescoException;
import com.bradesco.selenium.sdk.BradWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static BradWebDriver driver;
    public static trazerInvestimentosPage trazerPage;

    @Before
    public void setUp() throws Exception {
        // Usa a DriverFactory local com fallback para Chrome
        driver = br.com.bradesco.utils.DriverFactory.createDefault();
        trazerPage = new trazerInvestimentosPage(driver);
        driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() throws BradescoException {
        if (driver != null) {
            driver.close();
        }
    }
}