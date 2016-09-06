package steps;

import cucumber.api.java.en.Then;
import steps.nintyNineRestSteps.base99SD;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 23/08/16.
 */
public class commonSD extends base99SD {

    @Then("^The status code should be (\\d+)$")
    public void the_status_code_should_be(int statusCode) throws Throwable {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

}
