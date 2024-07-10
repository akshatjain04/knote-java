
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
            String servicetestmanagement_v2_e262808c96_url = System.getenv().getOrDefault("SERVICETESTMANAGEMENT_V2_E262808C96_URL", "http://127.0.0.1:4010");
String serviceactivationandconfiguration_v1_c70db0ed15_url = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_URL", "http://127.0.0.1:4011");
String servicetestmanagement_v2_e262808c96_auth_token = System.getenv().getOrDefault("SERVICETESTMANAGEMENT_V2_E262808C96_AUTH_TOKEN", "dummy_SERVICETESTMANAGEMENT_V2_E262808C96_AUTH_TOKEN");
String serviceactivationandconfiguration_v1_c70db0ed15_auth_token = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_AUTH_TOKEN", "dummy_SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_AUTH_TOKEN");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("SERVICETESTMANAGEMENT_V2_E262808C96_URL",servicetestmanagement_v2_e262808c96_url)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_URL",serviceactivationandconfiguration_v1_c70db0ed15_url)
.systemProperty("SERVICETESTMANAGEMENT_V2_E262808C96_AUTH_TOKEN", servicetestmanagement_v2_e262808c96_auth_token)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_AUTH_TOKEN", serviceactivationandconfiguration_v1_c70db0ed15_auth_token)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
