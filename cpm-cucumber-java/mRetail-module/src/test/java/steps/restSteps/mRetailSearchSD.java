package steps.restSteps;

import com.google.common.base.Splitter;
import com.jayway.restassured.path.xml.XmlPath;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.hamcrest.Matchers;
import utils.ModifyXML;
import utils.PropertyReader;
import utils.RestUtil;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hemantojha on 23/08/16.
 */
public class mRetailSearchSD extends baseSD {


    @Then("^The Journey count should be (\\d+)$")
    public void the_Journey_count_should_be(int count) throws Throwable {
        int journeyCount = xmlPath.getList("journey").size();
        assertThat(journeyCount, equalTo(count));
    }

    @Then("^The trip should have vehicle-id \"(.*?)\" and name \"(.*?)\" and not Cancelled$")
    public void the_trip_should_have_vehicle_id_and_name(String expVehicleNo, String expVehicleName) throws Throwable {

        String vehicleNo = xmlPath.get("journey[0].trips.trip.transportation.vehicle");
        String trainName = xmlPath.get("journey[0].trips.trip.transportation.info.name[0]");
        String cancelledStatus = xmlPath.get("journey[0].trips.trip.traffic-info.@cancelled");

        assertThat(vehicleNo, equalTo(expVehicleNo));
        assertThat(trainName, equalTo(expVehicleName));
        assertThat(cancelledStatus, is(String.valueOf(false)));
    }


    @Given("^The vehicle name language code should have codes \"(.*?)\"$")
    public void the_vehicle_name_language_code_should_have_codes(String codes) throws Throwable {

        List<String> expCodesList = Splitter.on(",").splitToList(codes);
        ArrayList<String> codeList = xmlPath.get("journey[0].trips.trip.transportation.info.name.@language");

        assertThat(codeList, Matchers.hasItems(expCodesList.toArray(new String[expCodesList.size()])));
    }



    @Given("^User sends a POST request to \"(.*?)\" with \"(.*?)\" date and following details$")
    public void user_sends_a_POST_request_to_with_date_and_following_details(String endPoint, String dateType, DataTable searchTable) throws Throwable {

        Map<String, String> searchMap = searchTable.asMap(String.class, String.class);

        requestTemplatePath = new PropertyReader().readProperty("searchRequestTemplatePath");
        RestUtil.setBaseURI(baseURI);
        File file = new File(requestTemplatePath);

        ModifyXML modifyXML = new ModifyXML(file);
        modifyXML.updateNodeValue("root/search/origin", searchMap.get("origin"));
        modifyXML.updateNodeValue("root/search/destination", searchMap.get("destination"));

        if(dateType.equals("current")){
            modifyXML.updateNodeValue("root/search/time", LocalDateTime.now().toString());
        }

        xmlRequest = modifyXML.getXML();
        System.out.println(xmlRequest);

        response = given()
                .auth().basic(userName, password)
                .header("Content-Type", "text/xml")
                .body(xmlRequest)
                .post(endPoint);

        xml = response.andReturn().asString();
        System.out.println("response = \t" + xml );

        xmlPath = new XmlPath(xml).setRoot("root");
    }

    @Then("^The Journey count should be \"(.*?)\" (\\d+)$")
    public void the_Journey_count_should_be(String compareTo, int count) throws Throwable {
        int journeyCount = xmlPath.getList("journey").size();
        if(compareTo.equals(">")){
            assertThat(journeyCount, greaterThan(count));
        }

    }

    @Then("^The Journey criteria should match the request details$")
    public void the_Journey_criteria_should_match_the_request_details(DataTable searchTable) throws Throwable {

        Map<String, String> expValuesMap = searchTable.asMap(String.class, String.class);

        String expOrigin = xmlPath.get("journey[1].criteria.origin").toString();
        String expDest = xmlPath.get("journey[1].criteria.destination").toString();

        assertThat(expOrigin, equalTo(expValuesMap.get("origin")));
        assertThat(expDest, equalTo(expValuesMap.get("destination")));



    }

}
