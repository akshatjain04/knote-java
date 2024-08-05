
  package com.learnk8s.api_tests;

  import com.intuit.karate.Results;
  import com.intuit.karate.Runner;
  // import com.intuit.karate.http.HttpServer;
  // import com.intuit.karate.http.ServerConfig;
  import org.junit.jupiter.api.Test;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  class HttpsRawGithubusercontentComRoostIoCredentialWalletApiMainApiCredentialregisterWallet100Resolved {

      @Test
      void testAll() {
          String apiHostServer = System.getenv().getOrDefault("API_HOST", "https://dev.roost.ai/api/");
          Results results = Runner.path("src/test/java/com/learnk8s/api_tests/HttpsRawGithubusercontentComRoostIoCredentialWalletApiMainApiCredentialregisterWallet100Resolved")
                  .systemProperty("url.base", apiHostServer)
                  .reportDir("testReport").parallel(1);
          assertEquals(0, results.getFailCount(), results.getErrorMessages());
      }

  }
