package steps;

import com.bradesco.selenium.sdk.BradWebDriver;

public class TestContext {
    private static BradWebDriver driver;
    public static void setWebDriver(BradWebDriver drv) { driver = drv; }
    public static BradWebDriver getWebDriver() {
        if (driver == null) {
            throw new IllegalStateException("BradWebDriver não inicializado. Configure em Hooks.");
        }
        return driver;
    }
}
