package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_GET_ValidateJSONResponse {

    @Test
    public void GET_validateJSONResponse()
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

        //Check whether a particular field is present or not in the JSOn response

        Assert.assertEquals(responseBody.contains("isbn"),true);  //By this we can verify any field is present in the json body or not

        //Extract each node in JSON response

        JsonPath jsonPath=response.jsonPath();

        String isbn=jsonPath.get("isbn");
        String title=jsonPath.get("title");
        String subTitle=jsonPath.get("subTitle");

        System.out.println("isbn"+isbn);
        System.out.println("title"+title);
        System.out.println("subTitle"+subTitle);

        //Assert the value of each node we captured above

        Assert.assertEquals(isbn,"Expected_Value");

    }
}
