package steps.nintyNineRestSteps;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by hemantojha on 23/08/16.
 */
public class ProjectsSD extends base99SD {

    @Given("^User sends a GET request to \"(.*?)\" with (.*) as parameter$")
    public void user_sends_a_GET_request_to_with_as_parameter(String endPoint, String dev_id) throws Throwable {

//        RestUtil.setBaseURI(baseURI);
        endPoint = baseURI+endPoint;

        System.out.println("Request: \n GET-"+ endPoint);

        response = given()
                .param("developer_id", dev_id)
                .get(endPoint);

        json = response.andReturn().asString();
        System.out.println("response = \n" + xml );


        jsonPath = new JsonPath(json);

        xmlPath = new XmlPath(xml).setRoot("root");

    }

    @Then("^Verify following name and status exists$")
    public void verify_following_name_and_status_exists(DataTable expNames) throws Throwable {

//        Map<String, String> expMap = dataTable.asMap(String.class,String.class);

        List<List<String>> actualNames = new ArrayList<List<String>>();

        actualNames.add(Arrays.asList("name","status"));
        actualNames.add(Arrays.asList("sadf","asdf"));
        actualNames.add(Arrays.asList("sadf","asdf"));

        expNames.diff(actualNames);


    }

/*
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
*/

}
