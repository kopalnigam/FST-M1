package liveProject;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.Consumer;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest{
    //Declare headers
    Map<String,String> headers=new HashMap<>();

    //Create the pact
    @Pact(consumer="UserConsumer",provider="UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        //Headers
        headers.put("Content-type","application/json");

        //Request and Response Body
        DslPart requestResponseBody=new PactDslJsonBody().
                numberType("id",123).
                stringType("firstName","Kopal").
                stringType("lastName","Nigam").
                stringType("email","kopal@example.com");

        //Generate contract
        return builder.given("POST request").
                uponReceiving("request to create a user").
                    method("POST").
                    path("/api/users").
                    headers(headers).
                    body(requestResponseBody).
                willRespondWith().
                    status(201).
                    body(requestResponseBody).
                toPact();
    }

    //Test with mock provider
    @Test
    @PactTestFor(providerName="UserProvider",port="8282")
    public void consumerTest(){
        //Request body
        Map<String,Object> reqBody=new HashMap<>();
        reqBody.put("id",123);
        reqBody.put("firstName","Kopal");
        reqBody.put("lastName","Nigam");
        reqBody.put("email","kopal@example.com");

        //Send request
        given().headers(headers).body(reqBody).log().all().
                when().post("http://localhost:8282/api/users").
                then().statusCode(201).body("firstName",equalTo("Kopal")).log().all();
    }
}
