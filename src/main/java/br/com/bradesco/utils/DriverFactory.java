package br.com.bradesco.utils;

import com.bradesco.selenium.sdk.BradWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Simple wrapper to obtain a BradWebDriver instance.
 * If your project already exposes a DriverFactory (com.bradesco.selenium.sdk.factory.DriverFactory)
 * prefer to use that. This class provides a single point to adjust browser capabilities.
 */
public class DriverFactory {

    public static BradWebDriver createDefault() throws Exception {
        // Tenta usar a factory oficial caso exista no classpath
        try {
            // Muitos projetos possuem com.bradesco.selenium.sdk.factory.DriverFactory.create()
            return DriverFactory.create();
        } catch (Throwable ignored) {
            // Fallback: cria um Chrome local simples
            try {
                WebDriverManager.chromedriver().setup();
                return new BradWebDriver(new ChromeDriver());
            } catch (Throwable t) {
                throw new Exception("Não foi possível criar BradWebDriver automaticamente. Ajuste DriverFactory conforme sua infra.", t);
            }
        }
    }
}