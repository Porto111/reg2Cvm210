package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"steps","br.com.bradesco.cvm210.hooks"},
    plugin = {"pretty","html:target/cucumber-html-report"},
    monochrome = true
)
public class TestRunner {
}