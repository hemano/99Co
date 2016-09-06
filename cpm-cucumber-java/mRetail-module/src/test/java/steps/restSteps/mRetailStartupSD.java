package steps.restSteps;

import com.jayway.restassured.path.xml.XmlPath;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.hamcrest.Matchers;
import utils.ModifyXML;
import utils.PropertyReader;
import utils.RestUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 23/08/16.
 */
public class mRetailStartupSD extends baseSD {

    @Given("^User sends a POST request to /mticket/startup with following details$")
    public void user_sends_a_POST_request_to_mticket_startup_with_following_details(DataTable startupTable) throws Throwable {
        Map<String, String> startupMap = startupTable.asMap(String.class, String.class);

        requestTemplatePath = new PropertyReader().readProperty("startUpRequestTemplatePath");

        RestUtil.setBaseURI(baseURI);
        File file = new File(requestTemplatePath);


        ModifyXML modifyXML = new ModifyXML(file);
        modifyXML.updateAttributeValue("root/startup/client-info", "language", startupMap.get("language") );
        modifyXML.updateAttributeValue("root/startup/client-info", "version", startupMap.get("version") );
        modifyXML.updateAttributeValue("root/startup/client-info", "platform", startupMap.get("platform") );
        modifyXML.updateNodeValue("root/startup/client-info/mobile", startupMap.get("mobile"));

        xmlRequest = modifyXML.getXML();
        System.out.println(xmlRequest);


        response = given()
                .auth().basic(userName, password)
                .header("Content-Type", "text/xml")
                .header("Authorization", authenticationKey)
                .body(xmlRequest)
                .post("/mticket/startup");

        xml = response.andReturn().asString();
//        System.out.println("response = \t" + xml );

        xmlPath = new XmlPath(xml).setRoot("root");
    }


    @Then("^The Products count shoule be (\\d+)$")
    public void the_Products_count_shoule_be(int expCount) throws Throwable {
        int productsCount = xmlPath.getList("products.product").size();
        assertThat(productsCount, equalTo(Integer.valueOf(expCount)));
    }

    @Then("^The startup should return following products$")
    public void the_startup_should_return_following_products(DataTable productsTable) throws Throwable {


        List<String> expProductsList = productsTable.asList(String.class);
        List<String> productList = xmlPath.getList("products.product.name");

        assertThat(productList, Matchers.hasItems(expProductsList.toArray(new String[expProductsList.size()])));

    }
}
