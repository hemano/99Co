package steps.restApiSteps;


import com.jayway.restassured.path.xml.*;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import utils.RestUtil;
import java.io.*;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by hemantojha on 27/07/16.
 */
public class initiatePaymentSteps {

    private Response res = null; //Response object
    private XmlPath jp = null; //JsonPath object


    @Given("^User initiates the Payment$")
    public void user_initiates_the_Payment() throws Throwable {

        RestUtil.setBaseURI("https://1415-mesb-sit.cellpointmobile.com");

        File file = new File("mConsole/src/test/resources/data/initialize_payment.xml");
        String str = FileUtils.readFileToString(file);

        Response response = given().header("Content-Type", "text/xml")
                .header("Authorization", "Basic MTQxNTpHaGR5NF9haDFH")
                .body(str)
                .post("/mpoint/initialize-payment");

        response
                .then()
                .body("root.client-config.name",
                        Matchers.equalTo("EKMobileiOS"))
                .body("root.client-config.callback-url",
                        Matchers.equalTo("http://mpoint.sit.cellpointmobile.com:80/_test/callback.php"))
                .statusCode(200);


        System.out.println("\n--------------------------------");

        String xml = response.asString();

        XmlPath xmlPath = new XmlPath(xml).setRoot("root");
        String callbackURL = xmlPath.get("client-config.callback-url");

        System.out.println("callbackURL = " + callbackURL);




    }
}