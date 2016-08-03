package steps.applicationSteps;

import cucumber.api.java.en.And;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LandingPage;
import pages.ManageTransactionsPage;
import ru.yandex.qatools.allure.annotations.Attachment;
import steps.StartingSteps;
import utils.CPMErrorCollector;
import utils.driver.DriverFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 21/07/16.
 */
public class LandingPageSteps extends DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(LandingPageSteps.class);

//    public CPMErrorCollector collector = new CPMErrorCollector();

    @And("^User navigates to Manage Transactions$")
    public void User_logs_in_to_mConsole() throws Throwable {

        logger.info("Moving to Manage Transaction");
        new LandingPage().clickOnManageTransaction();

        assertThat("Verify that User is on Manage Transactions",
                new ManageTransactionsPage().getURL(), containsString("transaction"));


//        System.out.println("Running the collector");
//        logger.debug("Loggin debug level");
//        logger.info("Loggin info level");
//        collector.checkThat("mobile verification", "12312312", containsString("asdf"));
//        collector.checkThat("email verification", "a", containsString("aasdfa"));
//
//        collector.verify();


    }

    @Attachment()
    private byte[] createAttachment() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
