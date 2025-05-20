
package com.learnk8s.integration_tests;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
// import com.intuit.karate.http.HttpServer;
// import com.intuit.karate.http.ServerConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Akshatjain04MyProductsRefsHeadsMainBddPetstoreTest {

	@Test
	void testAll() {
		String credentialregister_wallet_1_0_0_resolved_88376fefa0_url = System.getenv()
			.getOrDefault("CREDENTIALREGISTER_WALLET_1_0_0_RESOLVED_88376FEFA0_URL", "http://127.0.0.1:4010");
		String swagger_184f1d2b61_url = System.getenv().getOrDefault("SWAGGER_184F1D2B61_URL", "http://127.0.0.1:4011");
		String swagger_184f1d2b61_auth_token = System.getenv()
			.getOrDefault("SWAGGER_184F1D2B61_AUTH_TOKEN", "dummy_SWAGGER_184F1D2B61_AUTH_TOKEN");
		String credentialregister_wallet_1_0_0_resolved_88376fefa0_auth_token = System.getenv()
			.getOrDefault("CREDENTIALREGISTER_WALLET_1_0_0_RESOLVED_88376FEFA0_AUTH_TOKEN",
					"dummy_CREDENTIALREGISTER_WALLET_1_0_0_RESOLVED_88376FEFA0_AUTH_TOKEN");
		String auth_token = System.getenv().getOrDefault("AUTH_TOKEN", "dummy_AUTH_TOKEN");
		Results results = Runner
			.path("src/test/java/com/learnk8s/integration_tests/Akshatjain04MyProductsRefsHeadsMainBddPetstore")
			.systemProperty("CREDENTIALREGISTER_WALLET_1_0_0_RESOLVED_88376FEFA0_URL",
					credentialregister_wallet_1_0_0_resolved_88376fefa0_url)
			.systemProperty("SWAGGER_184F1D2B61_URL", swagger_184f1d2b61_url)
			.systemProperty("SWAGGER_184F1D2B61_AUTH_TOKEN", swagger_184f1d2b61_auth_token)
			.systemProperty("CREDENTIALREGISTER_WALLET_1_0_0_RESOLVED_88376FEFA0_AUTH_TOKEN",
					credentialregister_wallet_1_0_0_resolved_88376fefa0_auth_token)
			.systemProperty("AUTH_TOKEN", auth_token)
			.reportDir("testReport")
			.parallel(1);
		assertEquals(0, results.getFailCount(), results.getErrorMessages());
	}

}
