package APIAutomation;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TC001_GET_Demo {

       @Test
       public void GET_TC001()
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
