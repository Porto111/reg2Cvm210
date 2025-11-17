package br.com.bradesco.cvm210.hooks;

import br.com.bradesco.cvm210.page.trazerInvestimentosPage;
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
        driver.getWrappedDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}