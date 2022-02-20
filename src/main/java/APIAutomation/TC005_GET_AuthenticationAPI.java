package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_GET_AuthenticationAPI {

    @Test
    public void GET_Authentication()
    {
        //launch URI
        RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";

        //Authentication pass
        PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
        authscheme.setUserName("ToolsQA");
        authscheme.setPassword("TestPassword");

        RestAssured.authentication=authscheme;

        //Request object

        RequestSpecification httpreq=RestAssured.given();

        //Respose object

        Response response=httpreq.request(Method.GET,"/");

        //Print response

        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //assert response code

        int statuscode=response.getStatusCode();
        System.out.println("Status code is:"+statuscode);
        Assert.assertEquals(statuscode,200);

        //validate status Line

        String statusLine=response.getStatusLine();
        System.out.println("Status Line is:"+statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
}
