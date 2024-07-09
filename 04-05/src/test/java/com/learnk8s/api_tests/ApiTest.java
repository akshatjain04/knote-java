
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
            String finnhub_url_URL = System.getenv().getOrDefault("finnhub_URL", "http://localhost:4010");
String alphavantage_get_resolved_url_URL = System.getenv().getOrDefault("alphavantage-get-resolved_URL", "http://localhost:4010");
String finnhub_url = System.getenv().getOrDefault("finnhub_URL", "dummy finnhub_URL");
String finnhub_auth_token = System.getenv().getOrDefault("finnhub_AUTH_TOKEN", "dummy finnhub_AUTH_TOKEN");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("finnhub_URL",finnhub_url_URL)
.systemProperty("alphavantage-get-resolved_URL",alphavantage_get_resolved_url_URL)
.systemProperty("finnhub_URL", finnhub_url)
.systemProperty("finnhub_AUTH_TOKEN", finnhub_auth_token)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
