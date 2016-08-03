package steps.applicationSteps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.DescriptionType;
import ru.yandex.qatools.allure.model.SeverityLevel;
import utils.PropertyReader;
import utils.driver.DriverFactory;


/**
 * Created by hemantojha on 21/07/16.
 */
public class LoginPageSteps extends DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(LandingPageSteps.class);


    @When("^User logs in to mConsole$")
    public void User_logs_in_to_mConsole() throws Throwable {

        String username = new PropertyReader().readProperty("userName");
        String pwd = new PropertyReader().readProperty("password");

        new LoginPage()
                .enterUserName(username)
                .enterPassword(pwd)
                .andLogin();

        By manageTransactionBy = By.cssSelector("#manageTransactionBtn");

        System.out.println("driver.getTitle() = " + getDriver().getTitle());

    }

}
