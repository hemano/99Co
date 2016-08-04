package steps.iOSAppSteps;

import cucumber.api.java.en.Given;
import pages.ios.HomePage;
import utils.driver.DriverFactory;

/**
 * Created by hemantojha on 03/08/16.
 */
public class VisaCheckoutSteps extends DriverFactory {


    @Given("^User can book the flight$")
    public void user_can_book_the_flight() throws Throwable {
        new HomePage()
                .selectEnglishLanguage()
                .selectMyProfile();

    }


}
