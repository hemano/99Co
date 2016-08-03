package steps.applicationSteps;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import cucumber.api.java.en.Then;
import cucumber.api.java.it.Ma;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import pages.ManageTransactionsPage;
import utils.CPMErrorCollector;
import utils.driver.DriverFactory;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by hemantojha on 25/07/16.
 */
public class ManageTransactionsPageSteps extends DriverFactory {

    public CPMErrorCollector collector = new CPMErrorCollector();

    @Then("^User sees search option after entering number followed by \\+ sign like \"(.*?)\" and click on it$")
    public void user_sees_search_option_after_entering_number_followed_by_sign_like_and_click_on_it(String arg1) throws Throwable {
        ManageTransactionsPage manageTransactionsPage = new ManageTransactionsPage();

        manageTransactionsPage.enterValueInSearchTransaction(arg1);

        Optional<WebElement> options = manageTransactionsPage.doesSearchOptionExists();

        if (options.isPresent()) {
            assertThat("Verify Search option contains \" search as mobile \" option",
                    options.get().getText(), Matchers.containsString("search as Mobile"));
                    manageTransactionsPage.clickOnTheOption(options.get());
        } else {
            collector.addError(new Error("Search option is not visible"));
        }

//        String mobileNumber = manageTransactionsPage.getMobileNumberFirstRow();
        String mobileNumber = Splitter.on(" ")
                            .splitToList(manageTransactionsPage.
                                    getMobileNumberFirstRow()).get(1);

        assertThat("Verify the Mobile number of first record",
                arg1, Matchers.containsString(mobileNumber));


        collector.verify();
    }


}
