// ********RoostGPT********
/*
Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview

Test generated for /ping_get for http method type GET in rest-assured framework

RoostTestHash=628b0897a0


*/

// ********RoostGPT********
package io.learnk8s.RoostTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class pingGetTest {

    List<Map<String, String>> envList = new ArrayList<>();


    @Before
    public void setUp() {
      TestdataLoader dataloader = new TestdataLoader();
      envList = dataloader.loadData("src\test\java\io\learnk8s\RoostTest\pingGetTest.csv");
    }

  
    @Test  
    public void pingGet_Test() {
        this.setUp();
        for (Map<String, String> map : envList) {
          RestAssured.baseURI = map.get("BASE_URL");  
  
                Response response = given()
                .when()
                .get("/ping")  
                .then() 
                .extract().response();    
         
                if (response.statusCode() == 200) {
					System.out.println("Description: OK");
				}
  
            }  
    }
}
