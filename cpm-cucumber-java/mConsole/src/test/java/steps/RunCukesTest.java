package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

/**
 * Created by hemantojha on 21/07/16.
 */
//    @CucumberOptions(features = "classpath:features",
//    glue = {"steps"},
//    tags = {"@sanity"}, plugin = {"pretty", "html:target/cucumber-html-report"})
//    public class RunCukesTest extends AbstractTestNGCucumberTests {
//
//    }
// TODO: 21/07/16  mvn test -Dcucumber.options="--tags @sanity"

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {})
public class RunCukesTest {
}

