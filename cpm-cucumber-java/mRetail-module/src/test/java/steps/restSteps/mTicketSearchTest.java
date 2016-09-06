package steps.restSteps;


import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import utils.RestUtil;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 12/08/16.
 */
public class mTicketSearchTest {


    public void initializePayment() throws Exception {

        RestUtil.setBaseURI("http://dsb.mesb.test2.cellpointmobile.com:10080");

        File file = new File("src/test/resources/data/mTicketSearchRequestTemplate.xml");
        String requestAsString = FileUtils.readFileToString(file);

        //To Modify xml
//        ModifyXML modifyXML =  new ModifyXML(file);
//        modifyXML.updateAttributeValue("xpath", "attrName", "value");
//        modifyXML.updateNodeValue("xpath","value");


        Response response = given()
                .auth().basic("DSB","hdfy28abdl")
                .header("Content-Type", "text/xml")
                .header("Authorization", "Basic RFNCOmhkZnkyOGFiZGw=")
                .body(requestAsString)
                .post("/mticket/search");

        String xml = response.andReturn().asString();
        System.out.println("response = \t" + xml );


        System.out.println("\n--------------------------------");

        assertThat(response.getStatusCode(), equalTo(200));

        XmlPath xmlPath = new XmlPath(xml).setRoot("root");
        int journeyCount = xmlPath.getList("journey").size();


//        XmlPath xmlPath = new XmlPath(xml).setRoot("root");
////String status = xmlPath.get("journey.@id");
////Node node = xmlPath.getNode("journey");
//
//        xmlPath.getList("journey.criteria.origin")
//        xmlPath.getList("journey.criteria.destination")
//
//        xmlPath.getList("journey.criteria.passengers.passenger")
//
//        xmlPath.getList("journey.products.group.product.amount")


//        XmlPath xmlPath = new XmlPath(xml).setRoot("root");
//
//        xmlPath.get("journey[0].products.group.product[0].@available")
//
//        xmlPath.get("journey[0].products.group.product[0].trips.trip")
//
//        xmlPath.getList("journey[0..1].products.group.product[0].amount")
//
//        xmlPath.getList("journey[0..1].@id")

//String code = xmlPath.getString("status.@code");
//
//System.out.println("code = " + code);
//
//System.out.println("status = " + status);

//        XmlPath xmlPath = new XmlPath(xml).setRoot("root");
//        String status = xmlPath.get("status");
//        String code = xmlPath.getString("status.@code");
//
//        System.out.println("code = " + code);
//
//        System.out.println("status = " + status);
    }
}
