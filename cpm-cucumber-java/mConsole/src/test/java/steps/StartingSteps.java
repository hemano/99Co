package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import pages.LoginPage;
import utils.DriverFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by hemantojha on 21/07/16.
 */
public class StartingSteps extends DriverFactory {

    @Given("^user is on Login Page$")
    public void User_logs_in_to_mConsole() throws Throwable {
        driver.get("http://mconsole-sit.cellpointmobile.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Before
    public void beforeScenario(){
        driver = new DriverFactory().getDriver();
        System.out.println("This will run before the scenario");
    }

    @After
    public void afterScenario(){
        new DriverFactory().destroyDriver();
        System.out.println("This will run after the scenario");
    }

    @Before("@Signup-DataDriven")
    public void signupSetup() {
        System.out.println("This should run everytime before any of the @Signup-DataDriven tagged scenario is going to run");
    }

    @After("@Signup-DataDriven")
    public void signupTeardown() {
        System.out.println("This should run everytime after any of the @Signup-DataDriven tagged scenario has run");
    }

}
