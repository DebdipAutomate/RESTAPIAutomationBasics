package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_HeaderValidation {

    @Test
    public void HeaderValidation()
    {
        //launch URI
        RestAssured.baseURI="https://demoqa.com/BookStore/v1";

        //Request object

        RequestSpecification httpreq=RestAssured.given();

        //Respose object

        Response response=httpreq.request(Method.GET,"/Books");

        //Print response

        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //Print all header
        //Headers is hashmap structure

        Headers headers=response.headers();
        for(Header header:headers)
        {
            System.out.println("Key:"+header.getName()+"Value:"+header.getValue());
        }

        //validate particular header

        String ContentType=response.header("Content-Type");
        System.out.println("Content Type:"+ContentType);
        Assert.assertEquals(ContentType,"application/json; charset=utf-8");

        //Verifying Another header
        String ETag=response.header("ETag");
        System.out.println("ETag:"+ETag);
        Assert.assertEquals(ETag,"W/\"11a2-8zfX++QwcgaCjSU6F8JP9fUd1tY\"");

    }
}
