package steps.restApiSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by hemantojha on 25/07/16.
 */
public class SoftAsserts {


    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void myTest() {
        collector.checkThat("c", equalTo("a"));
        collector.checkThat(1, equalTo(1));
    }
}
