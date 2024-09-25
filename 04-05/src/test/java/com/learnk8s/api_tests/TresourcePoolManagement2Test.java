
  package com.learnk8s.api_tests;

  import com.intuit.karate.Results;
  import com.intuit.karate.Runner;
  // import com.intuit.karate.http.HttpServer;
  // import com.intuit.karate.http.ServerConfig;
  import org.junit.jupiter.api.Test;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  class TresourcePoolManagement2Test {

      @Test
      void testAll() {
          String apiHostServer = System.getenv().getOrDefault("ALIAS_3_URL_BASE", "https://github.com/Yashupadhyaya");
String alias3authtoken = System.getenv().getOrDefault("ALIAS_3_AUTH_TOKEN", "TOKEN_3");
          Results results = Runner.path("src/test/java/com/learnk8s/api_tests/TresourcePoolManagement2")
                  .systemProperty("ALIAS_3_URL_BASE", apiHostServer)
.systemProperty("ALIAS_3_AUTH_TOKEN", alias3authtoken)
                  .reportDir("testReport").parallel(1);
          assertEquals(0, results.getFailCount(), results.getErrorMessages());
      }

  }
