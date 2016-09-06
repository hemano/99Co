package steps.nintyNineRestSteps;

import com.jayway.restassured.path.json.JsonPath;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 23/08/16.
 */
public class LoginSD extends base99SD {

/*
    @Given("^User sends a GET request to \"(.*?)\" with (.*) as parameter$")
    public void user_sends_a_GET_request_to_with_as_parameter(String endPoint, String dev_id) throws Throwable {

        endPoint = baseURI + endPoint;

        System.out.println("Request: \n GET-" + endPoint);

        response = given()
                .param("developer_id", dev_id)
                .get(endPoint);

        json = response.andReturn().asString();
        System.out.println("response = \n" + xml);


        jsonPath = new JsonPath(json);

        xmlPath = new XmlPath(xml).setRoot("root");

    }
*/

    @Given("^User sends a GET request to \"(.*?)\" with (.*) and (.*)$")
    public void user_sends_a_GET_request_to_with_dev_co_and_dev(String endPoint, String email, String password) throws Throwable {

        endPoint = baseURI + endPoint;
        System.out.println("Request: \n GET-" + endPoint);

        String requests_body="{\n" +
                "    \"email\":     \""+    email      +"\",\n" +
                "    \"password\":  \""+    password    +"\"\n" +
                "}";

        System.out.println(requests_body);

        response = given()
                .header("Content-Type", "application/json")
                .body(requests_body)
                .post(endPoint);

        json = response.andReturn().asString();
        System.out.println("response = \n" + json);


        jsonPath = new JsonPath(json);


    }

    @Then("^The message shoule be (.*)$")
    public void the_message_shoule_be(String msg) throws Throwable {
        assertThat("The message is wrong",jsonPath.get("message"), equalTo(msg));
    }


    @Then("^The role shoule be (.*)")
    public void the_role_shoule_be_Developer(String expRole) throws Throwable {

        String dataJson = jsonPath.from(json).get("data").toString();
        String role = jsonPath.from(dataJson).get("role");
        assertThat("Role", role, equalTo(expRole));

    }



}
