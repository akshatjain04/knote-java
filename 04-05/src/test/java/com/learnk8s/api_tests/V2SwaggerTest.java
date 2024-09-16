
  package com.learnk8s.api_tests;

  import com.intuit.karate.Results;
  import com.intuit.karate.Runner;
  // import com.intuit.karate.http.HttpServer;
  // import com.intuit.karate.http.ServerConfig;
  import org.junit.jupiter.api.Test;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  class V2SwaggerTest {

      @Test
      void testAll() {
          String apiHostServer = System.getenv().getOrDefault("PET_STORE_URL_BASE", "https://petstore.swagger.io");
String petstoreauthtoken = System.getenv().getOrDefault("PET_STORE_AUTH_TOKEN", "AUTH_TOKEN_2");
          Results results = Runner.path("src/test/java/com/learnk8s/api_tests/V2Swagger")
                  .systemProperty("PET_STORE_URL_BASE", apiHostServer)
.systemProperty("PET_STORE_AUTH_TOKEN", petstoreauthtoken)
                  .reportDir("testReport").parallel(1);
          assertEquals(0, results.getFailCount(), results.getErrorMessages());
      }

  }
