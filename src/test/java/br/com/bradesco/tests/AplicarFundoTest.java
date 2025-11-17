package br.com.bradesco.tests;

import com.bradesco.selenium.exception.BradescoEngineInitializationException;
import com.bradesco.selenium.util.GlobalDriver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.hpe.alm.octane.OctaneGherkinFormatter:gherkin-results/Web_OctaneGherkinResults.xml",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "junit:webResults.xml"},
        monochrome = true,
        glue = "br.com.bradesco.aplicarfundo",
        features = "./src/main/java/br/com/bradesco/aplicarfundo/"
)

public class AplicarFundoTest {

    private static long millis;

    @BeforeClass
    public static void setup() throws BradescoEngineInitializationException {
        millis = System.currentTimeMillis();
        System.setProperty("timestamp", String.valueOf(millis));

        GlobalDriver.setJavaPackage("aplicarfundo"); // web
        com.bradesco.core.util.GlobalDriver.setJavaPackage("aplicarfundo"); // mobile
    }

    @AfterClass
    public static void fechar() throws IOException {
        GlobalDriver.closeWeb();
    }
}
