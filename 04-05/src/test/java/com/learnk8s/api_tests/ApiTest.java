
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
            String geographicAddressManagement-v1undefined_URL = System.getenv().getOrDefault(geographicAddressManagement-v1undefined_URL, http://localhost:4010);
String urlbase = System.getenv().getOrDefault("url.base", "dummy url.base");
String authtoken = System.getenv().getOrDefault("AUTH_TOKEN", "abcd");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty(geographicAddressManagement-v1undefined_URL,geographicAddressManagement-v1undefined_URL)
.systemProperty("url.base", urlbase)
.systemProperty("AUTH_TOKEN", authtoken)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
