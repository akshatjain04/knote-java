
  package com.learnk8s.api_tests;

  import com.intuit.karate.Results;
  import com.intuit.karate.Runner;
  // import com.intuit.karate.http.HttpServer;
  // import com.intuit.karate.http.ServerConfig;
  import org.junit.jupiter.api.Test;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  class ApiTest {

      @Test
      void testAll() {
          String apiHostServer = System.getenv().getOrDefault("API_HOST", "http://petstore.swagger.io/v2");
String authtoken = System.getenv().getOrDefault("AUTH_TOKEN", "abcd");
          Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                  .systemProperty("url.base", apiHostServer)
.systemProperty("AUTH_TOKEN", authtoken)
                  .reportDir("testReport").parallel(1);
          assertEquals(0, results.getFailCount(), results.getErrorMessages());
      }

  }
