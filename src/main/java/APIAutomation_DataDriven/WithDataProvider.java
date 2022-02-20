package APIAutomation_DataDriven;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WithDataProvider {

    @Test(dataProvider ="DataPump")
    public void PostData(String username,String password)
    {
        //launch base URI
        RestAssured.baseURI="https://demoqa.com/Account/v1";

        //Request object
        RequestSpecification httpRequest=RestAssured.given();

        //Payload capture
        JSONObject requestparams=new JSONObject();
        requestparams.put("userName",username);
        requestparams.put("password",password);


        httpRequest.header("content-Type","application/json");
        httpRequest.body(requestparams.toJSONString());

        //Response object
        Response response=httpRequest.request(Method.POST,"/User");

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

    @DataProvider(name = "DataPump")
     Object[][] data()
    {
        Object[][] data ={{"abc1","pasQs@234"},{"abcd1","pasQsw2@"}};
        return (data);
    }

}
