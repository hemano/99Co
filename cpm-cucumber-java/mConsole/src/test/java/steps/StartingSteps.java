package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CommonPage;
import pages.LoginPage;
import utils.PropertyReader;
import utils.RestUtil;
import utils.driver.DriverFactory;
import ru.yandex.qatools.allure.annotations.Attachment;


import java.util.concurrent.TimeUnit;

/**
 * Created by hemantojha on 21/07/16.
 */
public class StartingSteps extends DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(StartingSteps.class);

    @Given("^user is on Login Page$")
    public void User_logs_in_to_mConsole() throws Throwable {
        String appURL = new PropertyReader().readProperty("AppURL");

        logToReport(String.format("User is navigating to {%s}", appURL));
        getDriver().get(appURL);
        getDriver().manage().window().maximize();
    }


    @Before("@web")
    public void beforeScenario() throws Exception {
        instantiateDriverObject();

//        userLogsInTOmConsole();
        System.out.println("This will run default before the scenario");
    }

    @After("@web")
    public void afterScenario(Scenario scenario) throws Exception {
        System.out.println("This will run default after the scenario");

        try {
            new CommonPage().logout();

        } catch (Exception e) {
            logger.error("Unable to Logout of the Application",e);
        }

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + getDriver().getCurrentUrl());
                byte[] screenshot;
                try {
                    screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    screenshot = ((TakesScreenshot) new Augmenter().augment(getDriver())).getScreenshotAs(OutputType.BYTES);
                }
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        closeDriverObjects();
        System.out.println("This will run after the scenario");
    }

    @Before("@api-tests")
    public void apiTestSetUp(){

        System.out.println("Executed before api test");

    }

    @After("@api-tests")
    public void apiAfterTest(){

        System.out.println("Executed after api test");

    }

    @Before("@ios")
    public void iOSTestsSetup() throws Exception {
        logToReport("Executing the iOSTestSetup");
        instantiateDriverObject();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After("@ios")
    public void apiMobileTest() throws Exception {
        logToReport("Closing the iOS Driver session");
        System.out.println("Executed after api test");
        closeDriverObjects();
    }

    private void userLogsInTOmConsole() throws Exception {
        String appURL = new PropertyReader().readProperty("AppURL");
        getDriver().get(appURL);
        getDriver().manage().window().maximize();
        new LoginPage().UserLogsInWithUserNameAndPassword();
    }

}
