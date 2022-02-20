package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_PUTRecord {

    @Test
    public void PUTRec()
    {
        //launch base URI
        RestAssured.baseURI="https://reqres.in";

        //Request object
        RequestSpecification httpRequest=RestAssured.given();

        //Payload capture
        JSONObject requestparams=new JSONObject();
        requestparams.put("email","JohnXYZ@123");
        requestparams.put("first_name","John");


        httpRequest.header("content-Type","application/json");
        httpRequest.body(requestparams.toJSONString());

        //Response object
        Response response=httpRequest.request(Method.PUT,"/api/users/2");

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
