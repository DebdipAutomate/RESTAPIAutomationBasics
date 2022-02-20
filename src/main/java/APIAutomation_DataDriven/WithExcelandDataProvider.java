package APIAutomation_DataDriven;

import UtilityClass.DataReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class WithExcelandDataProvider {

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
     Object[][] data() throws IOException
    {
        String path=System.getProperty("user.dir")+"/src/main/java/TestData/RestAssuredDDF.xlsx";
        int row=DataReader.getrowcount(path,"Sheet1");
        int cellnum=DataReader.getcellcount(path,"Sheet1",1);

        Object[][] data=new Object[row][cellnum];

        for(int i=1;i<row;i++)
        {
            for(int j=0;j<cellnum;j++)
            {
                data[i-1][j]=DataReader.getCellData(path,"Sheet1",i,j);
            }
        }
        return (data);
    }

}
