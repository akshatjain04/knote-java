
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
            String servicetestmanagement_v2_url_URL = System.getenv().getOrDefault("serviceTestManagement-v2_URL", "http://localhost:4010");
String serviceactivationandconfiguration_v1_url_URL = System.getenv().getOrDefault("serviceActivationAndConfiguration-v1_URL", "http://localhost:4010");
String servicetestmanagement_v2_url = System.getenv().getOrDefault("serviceTestManagement-v2_URL", "dummy serviceTestManagement-v2_URL");
String servicetestmanagement_v2_auth_token = System.getenv().getOrDefault("serviceTestManagement-v2_AUTH_TOKEN", "dummy serviceTestManagement-v2_AUTH_TOKEN");
String serviceactivationandconfiguration_v1_auth_token = System.getenv().getOrDefault("serviceActivationAndConfiguration-v1_AUTH_TOKEN", "dummy serviceActivationAndConfiguration-v1_AUTH_TOKEN");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("serviceTestManagement-v2_URL",servicetestmanagement_v2_url_URL)
.systemProperty("serviceActivationAndConfiguration-v1_URL",serviceactivationandconfiguration_v1_url_URL)
.systemProperty("serviceTestManagement-v2_URL", servicetestmanagement_v2_url)
.systemProperty("serviceTestManagement-v2_AUTH_TOKEN", servicetestmanagement_v2_auth_token)
.systemProperty("serviceActivationAndConfiguration-v1_AUTH_TOKEN", serviceactivationandconfiguration_v1_auth_token)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
