
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
            String servicetestmanagement_v2_url = System.getenv().getOrDefault("SERVICETESTMANAGEMENT_V2_URL", "http://localhost:4010");
String serviceactivationandconfiguration_v1_url = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION_V1_URL", "http://localhost:4010");
String servicetestmanagement_v2_url = System.getenv().getOrDefault("SERVICETESTMANAGEMENT-V2_URL", "dummy_SERVICETESTMANAGEMENT-V2_URL");
String serviceactivationandconfiguration_v1_url = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION-V1_URL", "dummy_SERVICEACTIVATIONANDCONFIGURATION-V1_URL");
String servicetestmanagement_v2_auth_token = System.getenv().getOrDefault("SERVICETESTMANAGEMENT-V2_AUTH_TOKEN", "dummy_SERVICETESTMANAGEMENT-V2_AUTH_TOKEN");
String serviceactivationandconfiguration_v1_auth_token = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION-V1_AUTH_TOKEN", "dummy_SERVICEACTIVATIONANDCONFIGURATION-V1_AUTH_TOKEN");
String serviceactivationandconfiguration_v1_auth_token = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION_V1_AUTH_TOKEN", "dummy_SERVICEACTIVATIONANDCONFIGURATION_V1_AUTH_TOKEN");
String servicetestmanagement_v2_auth_token = System.getenv().getOrDefault("SERVICETESTMANAGEMENT_V2_AUTH_TOKEN", "dummy_SERVICETESTMANAGEMENT_V2_AUTH_TOKEN");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("SERVICETESTMANAGEMENT_V2_URL",servicetestmanagement_v2_url)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION_V1_URL",serviceactivationandconfiguration_v1_url)
.systemProperty("SERVICETESTMANAGEMENT-V2_URL", servicetestmanagement_v2_url)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION-V1_URL", serviceactivationandconfiguration_v1_url)
.systemProperty("SERVICETESTMANAGEMENT-V2_AUTH_TOKEN", servicetestmanagement_v2_auth_token)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION-V1_AUTH_TOKEN", serviceactivationandconfiguration_v1_auth_token)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION_V1_AUTH_TOKEN", serviceactivationandconfiguration_v1_auth_token)
.systemProperty("SERVICETESTMANAGEMENT_V2_AUTH_TOKEN", servicetestmanagement_v2_auth_token)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
