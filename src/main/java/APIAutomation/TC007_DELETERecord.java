package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC007_DELETERecord {

    @Test
    public void DELETERec()
    {
        //launch base URI
        RestAssured.baseURI="https://reqres.in";

        //Request object
        RequestSpecification httpRequest=RestAssured.given();



        //Response object
        Response response=httpRequest.request(Method.DELETE,"/api/users/1");

        //print response in the console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body:"+responseBody);

        //validate the status code

        int statuscode=response.getStatusCode();
        System.out.println("Status code is"+statuscode);
        Assert.assertEquals(statuscode,201);

        //verify successcode
        String successcode=response.jsonPath().get("SuccessCode");
        System.out.println("Success code is:"+successcode);

    }
}
