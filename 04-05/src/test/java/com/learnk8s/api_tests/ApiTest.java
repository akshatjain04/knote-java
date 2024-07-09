
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
            String appointment_v2_url_URL = System.getenv().getOrDefault("appointment-v2_URL", "http://localhost:4010");
String communicationmanagement_v2_url_URL = System.getenv().getOrDefault("communicationManagement-v2_URL", "http://localhost:4010");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("appointment-v2_URL",appointment_v2_url_URL)
.systemProperty("communicationManagement-v2_URL",communicationmanagement_v2_url_URL)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
