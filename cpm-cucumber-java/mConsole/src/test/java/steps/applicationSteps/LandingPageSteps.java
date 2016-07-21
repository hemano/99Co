package steps.applicationSteps;

import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LandingPage;
import pages.LoginPage;
import utils.DriverFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 21/07/16.
 */
public class LandingPageSteps extends DriverFactory {

    @And("^navigates to Manage Transactions$")
    public void User_logs_in_to_mConsole() throws Throwable {

        new LandingPage(driver).clickOnManageTransaction();

        By transactionTableBy = By.cssSelector("#paginate");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(transactionTableBy));

        String title = driver.getTitle();
        System.out.println("driver.getTitle() = " + driver.getTitle());

        assertThat(title, is(equalTo("blah!!")));

    }



}
