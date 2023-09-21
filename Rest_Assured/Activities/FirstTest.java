package examples;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest {
    String baseURI= "https://petstore.swagger.io/v2/pet";
    @Test
    public void getRequestWithQueryParam(){
        Response response=given().header("Content-Type","application/json").queryParam("status","sold").
                when().get(baseURI+"/findByStatus");
        System.out.println("As String: "+response.getBody().asString());
        System.out.println("As Pretty String"+response.getBody().asPrettyString());
        System.out.println("Headers: "+response.getHeaders().asList());

        String petName= response.then().extract().body().path("[0].name");
        System.out.println(petName);

        Assert.assertEquals(petName,"doggie");

        response.then().statusCode(200);
        response.then().body("[0].name",equalTo("doggie"));
    }
    @Test
    public void getRequestWithPathParam(){
        given().header("Content-Type","application/json").pathParam("petId",1).
                when().get(baseURI+"/{petId}").
                then().statusCode(200).body("name",equalTo("doggie"));
    }

}
