package br.com.bradesco.utils;

import com.bradesco.selenium.sdk.BradWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<BradWebDriver> driverThread = new ThreadLocal<>();
    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (Exception ignored) {}
    }

    public static BradWebDriver getDriver() throws Exception {
        if (driverThread.get() == null) {
            driverThread.set(createDriver());
        }
        return driverThread.get();
    }

    public static void quitDriver() {
        try {
            if (driverThread.get() != null) {
                driverThread.get().close();
            }
        } catch (Exception ignored) {}
        driverThread.remove();
    }

    /**
     * CRIA O DRIVER COMPLETO
     */
    private static BradWebDriver createDriver() throws Exception {

        // 1. Primeiro tenta usar a DriverFactory oficial do SDK
        try {
            Class<?> factory = Class.forName("com.bradesco.selenium.sdk.factory.DriverFactory");
            return (BradWebDriver) factory.getMethod("create").invoke(null);
        } catch (Throwable ignored) {}

        // 2. Carrega configurações
        String browser = System.getProperty("browser", props.getProperty("browser", "chrome")).toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", props.getProperty("headless", "false")));
        String remoteUrl = System.getProperty("remote", props.getProperty("remote", ""));
        String platform = System.getProperty("platform", props.getProperty("platform", "desktop")).toLowerCase();

        WebDriver driver;

        // =============================
        // 🔥 3. Suporte a MOBILE (APPIUM)
        // =============================
        if (platform.equals("android") || platform.equals("ios")) {
            DesiredCapabilities caps = new DesiredCapabilities();

            if (platform.equals("android")) {
                caps.setCapability("platformName", "Android");
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("deviceName", System.getProperty("deviceName", "Android Emulator"));
                caps.setCapability("appPackage", props.getProperty("appPackage"));
                caps.setCapability("appActivity", props.getProperty("appActivity"));
            }
            else {
                caps.setCapability("platformName", "iOS");
                caps.setCapability("automationName", "XCUITest");
                caps.setCapability("deviceName", System.getProperty("deviceName", "iPhone Simulator"));
                caps.setCapability("bundleId", props.getProperty("bundleId"));
            }

            URL appiumServer = new URL(System.getProperty("appiumUrl",
                    props.getProperty("appiumUrl", "http://localhost:4723/wd/hub")));

            return new BradWebDriver(new RemoteWebDriver(appiumServer, caps));
        }

        // =============================
        // 🔥 4. Selenium GRID / Selenoid
        // =============================
       /* if (!remoteUrl.isEmpty()) {
            DesiredCapabilities caps = new DesiredCapabilities();

            switch (browser) {
                case "firefox": caps.setBrowserName("firefox"); break;
                case "edge": caps.setBrowserName("edge"); break;
                default: caps.setBrowserName("chrome"); break;
            }

            caps.setCapability("enableVNC", true);
            caps.setCapability("enableVideo", false);

            return new BradWebDriver(new RemoteWebDriver(new URL(remoteUrl), caps));
        } */

        // =============================
        // 🔥 5. MODO DESKTOP (Local) — padrão
        // =============================
        switch (browser) {

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                ChromeOptions opt = new ChromeOptions();

                if (headless) {
                    opt.addArguments("--headless=new");
                    opt.addArguments("--disable-gpu");
                    opt.addArguments("--window-size=1920,1080");
                }

                opt.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(opt);
                break;
        }

        return new BradWebDriver(driver);
    }

    public static BradWebDriver createDefault() {
        return createDefault();
    }
}