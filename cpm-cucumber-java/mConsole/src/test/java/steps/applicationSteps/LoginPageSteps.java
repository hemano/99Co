package steps.applicationSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.DriverFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by hemantojha on 21/07/16.
 */
public class LoginPageSteps extends DriverFactory {

//    WebDriver driver = new FirefoxDriver();

    @When("^User logs in to mConsole$")
    public void User_logs_in_to_mConsole() throws Throwable {

        new LoginPage(driver).enterUserName("testadminuser").enterPassword("Abcd@12").andLogin();

        By manageTransactionBy = By.cssSelector("#manageTransactionBtn");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(manageTransactionBy));

        System.out.println("driver.getTitle() = " + driver.getTitle());

    }

}
