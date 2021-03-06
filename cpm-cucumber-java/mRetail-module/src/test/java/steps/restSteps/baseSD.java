package steps.restSteps;

import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import utils.PropertyReader;

/**
 * Created by hemantojha on 23/08/16.
 */
public class baseSD {

    protected String baseURI = new PropertyReader().readProperty("baseURI");
    protected String userName = new PropertyReader().readProperty("userName");
    protected String password = new PropertyReader().readProperty("password");
    protected String authenticationKey = new PropertyReader().readProperty("authenticationKey");

    protected String requestTemplatePath;

    protected String xmlRequest;
    protected String xml;
    protected XmlPath xmlPath;

    protected static Response response;

}
